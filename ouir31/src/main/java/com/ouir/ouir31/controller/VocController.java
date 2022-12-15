package com.ouir.ouir31.controller;

import com.ouir.ouir31.entity.User;
import com.ouir.ouir31.entity.Voc;
import com.ouir.ouir31.entity.VocFile;
import com.ouir.ouir31.service.VocService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@Log
public class VocController {

    @Autowired
    private VocService vServ;

    private ModelAndView mv = new ModelAndView();

    @GetMapping("/")
    public String list() {

        return "main";
    }

    //글작성 페이지
    @GetMapping("vwriteFrm")
    public String vwriteFrm() {
        return "vwriteFrm";
    }

    //글작성 프로세스
    @PostMapping("vwriteProc")
    public String vwriteProc(@RequestPart List<MultipartFile> files,
                             Voc voc, HttpSession session,
                             RedirectAttributes rttr) {
        log.info("vwrite");
        String vw = vServ.insertVoc(files, voc, session, rttr);
        return vw;
    }

    //voc 전체 리스트 출력(관리자)

    @GetMapping("vocList")
    public ModelAndView getVocList(Integer PageNum, HttpSession session) {
        mv = new ModelAndView();
        mv = vServ.getVocList(PageNum, session);
        mv.setViewName("list");
        return mv;
    }

    //voc 작성글 출력(개인)
//    @GetMapping("myVoc")
//    public ModelAndView getMyVoc(Integer PageNum, HttpSession session, String uid){
//        mv = new ModelAndView();
//        mv = vServ.getMyVoc(PageNum, session, uid);
//        mv.setViewName("myVoc");
//        return mv;
//    }

    // voc 상세보기 페이지
    @GetMapping("detail")
    public ModelAndView getVoc(long voc_no) {
        log.info("detail");
        mv = vServ.getVoc(voc_no);
        mv.setViewName("detail");
        return mv;
    }

    @PostMapping("download")
    public ResponseEntity<Resource> fileDownload(VocFile vf, HttpSession session)
            throws IOException {
        ResponseEntity<Resource> resp = vServ.fileDownload(vf, session);
        return resp;
    }

    @GetMapping("vocUpdate")
    public String updateProc(List<MultipartFile> files, Voc voc, HttpSession session, RedirectAttributes rttr) {
        String view = vServ.vocUpdate(files, voc, session, rttr);
        return view;
    }

    @GetMapping("delete")
    public String deleteVoc(long vocno, HttpSession session, RedirectAttributes rttr) {
        String view = vServ.vocDelete(vocno, session, rttr);
        return view;
    }

}
