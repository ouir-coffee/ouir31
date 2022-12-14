package com.ouir.ouir31.controller;

import com.ouir.ouir31.entity.Menu;
import com.ouir.ouir31.entity.ReturnMsg;
import com.ouir.ouir31.service.MenuService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Log
public class MenuController {
    @Autowired
    private MenuService mServ;

    @GetMapping("/menu/write")
    @ResponseBody
    public boolean menuWrite(Menu menu){
        log.info("menuWrite()");
        boolean result = mServ.insertMenu(menu);
        return result;
    }

    @GetMapping("/menu/list")
    @ResponseBody
    public List<Menu> menuList(){
        log.info("menuList()");
        return mServ.getMenuList();
    }

    @GetMapping("/menu/search")
    @ResponseBody
    public Menu menuSearch(int mno){
        log.info("menuSearch()");
        return mServ.menuSearch(mno);
    }

    @GetMapping("/menu/update")
    @ResponseBody
    public ReturnMsg menuUpdate(Menu menu){
        log.info("menuUpdate()");
        return mServ.menuUpdate(menu);
    }
}
