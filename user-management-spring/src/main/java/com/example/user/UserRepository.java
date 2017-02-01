package com.example.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> findByFirstNameLike(String firstName);

	public List<User> findAllByOrderByLastName();

	public List<User> findAllByOrderByLastNameDesc();

	public List<User> findAllByOrderByBirthDate();

	public List<User> findAllByOrderByBirthDateDesc();
}
