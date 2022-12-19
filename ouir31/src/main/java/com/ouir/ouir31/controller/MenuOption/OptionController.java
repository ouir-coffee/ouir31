package com.ouir.ouir31.controller.MenuOption;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.MenuOption.MenuCategories;
import com.ouir.ouir31.entity.MenuOption.Option;
import com.ouir.ouir31.entity.MenuOption.OptionCategories;
import com.ouir.ouir31.service.MenuOption.OptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/option")
@RequiredArgsConstructor
@Log
public class OptionController {
    private final OptionService oServ;

    // Option Insert ( 옵션 추가 )
    @PostMapping("")
    private ReturnMsg optionInsert(Option option, int oc_code){
        log.info("optionInsert()");
        return oServ.optionInsert(option, oc_code);
    }

    // Option List ( 옵션 전체 출력 )
    @GetMapping("/list")
    private List<Option> optionList(){
        log.info("optionList()");
        return oServ.optionList();
    }
    // Option Search ( 옵션 개별 출력 )
    @GetMapping("/search")
    private Option optionSearch(int ono){
        log.info("optionSearch()");
        return oServ.optionSearch(ono);
    }

    // Option Update ( 옵션 수정 )
    @PutMapping("")
    private ReturnMsg optionUpdate(Option option){
        log.info("optionUpdate()");
        return oServ.optionUpdate(option);
    }

    // Option Delete ( 옵션 삭제 )
    @DeleteMapping("")
    private ReturnMsg optionDelete(int ono){
        log.info("optionDelete()");
        return oServ.optionDelete(ono);
    }

    // 옵션 카테고리 Insert
    @PostMapping("/categories")
    public boolean optionCategories(OptionCategories optionCategories) {
        log.info("optionCategories()");
        return oServ.optionCategories(optionCategories);
    }

}
