package com.ouir.ouir31.controller;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class ReactController {
    @GetMapping("getData")
    public String getData(){
        log.info("getData()");
        return "";  
    }

}
