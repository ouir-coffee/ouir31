package com.ouir.ouir31.controller;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.*;
import com.ouir.ouir31.service.VocReviewService;
import com.ouir.ouir31.service.VocService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    //글작성 프로세스
    @GetMapping("/voc/write")
    @ResponseBody
    public ReturnMsg vocWrite(@RequestPart List<MultipartFile> files,
                                Voc voc, HttpSession session) {
        log.info("vocWrite()");
        return vServ.writeVoc(files, voc, session);
    }


    //voc 전체 리스트 출력(관리자)
    @GetMapping("/voc/list")
    @ResponseBody
    public List<Voc> getVocList(Model model, Integer PageNum, HttpSession session) {
        log.info("getVocList()");
        return vServ.getVocList(model,PageNum, session);
    }

    @GetMapping("/voc/test1")
    public List<VocFile> getVoc(long vocno){
        return vServ.getVoc(vocno);
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
//    @GetMapping("detail")
//    public ModelAndView getVoc(long voc_no) {
//        log.info("detail");
//        mv = vServ.getVoc(voc_no);
//        mv.setViewName("detail");
//        return mv;
//    }

    @PostMapping("/voc/download")
    public ResponseEntity<Resource> fileDownload(VocFile vf, HttpSession session)
            throws IOException {
        ResponseEntity<Resource> resp = vServ.fileDownload(vf, session);
        return resp;
    }

    @PostMapping("/voc/update")
    @ResponseBody
    public ReturnMsg vocUpdate(List<MultipartFile> files, Voc voc, HttpSession session) {
        log.info("vocUpdate()");
        return vServ.vocUpdate(files, voc, session);
    }

    @PostMapping("/voc/delete")
    @ResponseBody
    public ReturnMsg deleteVoc(long vocno, HttpSession session) {
        log.info("deleteVoc()");
        return vServ.vocDelete(vocno, session);
    }

}
