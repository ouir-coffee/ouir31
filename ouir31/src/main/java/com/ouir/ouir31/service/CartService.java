package com.ouir.ouir31.service;

import com.ouir.ouir31.entity.Cart;
import com.ouir.ouir31.entity.Menu;
import com.ouir.ouir31.entity.ReturnMsg;
import com.ouir.ouir31.entity.User;
import com.ouir.ouir31.repository.CartRepository;
import com.ouir.ouir31.repository.MenuRepository;
import com.ouir.ouir31.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class CartService {
    @Autowired
    private CartRepository cRepo;
    @Autowired
    private MenuRepository mRepo;
    @Autowired
    private UserRepository uRepo;

    public ReturnMsg insertCart(Cart cart) {
        log.info("insertCart()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        try{
            cart.setC_m_no(mRepo.findById(1).get());
            cart.setC_u_id(uRepo.findById("potato").get());
            cRepo.save(cart);
            rm.setMsg("장바구니담기에 성공하였습니다.");
            rm.setFlag(true);
        }catch(Exception e){
            e.printStackTrace();
            rm.setMsg("장바구니담기에 실패하였습니다.");
            rm.setFlag(false);
        }
        return rm;
    }

    public List<Cart> cartList() {
        log.info("cartList()");
        return cRepo.findAll();
    }

    public Cart cartSearch(long c_no) {
        log.info("cartSearch()");
        return cRepo.findById(c_no).get();
    }
}
