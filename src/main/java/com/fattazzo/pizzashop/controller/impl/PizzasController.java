package com.fattazzo.pizzashop.controller.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.fattazzo.pizzashop.controller.api.PizzasApi;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.Item;
import com.fattazzo.pizzashop.model.api.ItemPizza;
import com.fattazzo.pizzashop.model.api.ItemPizzaPrice;
import com.fattazzo.pizzashop.model.api.ToppingExtra;
import com.fattazzo.pizzashop.model.api.VariationDough;
import com.fattazzo.pizzashop.model.api.VariationSize;
import com.fattazzo.pizzashop.model.entity.CategoryEntity;
import com.fattazzo.pizzashop.model.entity.ItemImageEntity;
import com.fattazzo.pizzashop.model.entity.ItemPizzaEntity;
import com.fattazzo.pizzashop.model.entity.ItemPizzaPriceEntity;
import com.fattazzo.pizzashop.model.entity.ToppingExtraEntity;
import com.fattazzo.pizzashop.model.entity.VariationSizeEntity;
import com.fattazzo.pizzashop.service.category.CategoryService;
import com.fattazzo.pizzashop.service.item.PizzaService;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.variation.SizeService;
import com.fattazzo.pizzashop.service.variation.ToppingExtraService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "pizzas" })
public class PizzasController implements PizzasApi {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ToppingExtraService toppingExtraService;

	@Autowired
	private SizeService sizeService;

	private final HttpServletRequest request;

	@Autowired
	public PizzasController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	private ItemPizzaEntity addMissingPrices(ItemPizzaEntity pizzaEntity) {
		final List<ItemPizzaPriceEntity> pricesToAdd = new ArrayList<ItemPizzaPriceEntity>();
		final List<VariationSizeEntity> sizesEntities = sizeService.findAll();
		for (final VariationSizeEntity size : sizesEntities) {
			if (pizzaEntity.getPrices().stream().noneMatch(ip -> ip.getSize().getId().equals(size.getId()))) {
				pricesToAdd.add(new ItemPizzaPriceEntity(null, pizzaEntity, size, BigDecimal.ZERO));
			}
		}

		if (!pricesToAdd.isEmpty()) {
			pizzaEntity.getPrices().addAll(pricesToAdd);
			pizzaEntity = pizzaService.save(pizzaEntity);
		}
		return pizzaEntity;
	}

