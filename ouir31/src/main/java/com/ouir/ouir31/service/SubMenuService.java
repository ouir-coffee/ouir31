package com.ouir.ouir31.service;

import com.ouir.ouir31.entity.SubMenu;
import com.ouir.ouir31.repository.SubMenuRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Log
public class SubMenuService {

    @Autowired
    private SubMenuRepository smRepo;

    public boolean subMenuInsert(SubMenu submenu, HttpSession session) {
        log.info("subMenuInsert()");
        boolean result = false;

        try{
            smRepo.save(submenu);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public List<SubMenu> subMenuList() {
        log.info("subMenuList");
        return smRepo.findAll();
    }
}
