package com.ouir.ouir31.service;

import com.ouir.ouir31.entity.ReturnMsg;
import com.ouir.ouir31.entity.User;
import com.ouir.ouir31.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;

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

    public ReturnMsg loginProc(User user, HttpSession session){
        log.info("loginProc()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        try {
            User uData = uRepo.findByU_id(user.getU_id());

            if(uData == null){
                rm.setMsg("해당 아이디가 존재하지 않습니다.");
                rm.setFlag(false);
            }else{
                String cpwd = user.getU_pwd();
                String getpwd = uData.getU_pwd();

                if(cpwd.equals(getpwd)){
                    session.setAttribute("loginName",uData.getU_name());
                    session.setAttribute("loginId",uData.getU_id());
                    rm.setMsg("로그인 성공");
                } else {
                    rm.setMsg("로그인 실패");
                }
            }
        } catch (Exception e) {

            rm.setMsg("로그인 실패");
        }

        return rm;
    }
}
