package com.org.service.user.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.org.service.user.dao.entity.User;

@Component
public interface UserService {
	public List<User> getAllUsers(Pageable pageable);

	public List<User> getAllUsersByLastName(String lastName, Pageable pageable);

	public List<User> getAllUsersByAge(Integer age, Pageable pageable);

	public List<User> getAllUsersByLastNameAndAge(String lastName, Integer age, Pageable pageable);

}
