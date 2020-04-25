package com.fattazzo.pizzashop.service.group;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.entity.security.GroupEntity;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.repository.GroupRepository;

@Service
public class GroupService {

	public class GroupReadonlyException extends RuntimeException {
		private static final long serialVersionUID = -7569559135419043478L;

		public GroupReadonlyException(String s) {
			super(s);
		}

	}

	protected static final Logger logger = LoggerFactory.getLogger(GroupService.class);

	@Autowired
	private GroupRepository groupRepository;

	public void deleteById(Integer id) {
		final GroupEntity groupEntity = findById(id).orElseThrow(NoSuchEntityException::new);

		if (groupEntity.isReadOnly()) {
			throw new GroupReadonlyException(groupEntity.getName());
		}
		groupRepository.deleteById(id);
	}

	public List<GroupEntity> findAll() {
		return groupRepository.findAll();
	}

	public Optional<GroupEntity> findById(Integer id) {
		return groupRepository.findById(id);
	}

	public Optional<GroupEntity> findByName(String name) {
		return groupRepository.findByNameIgnoreCase(name);
	}

	public Optional<GroupEntity> loadAdminGroup() {
		return groupRepository.findByNameIgnoreCase(GroupEntity.NAME_ADMIN);
	}

	public Optional<GroupEntity> loadCustomerGroup() {
		return groupRepository.findByNameIgnoreCase(GroupEntity.NAME_CUSTOMER);
	}

	public Optional<GroupEntity> loadWorkerGroup() {
		return groupRepository.findByNameIgnoreCase(GroupEntity.NAME_WORKER);
	}

	public GroupEntity save(GroupEntity group) {
		if (group.getId() != null) {
			final GroupEntity groupEntity = findById(group.getId()).orElseThrow(NoSuchEntityException::new);

			if (groupEntity.isReadOnly()) {
				throw new GroupReadonlyException(groupEntity.getName());
			}
		}
		return groupRepository.save(group);
	}

}
