package com.fattazzo.pizzashop.controller.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fattazzo.pizzashop.controller.api.ProductsApi;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.Item;
import com.fattazzo.pizzashop.model.api.ItemProduct;
import com.fattazzo.pizzashop.model.api.ItemProductPrice;
import com.fattazzo.pizzashop.model.entity.CategoryEntity;
import com.fattazzo.pizzashop.model.entity.ItemImageEntity;
import com.fattazzo.pizzashop.model.entity.ItemProductEntity;
import com.fattazzo.pizzashop.model.entity.ItemProductPriceEntity;
import com.fattazzo.pizzashop.service.category.CategoryService;
import com.fattazzo.pizzashop.service.item.ProductService;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "products" })
public class ProductsController implements ProductsApi {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	private final HttpServletRequest request;

	@Autowired
	public ProductsController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<ItemProduct> createItemProduct(ItemProduct body) {
		ItemProductEntity entity = mapper.map(body, ItemProductEntity.class);
		for (final ItemProductPriceEntity itemProductPriceEntity : SetUtils.emptyIfNull(entity.getPrices())) {
			itemProductPriceEntity.setParent(entity);
		}

		entity = productService.save(entity);

		return new ResponseEntity<>(mapToItemProduct(entity, true), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<Void> deleteItemProduct(Integer itemId) {
		productService.deleteById(itemId);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<Void> deleteItemProductImage(Integer itemId) {
		ItemProductEntity entity = productService.findById(itemId).orElseThrow(NoSuchEntityException::new);

		entity.setImage(null);
		entity = productService.save(entity);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<ItemProduct> getItemProduct(Integer itemId, @Valid Boolean includeInvalidPrices) {
		final ItemProductEntity entity = productService.findById(itemId).orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapToItemProduct(entity, BooleanUtils.isTrue(includeInvalidPrices)));
	}

	@Override
	public ResponseEntity<Resource> getItemProductImage(Integer itemId) {
		final ItemProductEntity entity = productService.findById(itemId).orElseThrow(NoSuchEntityException::new);

		if (entity.getImage() == null || entity.getImage().getId() == null) {
			return ResponseEntity.noContent().build();
		}

		final InputStreamResource resource = new InputStreamResource(
				new ByteArrayInputStream(entity.getImage().getValue())) {
			@Override
			public long contentLength() {
				return entity.getImage().getValue().length;
			}

			@Override
			public String getFilename() {
				return entity.getName() + ".png";
			}
		};
		return ResponseEntity.ok(resource);
	}

	@Override
	public ResponseEntity<List<Item>> getItemProducts(Boolean includeDisabled, Integer categoryId) {
		List<ItemProductEntity> entities = new ArrayList<>();

		// find by category
		if (categoryId != null) {
			categoryService.findById(categoryId).orElseThrow(NoSuchEntityException::new);
			entities = productService.findAllByCategory(categoryId);
			if (BooleanUtils.isFalse(includeDisabled)) {
				entities = entities.stream().filter(p -> p.isEnabled()).collect(Collectors.toList());
			}
		} else {
			entities = BooleanUtils.isTrue(includeDisabled) ? productService.findAll()
					: productService.findAllEnabled();
		}

		final List<Item> items = entities.stream().map(p -> {
			final Item item = mapper.map(p, Item.class);
			// image url
			if (p.getImage() != null && p.getImage().getId() != null) {
				item.setImageUrl(
						ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString() + "/" + p.getId() + "/image");
			}
			// price list
			final List<BigDecimal> priceList = CollectionUtils.emptyIfNull(p.getPrices()).stream()
					.map(ItemProductPriceEntity::getPrice).sorted().collect(Collectors.toList());
			item.setAvailablePrices(priceList);
			return item;
		}).collect(Collectors.toList());
		return ResponseEntity.ok(items);
	}

	private ItemProduct mapToItemProduct(ItemProductEntity productEntity, boolean includeInvalidPrices) {

		final ItemProduct itemProduct = mapper.map(productEntity, ItemProduct.class);

		// price list
		List<ItemProductPrice> prices = itemProduct.getPrices();
		if (!includeInvalidPrices) {
			prices = prices.stream().filter(p -> p.getVariation().isEnabled()).collect(Collectors.toList());
		}
		final List<BigDecimal> priceList = CollectionUtils.emptyIfNull(productEntity.getPrices()).stream()
				.filter(p -> p.getPrice() != null && BigDecimal.ZERO.compareTo(p.getPrice()) != 0)
				.map(ItemProductPriceEntity::getPrice).sorted().collect(Collectors.toList());

		itemProduct.setAvailablePrices(priceList);
		itemProduct.setPrices(prices);

		if (productEntity.getImage() != null && productEntity.getImage().getId() != null) {
			itemProduct.setImageUrl(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString() + "/image");
		}

		return itemProduct;
	}

	@Transactional
	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<ItemProduct> updateItemProduct(ItemProduct body, Integer itemId) {
		final ItemProductEntity existingEntity = productService.findById(itemId)
				.orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("item.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("idParamNotEquals", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		// modelmapper breaks jpa OneToMany relation
		existingEntity.setDescription(body.getDescription());
		existingEntity.setEnabled(body.isEnabled());
		existingEntity.setCategory(mapper.map(body.getCategory(), CategoryEntity.class));
		existingEntity.setName(body.getName());
		existingEntity.getPrices().clear();

		ListUtils.emptyIfNull(body.getPrices()).stream().forEach(p -> {
			final ItemProductPriceEntity itemProductPriceEntity = mapper.map(p, ItemProductPriceEntity.class);
			itemProductPriceEntity.setParent(existingEntity);
			existingEntity.getPrices().add(itemProductPriceEntity);
		});

		final ItemProductEntity entity = productService.save(existingEntity);
		return ResponseEntity.ok(mapToItemProduct(entity, true));
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<Void> updateItemProductImage(MultipartFile file, Integer itemId) {
		final ItemProductEntity entity = productService.findById(itemId).orElseThrow(NoSuchEntityException::new);

		ItemImageEntity image;
		if (entity.getImage() != null && entity.getImage().getId() != null) {
			image = entity.getImage();
		} else {
			image = ItemImageEntity.builder().build();
		}

		try {
			image.setValue(file.getBytes());
		} catch (final IOException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("item.image.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("item.image.update.failed.detail", null, request))
					.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		entity.setImage(image);
		productService.save(entity);

		return ResponseEntity.noContent().build();
	}

}
