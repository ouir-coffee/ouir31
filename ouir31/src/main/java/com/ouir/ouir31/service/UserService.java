package com.ouir.ouir31.service;

import com.ouir.ouir31.entity.ReturnMsg;
import com.ouir.ouir31.entity.User;
import com.ouir.ouir31.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log
public class UserService {
    @Autowired
    private UserRepository uRepo;

//    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public ReturnMsg insertUser(User user) {
        log.info("insertUser()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

//        String uPwd = encoder.encode(user.getU_pwd());
//        user.setU_pwd(uPwd);

        try{
            uRepo.save(user);
            rm.setMsg("회원가입에 성공하셨습니다.");
            rm.setFlag(true);
        }catch (Exception e){
            e.printStackTrace();
            rm.setMsg("회원가입에 실패하셨습니다.");
            rm.setFlag(false);
        }
        return rm;
    }
}
