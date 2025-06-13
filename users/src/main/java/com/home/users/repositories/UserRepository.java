package com.home.users.repositories;

import com.home.common.entities.User;
import io.vavr.control.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Option<User> findByUsername(String username);

}
