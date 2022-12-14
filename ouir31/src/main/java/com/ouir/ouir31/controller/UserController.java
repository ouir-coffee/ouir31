package com.ouir.ouir31.controller;

import com.ouir.ouir31.entity.ReturnMsg;
import com.ouir.ouir31.entity.User;
import com.ouir.ouir31.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;

@RestController
@Log
public class UserController {
    @Autowired
    private UserService uServ;

    @PostMapping("/user/write")
    @ResponseBody
    public ReturnMsg userWrite(User user){
        log.info("userWrite()");
        return uServ.insertUser(user);
    }

//    @PostMapping("/user/join")
//    @ResponseBody
//    public ReturnMsg login(User user, HttpSession session){
//        log.info("login()");
//        return uServ.loginProc(user, session);
//    }
}
