package com.ouir.ouir31.repository;

import com.ouir.ouir31.entity.SubMenu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubMenuRepository extends CrudRepository<SubMenu, Long> {

    List<SubMenu> findAll();
}
