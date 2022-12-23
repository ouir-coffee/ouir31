package com.ouir.ouir31.controller;

import com.ouir.ouir31.entity.Notice;
import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.NoticeFile;
import com.ouir.ouir31.service.NoticeService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@Log

public class NoticeController {
    @Autowired
    private NoticeService nServ;

    @GetMapping("/notice/write")
    @ResponseBody
    public ReturnMsg noticeWrite(Notice notice, List<MultipartFile> files, HttpSession session){
        log.info("noticeWrite()");
        return nServ.noticeWrite(notice,files,session);
    }

    @PostMapping("/notice/update")
    @ResponseBody
    public ReturnMsg noticeUpdate(Notice notice,List<MultipartFile> files, HttpSession session){
        log.info("noticeUpdate()");
        return nServ.noticeUpdate(notice,files,session);
    }

    @PostMapping("/notice/delete")
    @ResponseBody
    public ReturnMsg noticeDelete(Notice notice,HttpSession session){
        log.info("noticeDelete()");
        return nServ.noticeDelete(notice.getNno(),session);
    }

    @GetMapping("/notice/list")
    @ResponseBody
    public List<Notice> getNoticeList(Model model, Integer pageNum, HttpSession session){
        log.info("getNoticeList");
        return (List<Notice>) nServ.getNoticeList(model,pageNum,session);
    }

    @GetMapping("/notice/download")
    public ResponseEntity<Resource> fileDownload(NoticeFile noticeFile, HttpSession session)
            throws IOException {
        ResponseEntity<Resource> resp = nServ.fileDownload(noticeFile, session);
        return resp;
    }

    @GetMapping("/notice/filetest")
    public List<NoticeFile> getNotice(long nno){
        return nServ.getNotice(nno);
    }


}
