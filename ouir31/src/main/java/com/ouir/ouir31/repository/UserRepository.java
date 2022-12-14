package com.ouir.ouir31.repository;

import com.ouir.ouir31.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Member;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    User findByUid(String uid);
}