	@Transactional
	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<ItemPizza> createItemPizza(ItemPizza body) {
		ItemPizzaEntity entity = mapper.map(body, ItemPizzaEntity.class);
		for (final ItemPizzaPriceEntity itemPizzaPriceEntity : SetUtils.emptyIfNull(entity.getPrices())) {
			itemPizzaPriceEntity.setParent(entity);
		}

		entity = pizzaService.save(entity);

		return new ResponseEntity<>(mapToItemPizza(entity, true), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<Void> deleteItemPizza(Integer itemId) {
		pizzaService.deleteById(itemId);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<Void> deleteItemPizzaImage(Integer itemId) {
		ItemPizzaEntity entity = pizzaService.findById(itemId).orElseThrow(NoSuchEntityException::new);

		entity.setImage(null);
		entity = pizzaService.save(entity);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<ItemPizza> getItemPizza(Integer itemId, @Valid Boolean includeInvalidPrices) {
		final ItemPizzaEntity entity = pizzaService.findById(itemId).orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapToItemPizza(entity, BooleanUtils.isTrue(includeInvalidPrices)));
	}

	@Override
	public ResponseEntity<Resource> getItemPizzaImage(Integer itemId) {
		final ItemPizzaEntity entity = pizzaService.findById(itemId).orElseThrow(NoSuchEntityException::new);

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
	public ResponseEntity<List<Item>> getItemPizzas(Boolean includeDisabled, Integer categoryId) {
		List<ItemPizzaEntity> entities = new ArrayList<>();

		// find by category
		if (categoryId != null) {
			categoryService.findById(categoryId).orElseThrow(NoSuchEntityException::new);
			entities = pizzaService.findAllByCategory(categoryId);
			if (BooleanUtils.isFalse(includeDisabled)) {
				entities = entities.stream().filter(p -> p.isEnabled()).collect(Collectors.toList());
			}
		} else {
			entities = BooleanUtils.isTrue(includeDisabled) ? pizzaService.findAll() : pizzaService.findAllEnabled();
		}

		final List<Item> items = entities.stream().map(ie -> {
			final Item item = mapper.map(ie, Item.class);
			// Image url
			if (ie.getImage() != null && ie.getImage().getId() != null) {
				item.setImageUrl(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString() + "/" + ie.getId()
						+ "/image");
			}
			// price list
			final List<BigDecimal> priceList = CollectionUtils.emptyIfNull(ie.getPrices()).stream()
					.filter(i -> BigDecimal.ZERO.compareTo(i.getPrice()) != 0).map(ItemPizzaPriceEntity::getPrice)
					.sorted().collect(Collectors.toList());
			item.setAvailablePrices(priceList);
			return item;
		}).collect(Collectors.toList());
		return ResponseEntity.ok(items);
	}

	private ItemPizza mapToItemPizza(ItemPizzaEntity pizzaEntity, boolean includeInvalidPrices) {
		pizzaEntity = addMissingPrices(pizzaEntity);

		final ItemPizza itemPizza = mapper.map(pizzaEntity, ItemPizza.class);

		// prices order
		Collections.sort(itemPizza.getPrices(), (o1, o2) -> {
			if (o1.getSize().getOrder() == null && o2.getSize().getOrder() != null) {
				return 1;
			}
			if (o1.getSize().getOrder() == null && o2.getSize().getOrder() == null) {
				return 0;
			}
			if (o1.getSize().getOrder() != null && o2.getSize().getOrder() == null) {
				return -1;
			}
			return o1.getSize().getOrder().compareTo(o2.getSize().getOrder());
		});

		// price list
		List<ItemPizzaPrice> prices = itemPizza.getPrices();
		if (!includeInvalidPrices) {
			prices = prices.stream().filter(p -> p.getSize().isEnabled()).collect(Collectors.toList());
		}
		final List<BigDecimal> priceList = CollectionUtils.emptyIfNull(pizzaEntity.getPrices()).stream()
				.filter(p -> p.getPrice() != null && BigDecimal.ZERO.compareTo(p.getPrice()) != 0)
				.map(ItemPizzaPriceEntity::getPrice).sorted().collect(Collectors.toList());

		itemPizza.setAvailablePrices(priceList);
		itemPizza.setPrices(prices);

		final CategoryEntity categoryEntity = categoryService.findById(pizzaEntity.getCategory().getId())
				.orElseThrow(NoSuchEntityException::new);
		final List<VariationDough> doughs = CollectionUtils.emptyIfNull(categoryEntity.getDoughs()).stream()
				.filter(d -> d.isEnabled()).map(d -> mapper.map(d, VariationDough.class))
				.sorted(Comparator.comparing(VariationDough::getOrder)).collect(Collectors.toList());
		final List<VariationSize> sizes = CollectionUtils.emptyIfNull(categoryEntity.getSizes()).stream()
				.filter(s -> s.isEnabled()).map(s -> mapper.map(s, VariationSize.class))
				.sorted(Comparator.comparing(VariationSize::getOrder)).collect(Collectors.toList());
		itemPizza.setDoughs(doughs);
		itemPizza.setSizes(sizes);

		final List<ToppingExtraEntity> extrasEntities = toppingExtraService.findAll();
		List<ToppingExtra> extras = CollectionUtils.emptyIfNull(extrasEntities).stream()
				.map(e -> mapper.map(e, ToppingExtra.class)).collect(Collectors.toList());

		extras = extras.stream().filter(p -> doughs.contains(p.getDough()) && sizes.contains(p.getSize()))
				.collect(Collectors.toList());
		if (!includeInvalidPrices) {
			extras = extras.stream().filter(te -> BigDecimal.ZERO.compareTo(te.getExtra()) != 0)
					.collect(Collectors.toList());
		}

		itemPizza.setToppingExtras(extras);

		// iamge url
		if (pizzaEntity.getImage() != null && pizzaEntity.getImage().getId() != null) {
			itemPizza.setImageUrl(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString() + "/image");
		}

		return itemPizza;
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<ItemPizza> updateItemPizza(ItemPizza body, Integer itemId) {
		final ItemPizzaEntity existingEntity = pizzaService.findById(itemId).orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("item.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("idParamNotEquals", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

//		existingEntity.getPrices().clear();
//		mapper.map(body, existingEntity);
//		for (final ItemPizzaPriceEntity itemPizzaPriceEntity : SetUtils.emptyIfNull(existingEntity.getPrices())) {
//			itemPizzaPriceEntity.setParent(existingEntity);
//		}

		// modelmapper briks jpa OneToMany relation
		existingEntity.setDescription(body.getDescription());
		existingEntity.setEnabled(body.isEnabled());
		existingEntity.setCategory(mapper.map(body.getCategory(), CategoryEntity.class));
		existingEntity.setName(body.getName());
		existingEntity.getPrices().clear();

		ListUtils.emptyIfNull(body.getPrices()).stream().forEach(p -> {
			final ItemPizzaPriceEntity itemPizzaPriceEntity = mapper.map(p, ItemPizzaPriceEntity.class);
			itemPizzaPriceEntity.setParent(existingEntity);
			existingEntity.getPrices().add(itemPizzaPriceEntity);
		});

		final ItemPizzaEntity entity = pizzaService.save(existingEntity);
		return ResponseEntity.ok(mapToItemPizza(entity, true));
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<Void> updateItemPizzaImage(MultipartFile file, Integer itemId) {
		final ItemPizzaEntity entity = pizzaService.findById(itemId).orElseThrow(NoSuchEntityException::new);

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
		pizzaService.save(entity);

		return ResponseEntity.noContent().build();
	}

}
