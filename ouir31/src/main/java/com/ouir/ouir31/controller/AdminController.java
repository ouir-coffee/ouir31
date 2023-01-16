package com.ouir.ouir31.controller;


import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.service.AdminService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class AdminController {
    @Autowired
    private AdminService aServ;

    @PostMapping("/admin/usersearch")
    @ResponseBody
    public ReturnMsg userSearch(String search){
        log.info("userSearch()");
        return aServ.userSearch(search);
    }

    @PostMapping("/admin/userList")
    @ResponseBody
    public ReturnMsg userList(){
        return aServ.userList();
    }
}
