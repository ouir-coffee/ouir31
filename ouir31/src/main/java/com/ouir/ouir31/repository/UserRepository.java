package com.ouir.ouir31.repository;

import com.ouir.ouir31.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

//    User findUserByU_id(String u_id);
}
