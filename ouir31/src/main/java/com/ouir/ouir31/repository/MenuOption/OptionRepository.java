package com.ouir.ouir31.repository.MenuOption;

import com.ouir.ouir31.entity.MenuOption.Option;
import org.aspectj.weaver.loadtime.Options;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OptionRepository extends CrudRepository<Option, String> {

    List<Option> findAll();
    Option findByOitem(String oitem);
}
