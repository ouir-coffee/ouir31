package com.ouir.ouir31.controller;

import com.ouir.ouir31.entity.CartOrder.Cart;
import com.ouir.ouir31.dto.ReturnMsg;
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
    public ReturnMsg cartWrite(Cart cart){
        log.info("cartWrite()");
        return cServ.insertCart(cart);
    }

    @GetMapping("")
    public List<Cart> cartList(String cuid){
        log.info("cartList()");
        return cServ.cartList(cuid);
    }

    @PutMapping("")
    public ReturnMsg cartUpdate(Cart cart){
        log.info("cartUpdate()");
        return cServ.cartUpdate(cart);
    }
}
