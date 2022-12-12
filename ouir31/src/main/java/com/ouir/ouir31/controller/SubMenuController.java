package com.ouir.ouir31.controller;

import com.ouir.ouir31.entity.SubMenu;
import com.ouir.ouir31.service.SubMenuService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Log
public class SubMenuController {
    @Autowired
    private SubMenuService smServ;

    @GetMapping("/submenu/write")
    @ResponseBody
    public boolean subMenuWrite(SubMenu submenu, HttpSession session){
        log.info("subMenuWrite()");
        boolean result = smServ.subMenuInsert(submenu,session);
        return result;
    }

    @GetMapping("/submenu/list")
    @ResponseBody
    public List<SubMenu> subMenuList(){
        log.info("subMenuList()");
        return smServ.subMenuList();
    }

}
