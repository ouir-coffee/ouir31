package com.ouir.ouir31.controller;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.User;
import com.ouir.ouir31.service.MailService;
import com.ouir.ouir31.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@Log
public class UserController {
    @Autowired
    private UserService uServ;
    @Autowired
    private MailService mServ;

    @PostMapping("/user/join")
    public ReturnMsg userWrite(@RequestBody User user){
        log.info("userWrite()");
        return uServ.insertUser(user);
    }

    @PostMapping("/user/login")
    public Map<Object, Object> login(@RequestBody User user, HttpSession session){
        log.info("login()");
        return uServ.loginProc(user, session);
    }

    @PostMapping("/user/logout")
    @ResponseBody
    public ReturnMsg userLogout(HttpSession session){
        log.info("userLogout()");
        return uServ.userLogout(session);
    }

    @PostMapping("/user/idsearch")
    @ResponseBody
    public ReturnMsg userIdsearch(User uemail){
        log.info("userIdsearch()");
        return uServ.userIdsearch(uemail);
    }

    @PostMapping("/user/pwdsearch")
    @ResponseBody
    public ReturnMsg userPwdsearch(String email){
        return mServ.userpwdsearch(email);
    }

    @PostMapping("/user/update")
    @ResponseBody
    public ReturnMsg userUpdate(User user,String check,HttpSession session){
        log.info("userUpdate()");
        return uServ.userUpdate(user,check,session);
    }

    @PostMapping("/user/delete")
    @ResponseBody
    public ReturnMsg userDelete(HttpSession session,String dCheck){
        log.info("userDelete()");
        return uServ.userDelete(session,dCheck);
    }
}
