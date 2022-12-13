package com.ouir.ouir31.repository;

import com.ouir.ouir31.entity.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuRepository extends CrudRepository<Menu, Integer> {

//    Menu findMenuByM_no(int m_id);
    List<Menu> findAll();
}
