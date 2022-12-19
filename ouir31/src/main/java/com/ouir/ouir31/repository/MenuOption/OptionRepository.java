package com.ouir.ouir31.repository.MenuOption;

import com.ouir.ouir31.entity.MenuOption.Option;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OptionRepository extends CrudRepository<Option, Integer> {

    List<Option> findAll();
    Option findById(int ono);
}
