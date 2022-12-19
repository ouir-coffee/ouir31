package com.ouir.ouir31.service.MenuOption;

import com.ouir.ouir31.entity.MenuOption.Menu;
import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.MenuOption.MenuCategories;
import com.ouir.ouir31.repository.MenuOption.MenuCateRepository;
import com.ouir.ouir31.repository.MenuOption.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class MenuService {
    private final MenuRepository mRepo;
    private final MenuCateRepository mcRepo;
    private final ReturnMsg rm = new ReturnMsg();

    // Menu Insert ( 메뉴 작성 )
    public ReturnMsg menuInsert(Menu menu, int mc_code) {
        log.info("menuInsert()");
        rm.setFlag(false);
        try{
            // 카테고리에서의 No를 menu 외래키에 저장.
            menu.setMenuCategories(mcRepo.findById(mc_code).get());
            mRepo.save(menu);
            rm.setFlag(true);
            rm.setMsg("Menu 작성 완료");
        }catch(Exception e){
            e.printStackTrace();
            rm.setFlag(false);
        }
        return rm;

    }

    // Menu List ( 메뉴 전체 출력 )
    public List<Menu> getMenuList() {
        log.info("getMenuList()");
        return mRepo.findAll();
    }

    // Menu Search ( 메뉴 개별 출력 )
    public Menu menuSearch(int mno) {
        log.info("menuSearch()");
        return mRepo.findById(mno);
    }


    // Menu Update ( 메뉴 수정 )
    public ReturnMsg menuUpdate(Menu menu) {
        log.info("menuUpdate()");
        rm.setFlag(false);
        try{
            Menu m = mRepo.findById(menu.getMno());
            if(m == null){
                rm.setFlag(false);
                rm.setMsg("데이터가 없습니다.");
                return rm;
            }
            if(menu.getMitem() == null){
            m.setMitem(mRepo.findById(menu.getMno()).getMitem());
            } else {
                m.setMitem(menu.getMitem());
            }
            if(menu.getMprice() == 0){
                m.setMprice(mRepo.findById(menu.getMno()).getMprice());
            } else {
                m.setMprice(menu.getMprice());
            }
            mRepo.save(m);
            rm.setFlag(true);
            rm.setMsg("수정 성공하였습니다.");
        }catch(Exception e ){
            e.printStackTrace();
            rm.setFlag(false);
            rm.setMsg("수정 실패하였습니다.");
        }

        return rm;
    }

    // Menu Delete ( 메뉴 삭제 )
    public ReturnMsg menuDelete(int mno) {
        log.info("menuDelete()");
        rm.setFlag(false);

        try{
            Menu menu = mRepo.findById(mno);
            if(menu == null){
                rm.setMsg("데이터가 없습니다.");
                rm.setFlag(false);
                return rm;
            }
            mRepo.deleteById(mno);
            rm.setMsg(mno + " 번이 삭제되었습니다.");
            rm.setFlag(true);
        }catch(Exception e){
            e.printStackTrace();
            rm.setFlag(false);
        }
        return rm;
    }

    // 메뉴 카테고리 insert
    public boolean mcInsert(MenuCategories menuCategories) {
        log.info("mcInsert()");
        boolean result = false;

        try{
        mcRepo.save(menuCategories);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

}
