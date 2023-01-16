package com.ouir.ouir31.service;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.User;
import com.ouir.ouir31.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class AdminService {

    @Autowired
    private UserRepository uRepo;

    public ReturnMsg userSearch(String search) {
        ReturnMsg rm = new ReturnMsg();

        List<User> udata = uRepo.findByUidContaining(search);

        try{
            if(udata.size()==0){
                rm.setFlag(false);
                rm.setMsg("해당 아이디는 검색되지 않습니다.");
            }else {
                for(User i : udata){
                    System.out.println(i.getUid());
                }
                rm.setMsg("검색 성공");
            }

        }catch (Exception e){
            rm.setMsg("코드 잘못짯다.");
        }

        return rm;
    }

    public ReturnMsg userList(){
        ReturnMsg rm = new ReturnMsg();

        List<User> uList = uRepo.findAll();

        try {
            for(User i : uList){
                System.out.println(i);
            }
        }catch (Exception e){
            rm.setMsg("ㅈ망");
        }
        return rm;
    }

}
