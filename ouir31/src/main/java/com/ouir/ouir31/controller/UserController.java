package com.ouir.ouir31.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log
public class UserController {
    @GetMapping("/")
    public String home(){
        log.info("home()");
        return "";
    }

}
