package com.ouir.ouir31.repository.MenuOption;

import com.ouir.ouir31.entity.MenuOption.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuRepository extends CrudRepository<Menu, String> {

    List<Menu> findAll();
    Menu findByMitem(String mitem);

}
