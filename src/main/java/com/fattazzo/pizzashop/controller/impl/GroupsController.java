package com.fattazzo.pizzashop.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.GroupsApi;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.dto.Group;
import com.fattazzo.pizzashop.model.entity.GroupEntity;
import com.fattazzo.pizzashop.service.group.GroupService;
import com.fattazzo.pizzashop.service.group.GroupService.GroupReadonlyException;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;

@RestController
public class GroupsController implements GroupsApi {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private GroupService groupService;

	@Autowired
	private LocaleUtilsMessage localeUtilsMessage;

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'SECURITY'})")
	public ResponseEntity<Group> createGroup(@Valid Group body) {
		final GroupEntity existingGroup = groupService.findByName(body.getName()).orElse(null);
		if (existingGroup != null) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("group.insert.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("group.insert.failed.alreadyexist",
							new Object[] { existingGroup.getName() }))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		GroupEntity group = mapper.map(body, GroupEntity.class);

		group = groupService.save(group);

		return new ResponseEntity<>(mapper.map(group, Group.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'SECURITY'})")
	public ResponseEntity<Void> deleteGroup(Integer groupId) {
		try {
			groupService.deleteById(groupId);
		} catch (final GroupReadonlyException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("group.delete.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("group.failed.readonly",
							new Object[] { e.getMessage() }))
					.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Group> getGroup(Integer groupId) {
		final GroupEntity group = groupService.findById(groupId).orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapper.map(group, Group.class));
	}

	@Override
	public ResponseEntity<List<Group>> getGroups() {
		final List<Group> groups = groupService.findAll().stream().map(ge -> mapper.map(ge, Group.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(groups);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'SECURITY'})")
	public ResponseEntity<Group> updateGroup(@Valid Group body, Integer groupId) {
		final GroupEntity existingGroup = groupService.findById(groupId).orElseThrow(NoSuchEntityException::new);

		if (!existingGroup.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("group.update.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("group.update.failed.idParamNotEquals", null))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		try {
			final GroupEntity group = groupService.save(mapper.map(body, GroupEntity.class));
			return ResponseEntity.ok(mapper.map(group, Group.class));
		} catch (final GroupReadonlyException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("group.delete.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("group.failed.readonly",
							new Object[] { e.getMessage() }))
					.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
