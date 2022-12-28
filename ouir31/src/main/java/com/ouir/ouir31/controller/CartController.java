package com.ouir.ouir31.controller;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.Cart;
import com.ouir.ouir31.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Log
public class CartController {
    private final CartService cServ;

    @PostMapping("")
    public ReturnMsg cartInsert(Cart cart){
        log.info("cartInsert()");
        return cServ.cartInsert(cart);
    }

    @GetMapping("")
    public List<Cart> cartList(){
        log.info("cartList()");
        return cServ.cartList();
    }

    //개인 장바구니
    @GetMapping("/search")
    public List<Cart> cartSearch(String cuid){
        log.info("cartSearch()");
        return cServ.cartSearch(cuid);
    }

    @PutMapping("")
    public ReturnMsg cartUpdate(Cart cart){
        log.info("cartUpdate()");
        return cServ.cartUpdate(cart);
    }

    @DeleteMapping("")
    public ReturnMsg cartDelete(long cno){
        log.info("cartDelete()");
        return cServ.cartDelete(cno);
    }

}
