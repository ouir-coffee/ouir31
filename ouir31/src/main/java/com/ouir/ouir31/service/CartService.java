package com.ouir.ouir31.service;

import com.ouir.ouir31.entity.CartOrder.Cart;
import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.User;
import com.ouir.ouir31.repository.CartRepository;
import com.ouir.ouir31.repository.MenuOption.MenuRepository;
import com.ouir.ouir31.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cRepo;
    private final MenuRepository mRepo;
    private final UserRepository uRepo;

    public ReturnMsg insertCart(Cart cart) {
        log.info("insertCart()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        try{
            cart.setCmno(mRepo.findById(1));
            cart.setCuid(uRepo.findById("goguma").get());
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

    public List<Cart> cartList(String cuid) {
        log.info("cartList()");
        User user = new User();
        user.setUid(cuid);
        return cRepo.findByCuid(user);
    }

    public ReturnMsg cartUpdate(Cart cart) {
        log.info("cartUpdate()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);
        try{


        }catch(Exception e){
            e.printStackTrace();

        }
        return rm;
    }
}
