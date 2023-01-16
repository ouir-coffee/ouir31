package com.ouir.ouir31.controller;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.service.MailService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log
public class MailController {

    @Autowired
    private MailService mServ;

    //Mail check 컨트롤러
    @PostMapping("/check/mailch")
    @ResponseBody
    public Map<String, Boolean> pw_find(String userEmail) {
        Map<String, Boolean> json = new HashMap<>();
        boolean pwFindCheck = mServ.userEmailCheck(userEmail);

        System.out.println(pwFindCheck);
        json.put("check", pwFindCheck);
        return json;
    }

    @GetMapping  ("/mail/check")
    @ResponseBody
    public ReturnMsg mailcheck(@RequestParam("uemail") String uemail) {
        ReturnMsg rm = new ReturnMsg();
        return mServ.createMailAndChangePassword(uemail);
    }

}

