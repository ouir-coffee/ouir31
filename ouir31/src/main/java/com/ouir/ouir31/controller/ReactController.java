package com.ouir.ouir31.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Log
public class ReactController {
    @GetMapping("/")
    public String home(){
        log.info("home()");
        return "";
    }

}
