package com.ouir.ouir31.service;

import com.ouir.ouir31.entity.Cart;
import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.MenuOption.Menu;
import com.ouir.ouir31.entity.MenuOption.Option;
import com.ouir.ouir31.entity.User;
import com.ouir.ouir31.repository.CartRepository;
import com.ouir.ouir31.repository.MenuOption.MenuRepository;
import com.ouir.ouir31.repository.MenuOption.OptionRepository;
import com.ouir.ouir31.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cRepo;
    private final MenuRepository mRepo;
    private final OptionRepository oRepo;
    private final ReturnMsg rm = new ReturnMsg();


    public ReturnMsg cartInsert(Cart cart) {
        int price = 0;
        try {
            //유저의 아이디
            cart.setCuid(cart.getCuid());

            Menu menu = mRepo.findByMitem(cart.getCmitem());

            if (cart.getCoitem1() != null) {
                Option option1 = oRepo.findByOitem(cart.getCoitem1());
                Option option2 = oRepo.findByOitem(cart.getCoitem2());
                Option option3 = oRepo.findByOitem(cart.getCoitem3());
                price += option1.getOprice();
                price += option2.getOprice();
                price += option3.getOprice();
            }
            price += menu.getMprice();

            cart.setCmethod(cart.getCmethod() == null ? "현장결제" : cart.getCmethod());
            cart.setCrequest(cart.getCrequest() == null ? "기본" : cart.getCrequest());
            cart.setCstatus(cart.getCstatus() == 0 ? 1 : cart.getCstatus());
            cart.setCprice(price);
            cRepo.save(cart);
            rm.setFlag(true);

        } catch (Exception e) {
            e.printStackTrace();
            rm.setFlag(false);
        }
        return rm;
    }

    public List<Cart> cartList() {
        return cRepo.findAll();
    }

    public List<Cart> cartSearch(String cuid) {
        return cRepo.findByCuid(cuid);
    }

    public ReturnMsg cartUpdate(Cart cart) {
        try {
            cart = cRepo.findByCno(cart.getCno());
            cRepo.save(cart);
            rm.setFlag(true);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setFlag(false);
        }
        return rm;
    }

    public ReturnMsg cartDelete(long cno) {
        try {
            cRepo.deleteById(cno);
            rm.setFlag(true);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setFlag(false);
        }

        return rm;
    }
}
