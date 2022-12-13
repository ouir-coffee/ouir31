package com.ouir.ouir31.service;

import com.ouir.ouir31.entity.*;
import com.ouir.ouir31.repository.CartRepository;
import com.ouir.ouir31.repository.MenuRepository;
import com.ouir.ouir31.repository.SubMenuRepository;
import com.ouir.ouir31.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@Log
public class CartService {
//    @Autowired
//    private CartRepository cRepo;
//
//    @Autowired
//    private UserRepository uRepo;
//
//    @Autowired
//    private MenuRepository mRepo;
//
//    @Autowired
//    private SubMenuRepository smRepo;
//
//    @Transactional
//    public ReturnMsg InsertCart(SubMenu submenu, Cart cart, HttpSession session) {
//        log.info("InsertCart()");
//        ReturnMsg rm = new ReturnMsg();
//        rm.setFlag(false);
//        try{
//            User user = uRepo.findUserByU_id("goguma");
//            cart.setC_u_id(user);
//            Menu menu = mRepo.findMenuByM_no(1);
//            cart.setC_m_no(menu);
//            cRepo.save(cart);
//            subMenuInsert(submenu);
//
//
//            rm.setMsg("장바구니에 저장 완료.");
//            rm.setFlag(true);
//        }catch(Exception e){
//            e.printStackTrace();
//            rm.setMsg("저장되지 않았습니다.");
//            rm.setFlag(false);
//        }
//        return rm;
//    }
//
//    private void subMenuInsert (SubMenu submenu){
//        log.info("subMenuInsert()");
//
//        try{
//            smRepo.save(submenu);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }


}
