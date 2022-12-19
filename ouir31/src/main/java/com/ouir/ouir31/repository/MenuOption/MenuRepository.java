package com.ouir.ouir31.repository.MenuOption;

import com.ouir.ouir31.entity.MenuOption.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends CrudRepository<Menu, Integer> {

    List<Menu> findAll();
    Menu findById(int mno);
}
