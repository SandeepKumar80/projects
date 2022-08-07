package com.org.service.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.service.user.dao.entity.User;
import com.org.service.user.dao.repository.UserRepository;
import com.org.service.user.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	@Transactional
	public List<User> getAllUsers(Pageable pageable) {
		Page<User> pageResult = repository.findAll(pageable);
		if (pageResult.hasContent()) {
			return (List<User>) pageResult.getContent();
		}
		return null;
	}

	@Override
	@Transactional
	public List<User> getAllUsersByLastName(String lastName, Pageable pageable) {
		Page<User> pageResult = repository.findAllUsersByLastName(lastName, pageable);
		if (pageResult.hasContent()) {
			return (List<User>) pageResult.getContent();
		}
		return null;
	}

	@Override
	@Transactional
	public List<User> getAllUsersByAge(Integer age, Pageable pageable) {
		Page<User> pageResult = repository.findAllUsersByAge(age, pageable);
		if (pageResult.hasContent()) {
			return (List<User>) pageResult.getContent();
		}
		return null;
	}

	@Override
	@Transactional
	public List<User> getAllUsersByLastNameAndAge(String lastName, Integer age, Pageable pageable) {
		Page<User> pageResult = repository.findAllUsersByLastNameAndAge(lastName, age, pageable);
		if (pageResult.hasContent()) {
			return (List<User>) pageResult.getContent();
		}
		return null;
	}
}