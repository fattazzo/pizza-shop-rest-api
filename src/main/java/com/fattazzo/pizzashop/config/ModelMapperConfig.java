package com.fattazzo.pizzashop.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fattazzo.pizzashop.model.api.ItemPizzaPrice;
import com.fattazzo.pizzashop.model.api.OrderDetails;
import com.fattazzo.pizzashop.model.api.UserDetails;
import com.fattazzo.pizzashop.model.entity.ItemPizzaPriceEntity;
import com.fattazzo.pizzashop.model.entity.OrderEntity;
import com.fattazzo.pizzashop.model.entity.UserEntity;

@Configuration
public class ModelMapperConfig {

	private PropertyMap<ItemPizzaPrice, ItemPizzaPriceEntity> getItemPizzaPriceMapping() {
		return new PropertyMap<ItemPizzaPrice, ItemPizzaPriceEntity>() {
			@Override
			protected void configure() {
				map(source.getVariationSize(), destination.getVariationSize());
			}
		};
	}

	private PropertyMap<OrderDetails, OrderEntity> getOrderMapping() {
		return new PropertyMap<OrderDetails, OrderEntity>() {
			@Override
			protected void configure() {
				skip(destination.getLines());
			}
		};
	}

	private PropertyMap<UserDetails, UserEntity> getUserDetailsMapping() {
		return new PropertyMap<UserDetails, UserEntity>() {
			@Override
			protected void configure() {
				skip(destination.getDeliveryAddresses());
			}
		};
	}

	@Bean
	public ModelMapper modelMapper() {
		final ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(getOrderMapping());
		modelMapper.addMappings(getItemPizzaPriceMapping());
		modelMapper.addMappings(getUserDetailsMapping());
		return modelMapper;
	}
}
