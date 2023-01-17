package com.ouir.ouir31.controller.MenuOption;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.MenuOption.Menu;
import com.ouir.ouir31.service.MenuOption.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log
@RequestMapping("/menu")
public class MenuController {
    private final MenuService mServ;

    // 메뉴 저장
    @PostMapping("")
    public ReturnMsg menuInsert(@RequestPart(value = "data", required = true) Menu menu,
                                @RequestPart(value = "files", required = false)List<MultipartFile> files,
                                HttpSession session){
        log.info("menuInsert()");
        return mServ.menuInsert(menu, files, session);
    }

    // 메뉴 전체 출력
    @GetMapping("")
    public List<Menu> menuAll(){
        log.info("menuAll()");
        return mServ.menuAll();
    }

    // 메뉴 카테고리별 출력
    @GetMapping("/categories")
    public List<Menu> menuList(@RequestParam String mcate){
        log.info("menuList()");
        log.info("mCate : " + mcate);
        return mServ.menuList(mcate);
    }

    // 메뉴 개별 출력
    @GetMapping("/search")
    public Menu menuSearch(@RequestParam String mitem){
        log.info("menuSearch()");
        return mServ.menuSearch(mitem);
    }

    // 메뉴 삭제
    @DeleteMapping("")
    public ReturnMsg menuDelete(@RequestParam String mitem, HttpSession session){
        log.info("menuDelete()");
        return mServ.menuDelete(mitem,session);
    }
}
