package com.org.service.user.dao.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.org.service.user.dao.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Serializable> {

	@Query(value = "SELECT * FROM <To_Be_Replaced_Schema_Name>.USERS WHERE LOWER(LAST_NAME) = LOWER(?1)", nativeQuery = true)
	public Page<User> findAllUsersByLastName(String lastName, Pageable pageable);

	@Query(value = "SELECT * FROM <To_Be_Replaced_Schema_Name>.USERS WHERE AGE = ?1", nativeQuery = true)
	public Page<User> findAllUsersByAge(Integer age, Pageable pageable);

	@Query(value = "SELECT * FROM <To_Be_Replaced_Schema_Name>.USERS WHERE LOWER(LAST_NAME) = LOWER(?1) AND AGE = ?2", nativeQuery = true)
	public Page<User> findAllUsersByLastNameAndAge(String lastName, Integer age, Pageable pageable);

}
