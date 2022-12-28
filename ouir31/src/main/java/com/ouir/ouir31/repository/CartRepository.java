package com.ouir.ouir31.repository;

import com.ouir.ouir31.entity.Cart;
import com.ouir.ouir31.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {

    Cart findByCno(long cno);
    List<Cart> findAll();
    List<Cart> findByCuid(String cuid);
}
