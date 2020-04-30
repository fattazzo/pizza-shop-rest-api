package com.fattazzo.pizzashop.controller.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
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

import com.fattazzo.pizzashop.controller.api.ProductsApi;
import com.fattazzo.pizzashop.entity.data.ProductCategoryEntity;
import com.fattazzo.pizzashop.entity.data.ProductEntity;
import com.fattazzo.pizzashop.entity.data.ProductImageEntity;
import com.fattazzo.pizzashop.entity.data.ToppingExtraEntity;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.Product;
import com.fattazzo.pizzashop.model.api.ProductDetails;
import com.fattazzo.pizzashop.model.api.ToppingExtra;
import com.fattazzo.pizzashop.model.api.VariationDough;
import com.fattazzo.pizzashop.model.api.VariationSize;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.product.ProductCategoryService;
import com.fattazzo.pizzashop.service.product.ProductService;
import com.fattazzo.pizzashop.service.variation.ToppingExtraService;

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
	private ProductCategoryService productCategoryService;

	@Autowired
	private ToppingExtraService toppingExtraService;

	private final HttpServletRequest request;

	@Autowired
	public ProductsController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<ProductDetails> createProduct(@Valid ProductDetails body) {

		ProductEntity entity = mapper.map(body, ProductEntity.class);

		entity = productService.save(entity);

		return new ResponseEntity<>(mapper.map(entity, ProductDetails.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<Void> deleteProduct(Integer productId) {
		productService.deleteById(productId);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<Void> deleteProductImage(Integer productId) {
		ProductEntity product = productService.findById(productId).orElseThrow(NoSuchEntityException::new);

		product.setImage(null);
		product = productService.save(product);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<ProductDetails> getProduct(Integer productId) {
		final ProductEntity entity = productService.findById(productId).orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapToProductDetails(entity));
	}

	@Transactional
	@Override
	public ResponseEntity<Resource> getProductImage(Integer productId) {
		final ProductEntity product = productService.findById(productId).orElseThrow(NoSuchEntityException::new);

		if (product.getImage() == null || product.getImage().getId() == null) {
			return ResponseEntity.noContent().build();
		}

		final InputStreamResource resource = new InputStreamResource(
				new ByteArrayInputStream(product.getImage().getValue())) {
			@Override
			public long contentLength() {
				return product.getImage().getValue().length;
			}

			@Override
			public String getFilename() {
				return product.getName() + ".png";
			}
		};
		return ResponseEntity.ok(resource);
	}

	@Override
	public ResponseEntity<List<Product>> getProducts(Boolean includeDisabled) {
		final List<ProductEntity> entities = BooleanUtils.isTrue(includeDisabled) ? productService.findAll()
				: productService.findAllEnabled();

		final List<Product> products = entities.stream().map(p -> mapper.map(p, Product.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(products);
	}

	private ProductDetails mapToProductDetails(ProductEntity productEntity) {
		final ProductDetails productDetails = mapper.map(productEntity, ProductDetails.class);

		final ProductCategoryEntity categoryEntity = productCategoryService
				.findById(productEntity.getCategory().getId()).orElseThrow(NoSuchEntityException::new);
		final List<VariationDough> doughs = CollectionUtils.emptyIfNull(categoryEntity.getDoughs()).stream()
				.filter(d -> d.isEnabled()).map(d -> mapper.map(d, VariationDough.class)).collect(Collectors.toList());
		final List<VariationSize> sizes = CollectionUtils.emptyIfNull(categoryEntity.getSizes()).stream()
				.filter(s -> s.isEnabled()).map(s -> mapper.map(s, VariationSize.class)).collect(Collectors.toList());
		productDetails.setDoughs(doughs);
		productDetails.setSizes(sizes);

		final List<ToppingExtraEntity> extrasEntities = toppingExtraService.findAll();
		List<ToppingExtra> extras = CollectionUtils.emptyIfNull(extrasEntities).stream()
				.map(e -> mapper.map(e, ToppingExtra.class)).collect(Collectors.toList());

		extras = extras.stream().filter(p -> doughs.contains(p.getDough()) && sizes.contains(p.getSize()))
				.collect(Collectors.toList());

		productDetails.setToppingExtras(extras);

		return productDetails;
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<ProductDetails> updateProduct(@Valid ProductDetails body, Integer productId) {
		final ProductEntity existingEntity = productService.findById(productId).orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("product.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("idParamNotEquals", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		mapper.map(body, existingEntity);
		final ProductEntity product = productService.save(existingEntity);
		return ResponseEntity.ok(mapToProductDetails(product));
	}

	@Transactional
	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<Void> updateProductImage(@Valid MultipartFile file, Integer productId) {
		final ProductEntity productEntity = productService.findById(productId).orElseThrow(NoSuchEntityException::new);

		ProductImageEntity image;
		if (productEntity.getImage() != null && productEntity.getImage().getId() != null) {
			image = productEntity.getImage();
		} else {
			image = ProductImageEntity.builder().build();
		}

		try {
			image.setValue(file.getBytes());
		} catch (final IOException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("company.logo.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("company.logo.update.failed.detail", null, request))
					.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		productEntity.setImage(image);
		productService.save(productEntity);

		return ResponseEntity.noContent().build();
	}

}
