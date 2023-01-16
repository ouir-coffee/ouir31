package com.ouir.ouir31.repository;

import com.ouir.ouir31.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    User findByUid(String uid);
    User findByUemail(String uemail);

    List<User> findByUidContaining(String search);

    List<User> findAll();

    int countUserByUemail(String uemail);
}
