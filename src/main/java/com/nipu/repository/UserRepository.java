package com.nipu.repository;

import com.nipu.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>
{

    List<User> findByLastName(String lastName);

    User findByEmail(String email);
}
