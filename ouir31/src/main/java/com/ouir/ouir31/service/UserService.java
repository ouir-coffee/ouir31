package com.ouir.ouir31.service;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.User;
import com.ouir.ouir31.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

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

//        String uPwd = encoder.encode(user.getUpwd());
//        user.setUpwd(uPwd);

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

        User uData = null;
        uData = uRepo.findByUid(user.getUid());

        try {
            if(uData != null){
                String cPwd = user.getUpwd();
                String getPwd = uData.getUpwd();

                if(cPwd.equals(getPwd)){
                    session.setAttribute("loginName",uData.getUname());
                    session.setAttribute("loginId",uData.getUid());
                    rm.setMsg("로그인 성공");
                    rm.setFlag(true);
                } else {
                    rm.setMsg("비밀번호를 확인해주세요.");
                    rm.setFlag(false);
                }
            }else {
                rm.setMsg("아이디를 찾을 수 없습니다.");
                rm.setFlag(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            rm.setMsg("로그인 실패");
            rm.setFlag(false);
        }

        return rm;
    }
}
