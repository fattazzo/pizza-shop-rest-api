package com.fattazzo.pizzashop.service.variation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.entity.data.DoughEntity;
import com.fattazzo.pizzashop.entity.data.SizeEntity;
import com.fattazzo.pizzashop.entity.data.ToppingEntity;
import com.fattazzo.pizzashop.entity.data.ToppingExtraEntity;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.repository.ToppingExtraRepository;

@Service
public class ToppingExtraService {

	@Autowired
	private ToppingExtraRepository toppingExtraRepository;

	@Autowired
	private DoughService doughService;

	@Autowired
	private SizeService sizeService;

	@Autowired
	private ToppingService toppingService;

	public List<ToppingExtraEntity> findAll() {
		final List<ToppingExtraEntity> entities = toppingExtraRepository
				.findAll(Sort.by(Direction.ASC, "topping.name"));

		final Comparator<ToppingExtraEntity> comparator = Comparator
				.<ToppingExtraEntity, String>comparing(te -> te.getTopping().getName())
				.thenComparing(te -> te.getDough().getOrder()).thenComparing(te -> te.getSize().getOrder());

		entities.sort(comparator);

		return entities;
	}

	public List<ToppingExtraEntity> findAll(Integer doughId, Integer sizeId) {
		if (doughId == null || sizeId == null) {
			return new ArrayList<>();
		}

		final List<ToppingExtraEntity> entities = toppingExtraRepository
				.findByDoughIdAndSizeIdOrderByToppingNameAsc(doughId, sizeId);

		final DoughEntity dough = doughService.findById(doughId).orElseThrow(NoSuchEntityException::new);
		final SizeEntity size = sizeService.findById(sizeId).orElseThrow(NoSuchEntityException::new);
		final List<ToppingEntity> toppings = toppingService.findAll();

		for (final ToppingEntity topping : toppings) {
			final Optional<ToppingExtraEntity> existingEntity = entities.stream().filter(
					te -> te.getTopping().equals(topping) && te.getDough().equals(dough) && te.getSize().equals(size))
					.findFirst();

			if (!existingEntity.isPresent()) {
				ToppingExtraEntity entityCreated = ToppingExtraEntity.builder().dough(dough).enabled(true)
						.extra(BigDecimal.ZERO).size(size).topping(topping).build();

				entityCreated = toppingExtraRepository.save(entityCreated);
				entities.add(entityCreated);
			}
		}

		final Comparator<ToppingExtraEntity> comparator = Comparator
				.<ToppingExtraEntity, String>comparing(te -> te.getTopping().getName())
				.thenComparing(te -> te.getDough().getOrder()).thenComparing(te -> te.getSize().getOrder());

		entities.sort(comparator);

		return entities;
	}

	public Optional<ToppingExtraEntity> findById(Integer id) {
		return toppingExtraRepository.findById(id);
	}

	public ToppingExtraEntity save(ToppingExtraEntity toppingExtra) {
		if (toppingExtra.getId() != null) {
			findById(toppingExtra.getId()).orElseThrow(NoSuchEntityException::new);
		}
		return toppingExtraRepository.save(toppingExtra);
	}
}
