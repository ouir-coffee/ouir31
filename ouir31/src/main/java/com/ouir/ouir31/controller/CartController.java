package com.ouir.ouir31.controller;

import com.ouir.ouir31.entity.Cart;
import com.ouir.ouir31.entity.ReturnMsg;
import com.ouir.ouir31.entity.SubMenu;
import com.ouir.ouir31.service.CartService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Log
public class CartController {
//    @Autowired
//    private CartService cServ;
//
//    @PostMapping("/cart/write")
//    @ResponseBody
//    public ReturnMsg cartWrite(SubMenu submenu, Cart cart, HttpSession session){
//        log.info("cartWrite()");
//        return cServ.InsertCart(submenu,cart, session);
//    }
}
