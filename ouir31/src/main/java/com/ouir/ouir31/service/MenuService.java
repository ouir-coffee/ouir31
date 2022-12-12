package com.ouir.ouir31.service;

import com.ouir.ouir31.entity.Menu;
import com.ouir.ouir31.entity.ReturnMsg;
import com.ouir.ouir31.repository.MenuRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Log
public class MenuService {
    @Autowired
    private MenuRepository mRepo;

    public boolean insertMenu(Menu menu) {
        log.info("insertMenu()");
        boolean result = false;

        try{
            mRepo.save(menu);
            result = true;
        }catch(Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;

    }

    public List<Menu> getMenuList() {
        log.info("getMenuList()");
        return mRepo.findAll();
    }

    public Menu menuSearch(int m_no) {
        log.info("menuSearch()");

        try{
            Menu menu = mRepo.findById(m_no).get();
            mRepo.save(menu);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mRepo.findById(m_no).get();
    }


    public ReturnMsg menuUpdate(Menu menu) {
        log.info("menuUpdate()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        try{
            Menu m = mRepo.findById(menu.getM_no()).get();
            if(m.equals(null)){
                rm.setFlag(false);
                rm.setMsg("데이터가 없습니다.");
                return rm;
            }
            m.setM_type(menu.getM_type());
            m.setM_price(menu.getM_price());
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
}
