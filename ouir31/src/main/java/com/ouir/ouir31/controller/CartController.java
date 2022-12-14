package com.ouir.ouir31.controller;

import com.ouir.ouir31.entity.Cart;
import com.ouir.ouir31.entity.ReturnMsg;
import com.ouir.ouir31.service.CartService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Log
public class CartController {
    @Autowired
    private CartService cServ;


    @PostMapping("/cart/write")
    @ResponseBody
    public ReturnMsg cartWrite(Cart cart){
        log.info("cartWrite()");
        return cServ.insertCart(cart);
    }

    @PostMapping("/cart/list")
    @ResponseBody
    public List<Cart> cartList(){
        log.info("cartList()");
        return cServ.cartList();
    }

    @PostMapping("/cart/search")
    @ResponseBody
    public Cart cartSearch(long cNo){
        log.info("cartSearch()");
        return cServ.cartSearch(cNo);
    }

//    @PostMapping("/cart/update")
//    @ResponseBody
//    public ReturnMsg cartUpdate(Cart cart){
//        log.info("cartUpdate()");
//        return cServ.cartUpdate(cart);
//    }
}
