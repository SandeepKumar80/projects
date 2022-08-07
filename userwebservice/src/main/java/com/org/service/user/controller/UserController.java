package com.org.service.user.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.service.user.dao.entity.User;
import com.org.service.user.exception.PageSizeTooLarge;
import com.org.service.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"), 
		@ApiResponse(responseCode = "400", description = "Bad request"), 
		@ApiResponse(responseCode = "404", description = "Not Found"),
		@ApiResponse(responseCode = "500", description = "System Error") })
	@Operation(summary = "getUsers", description = "Call to fetch Users.")
	public ResponseEntity<List<User>> getUsers(@RequestParam(defaultValue = "0") Integer pageIndex, 
			@RequestParam(defaultValue = "500") Integer pageSize, @RequestParam(required = false) String sortBy, 
			@RequestParam(required = false) String lastName, @RequestParam(required = false) Integer age)
			throws Exception {
		List<User> lstUser = null;
		log.info("Entering getUsers, pageIndex: {}, sizeOfPage: {}, sortBy: {}, lastName: {}, age: {}", pageIndex, pageSize, sortBy, lastName, age);
		if (pageSize > 1000)
			throw new PageSizeTooLarge("Requested Page Size is too large, keep it under 1000 records for optimal performance.");
		String sort = getSortBy(sortBy);
		Pageable pageable = StringUtils.isNotEmpty(sort) ? PageRequest.of(pageIndex, pageSize, Sort.by(sort)) : PageRequest.of(pageIndex, pageSize);
		if (StringUtils.isNotEmpty(lastName) && age != null) {
			lstUser = userService.getAllUsersByLastNameAndAge(lastName, age, pageable);
		} else {
			if (StringUtils.isNotEmpty(lastName)) {
				lstUser = userService.getAllUsersByLastName(lastName, pageable);
			} else if (age != null) {
				lstUser = userService.getAllUsersByAge(age, pageable);
			} else {
				lstUser = userService.getAllUsers(pageable);
			}
		}
		log.info("Exiting getUsers, User List size: {}", lstUser == null ? 0 : lstUser.size());
		if (lstUser == null || lstUser.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(lstUser, HttpStatus.OK);
	}

	private String getSortBy(String sortBy) {
		if (StringUtils.isEmpty(sortBy))
			return null;

		switch (sortBy) {
		case "firstName":
			return "FIRST_NAME";
		case "lastName":
			return "LAST_NAME";
		case "three":
			return "AGE";
		default:
			return null;
		}
	}
}
