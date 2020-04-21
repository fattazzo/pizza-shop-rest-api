package com.fattazzo.pizzashop;

import java.util.Arrays;
import java.util.Optional;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fattazzo.pizzashop.model.entity.Group;
import com.fattazzo.pizzashop.model.entity.Role;
import com.fattazzo.pizzashop.model.entity.User;
import com.fattazzo.pizzashop.model.entity.User.UserStatusEnum;
import com.fattazzo.pizzashop.service.group.GroupManager;
import com.fattazzo.pizzashop.service.user.UserManager;

@SpringBootApplication
public class PizzaShopApplication {

	protected static final Logger logger = LoggerFactory.getLogger(PizzaShopApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PizzaShopApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	private Group initAdminGroup() {
		return Group.builder().name(Group.NAME_ADMIN).roles(Role.getDefaultAdminRole()).build();
	}

	private User initAdminUser(Group group) {
		return User.builder().username("admin").password(passwordEncoder.encode("admin")).email("admin@email.com")
				.groups(Arrays.asList(group)).status(UserStatusEnum.Active).build();
	}

	private Group initCustomerGroup() {
		return Group.builder().name(Group.NAME_CUSTOMER).roles(Role.getDefaultCustomerRole()).build();
	}

	@Bean
	public CommandLineRunner initData(UserManager userManager, GroupManager groupManager) {
		return (args) -> {

			// Customer
			Group group = groupManager.loadCustomerGroup();
			if (group == null) {
				group = groupManager.save(initCustomerGroup());
			}

			// Worker
			group = groupManager.loadWorkerGroup();
			if (group == null) {
				group = groupManager.save(initWorkerGroup());
			}

			// Admin
			group = groupManager.loadAdminGroup();
			if (group == null) {
				group = groupManager.save(initAdminGroup());
			}
			final Optional<User> user = userManager.findAdminUser();
			if (!user.isPresent()) {
				userManager.save(initAdminUser(group));
			}
		};
	}

	private Group initWorkerGroup() {
		return Group.builder().name(Group.NAME_WORKER).roles(Role.getDefaultWorkerRole()).build();
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
