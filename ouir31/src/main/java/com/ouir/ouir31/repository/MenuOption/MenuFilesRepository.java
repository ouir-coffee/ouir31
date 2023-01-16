package com.ouir.ouir31.repository.MenuOption;

import com.ouir.ouir31.entity.MenuOption.MenuFiles;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuFilesRepository extends CrudRepository<MenuFiles,Integer> {

    MenuFiles findByMfmitem(String mitem);
    MenuFiles findByMforiname(String mforiname);


}
