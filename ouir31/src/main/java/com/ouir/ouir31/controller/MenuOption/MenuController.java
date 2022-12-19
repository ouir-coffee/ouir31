package com.ouir.ouir31.controller.MenuOption;

import com.ouir.ouir31.entity.MenuOption.Menu;
import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.MenuOption.MenuCategories;
import com.ouir.ouir31.service.MenuOption.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
@Log
public class MenuController {
    private final MenuService mServ;

    // Menu Insert ( 메뉴 작성 )
    @PostMapping("")
    public ReturnMsg menuInsert(Menu menu, int mc_code){
        log.info("menuInsert()");
        return mServ.menuInsert(menu, mc_code);
    }

    // Menu List ( 메뉴 전체 출력 )
    @GetMapping("/list")
    public List<Menu> menuList(){
        log.info("menuList()");
        return mServ.getMenuList();
    }

    // Menu Search ( 메뉴 개별 출력 )
    @GetMapping("/search")
    public Menu menuSearch(int mno){
        log.info("menuSearch()");
        return mServ.menuSearch(mno);
    }

    // Menu Update ( 메뉴 수정 )
    @PutMapping("")
    public ReturnMsg menuUpdate(Menu menu){
        log.info("menuUpdate()");
        return mServ.menuUpdate(menu);
    }

    // Menu Delete ( 메뉴 삭제 )
    @DeleteMapping("")
    public ReturnMsg menuDelete(int mno){
        log.info("menuDelete()");
        return mServ.menuDelete(mno);
    }


    // 메뉴 카테고리 insert
    @PostMapping("/categories")
    public boolean menuCategories(MenuCategories menuCategories){
        log.info("menuCategories()");
        boolean result = mServ.mcInsert(menuCategories);
        return result;
    }

}
