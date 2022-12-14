package com.ouir.ouir31.controller;

import com.ouir.ouir31.entity.Notice;
import com.ouir.ouir31.entity.ReturnMsg;
import com.ouir.ouir31.service.NoticeService;
import lombok.extern.java.Log;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
@Log

public class NoticeController {
    @Autowired
    private NoticeService nServ;

    @GetMapping("/notice/write")
    @ResponseBody
    public boolean noticeWrite(Notice notice){
        log.info("noticeWrite()");
        boolean result = nServ.noticeWrite(notice);
        return result;
    }

    @PostMapping("/notice/update")
    @ResponseBody
    public ReturnMsg noticeUpdate(Notice notice){
        log.info("noticeUpdate()");
        return nServ.noticeUpdate(notice);
    }

    @PostMapping("/notice/delete")
    @ResponseBody
    public ReturnMsg noticeDelete(Notice notice){
        log.info("noticeDelete()");
        return nServ.noticeDelete(notice.getNno());
    }

    @GetMapping("/notice/list")
    @ResponseBody
    public List<Notice> noticeList(){
        log.info("noticeList");
        return nServ.noticeList();
    }


}
