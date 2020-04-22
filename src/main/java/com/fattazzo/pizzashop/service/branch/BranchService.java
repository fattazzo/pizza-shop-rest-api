package com.fattazzo.pizzashop.service.branch;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.model.entity.BranchEntity;
import com.fattazzo.pizzashop.repository.BranchRepository;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;

@Service
public class BranchService {

	public class BranchPrimaryCheckException extends Exception {
		private static final long serialVersionUID = -7569559135419043478L;

		public BranchPrimaryCheckException() {
			super();
		}
	}

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	LocaleUtilsMessage localeUtilsMessage;

	public void deleteById(Integer id) throws BranchPrimaryCheckException {
		final BranchEntity entity = findById(id).orElseThrow(NoSuchEntityException::new);

		if (entity.isPrimary()) {
			throw new BranchPrimaryCheckException();
		}

		branchRepository.deleteById(id);
	}

	public List<BranchEntity> findAll() {
		return branchRepository.findAll();
	}

	public Optional<BranchEntity> findById(Integer id) {
		return branchRepository.findById(id);
	}

	public Optional<BranchEntity> loadPrimary() {
		return branchRepository.findOneByPrimary(true);
	}

	public BranchEntity save(BranchEntity branch) throws BranchPrimaryCheckException {
		if (branch.getId() != null) {
			findById(branch.getId()).orElseThrow(NoSuchEntityException::new);
		}

		final Optional<BranchEntity> primaryBranch = branchRepository.findOneByPrimary(true);

		// Check primary flag
		if (branch.isPrimary() && primaryBranch.isPresent() && branch.getId() != primaryBranch.get().getId()) {
			throw new BranchPrimaryCheckException();
		}

		if (!branch.isPrimary() && !branchRepository.findOneByPrimary(true).isPresent()) {
			throw new BranchPrimaryCheckException();
		}

		CollectionUtils.emptyIfNull(branch.getShippingZones()).stream().forEach(zone -> zone.setBranch(branch));

		return branchRepository.save(branch);
	}
}
