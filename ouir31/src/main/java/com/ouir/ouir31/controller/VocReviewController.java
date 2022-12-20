package com.ouir.ouir31.controller;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.VocReview;
import com.ouir.ouir31.service.VocReviewService;
import lombok.extern.java.Log;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Log

public class VocReviewController {

    @Autowired
    private VocReviewService vrServ;

    @PostMapping("/vocreview/save")
    public ReturnMsg vrSave(VocReview vocReview, HttpSession session){
        log.info("vrSave()");
        return vrServ.vocReviewSave(vocReview,session);
    }

    @PostMapping("vocreview/update")
    public ReturnMsg vrUpdate(VocReview vocReview, HttpSession session){
        log.info("vrUpdate()");
        return vrServ.vocReviewUpdate(vocReview, session);
    }

    @PostMapping("vocreview/delete")
    public ReturnMsg vrDelete(Long vrno, HttpSession session){
        log.info("vrDelete()");
        return vrServ.vocReviewDelete(vrno, session);
    }

    @GetMapping("vocreview/list")
    public @ResponseBody List<VocReview> vrList(long vocno){
        log.info("vrList()");

        List<VocReview> vocReviewList = vrServ.getVocReviewList(vocno);
        return vocReviewList;
    }
}
