package com.ouir.ouir31.service;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.*;
import com.ouir.ouir31.repository.UserRepository;
import com.ouir.ouir31.repository.VocFileRepository;
import com.ouir.ouir31.repository.VocRepository;
import com.ouir.ouir31.util.PagingUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class VocService {
    @Autowired
    private VocRepository vRepo;
    @Autowired
    private VocFileRepository vfRepo;



    //페이징
//    private String getPaging(int pageNum, int totalPage) {
//        String pageHtml = null;
//        int pageCnt = 5;
//        String listName = "?";
//        PagingUtil paging = new PagingUtil(totalPage, pageNum, pageCnt, listName);
//        pageHtml = paging.makePaging();
//        return pageHtml;
//    }


    @Transactional
    //Voc 글작성
    public ReturnMsg writeVoc(List<MultipartFile> files, Voc voc, HttpSession session) {
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        try {
            vRepo.save(voc);
            fileUpload(files, session, voc);
            rm.setFlag(true);
            rm.setMsg("등록되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
            rm.setFlag(false);
            rm.setMsg("예기치 않은 오류가 발생하였습니다. 다시 시도해주세요.");
        }
        return rm;
    }

    //Voc 파일 업로드
    public void fileUpload(List<MultipartFile> files, HttpSession session,
                           Voc voc) throws Exception {
        log.info("fileUpload()");
        String Path = session.getServletContext().getRealPath("/");
        Path += "upload/";
        File folder = new File(Path);
        //폴더 생성
        if (folder.isDirectory() == false) {
            folder.mkdir();
        }
        for (MultipartFile mf : files) {
            String oriname = mf.getOriginalFilename();
            if (oriname.equals("")) {
                return;
            }

            //파일정보 저장
            VocFile vf = new VocFile();
            vf.setVfid(voc);
            vf.setVforiname(oriname);

            String sysname = System.currentTimeMillis() + oriname.substring(oriname.indexOf("."));
            vf.setVfsysname(sysname);

            File file = new File(Path + sysname);
            mf.transferTo(file);
            vfRepo.save(vf);
        }
    }// 업로드 끝


    //파일 다운로드
    public ResponseEntity<Resource> fileDownload(VocFile vf, HttpSession session)
            throws IOException {
        String realpath = session.getServletContext().getRealPath("/");
        realpath += "upload/" + vf.getVfsysname();
        Path filePath = Paths.get(realpath);
        InputStreamResource fResource = new InputStreamResource(new FileInputStream(realpath));
        String fileName = URLEncoder.encode(vf.getVforiname(), "UTF-8");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .cacheControl(CacheControl.noCache())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(fResource);
    }


    //voc 문의글 상세내용 가져오기
//    public ModelAndView getVoc(long vocno) {
//        log.info("getVoc");
//        mv = new ModelAndView();
//
//        Voc voc = vRepo.findById(vocno).get();
//        mv.addObject("voc", voc);
//
//        //첨부파일 가져오기
//        List<VocFile> vfList = vfRepo.findByvfid(voc);
//        mv.addObject("vfList", vfList);
//        return mv;
//    }


    //전체 voc 리스트 출력(관리자용)
//    public List<Voc> getVocList(Integer PageNum, HttpSession session) {
//        log.info("getVocList");
//        mv = new ModelAndView();
//
//        if (PageNum == null) {
//            PageNum = 1;
//        }
//        int listCnt = 5;
//        Pageable pb = PageRequest.of((PageNum - 1), listCnt, Sort.Direction.DESC, "vocno");
//        Page<Voc> result = vRepo.findByVocnoGreaterThan(0L, pb);
//        List<Voc> vocList = result.getContent();
//        int totalPage = result.getTotalPages();
//
//        String paging = getPaging(PageNum, totalPage);
//        mv.addObject("vocList", vocList);
//        mv.addObject("paging", paging);
//
//        session.setAttribute("pageNum", PageNum);
//
//        return mv;
//    }

    //개인 voc 출력
//    public ModelAndView getMyVoc(Integer PageNum, HttpSession session,
//                                 String uid) {
//
//        mv = new ModelAndView();
//
//        if (PageNum == null) {
//            PageNum = 1;
//        }
//        int listCnt = 5;
//        Pageable pb = PageRequest.of((PageNum - 1), listCnt, Sort.Direction.DESC, "vocno");
//        Page<Voc> result = vRepo.findByVocnoGreaterThan(0L, pb);
//        List<Voc> vocList = result.getContent();
//        int totalPage = result.getTotalPages();
//
//        String paging = getPaging(PageNum, totalPage);
//        mv.addObject("vocList", vocList);
//        mv.addObject("paging", paging);
//
//        session.setAttribute("pageNum", PageNum);
//
//
//        // 개인 작성글 찾기
//        List<Voc> myList = new ArrayList<>();
//        Iterable<Voc> myIter = vRepo.findByVocuid();
//        for (Voc my : myIter){
//            myList.add(my);
//        }
//        mv.addObject("myList", myList);
//        mv.setViewName("myList");
//        return mv;
//    }


@Transactional
    //문의 수정
public ReturnMsg vocUpdate(List<MultipartFile> files, Voc voc, HttpSession session){
    log.info("vocUpdate()");
    ReturnMsg rm = new ReturnMsg();
    rm.setFlag(false);

    try {
        vRepo.save(voc);
        rm.setFlag(true);
        rm.setMsg("수정되었습니다.");
    }catch (Exception e){
        e.printStackTrace();
        rm.setFlag(false);
        rm.setMsg("예기치 않은 오류가 발생하였습니다. 다시 시도해주세요.");
    }
    return rm;
}

// voc 삭제
public ReturnMsg vocDelete(long vocno,HttpSession session){
    log.info("vocDelete()");
    ReturnMsg rm = new ReturnMsg();
    rm.setFlag(false);

   Voc voc = new Voc();
    voc.setVocno(vocno);

    String realPath = session.getServletContext().getRealPath("/");
    realPath += "upload/";

    List<VocFile> vfList = vfRepo.findByvfid(voc);

    try {
        for (VocFile vf : vfList){
            String delPath = realPath + vf.getVfsysname();
            File file = new File(delPath);

            if(file.exists()){
                file.delete();
            }
        }
        vfRepo.deleteByvfid(voc);
        vRepo.deleteById(vocno);
        rm.setFlag(true);
        rm.setMsg("삭제되었습니다.");
    }catch (Exception e){
        e.printStackTrace();
        rm.setFlag(false);
        rm.setMsg("예기치 않은 오류가 발생하였습니다. 다시 시도해주세요.");
    }
    return rm;
}

//voc 가져오기
public List<VocFile> getVoc(long vocno){
        log.info("getVoc()");
        Voc voc = vRepo.findById(vocno);

        List<VocFile> vfList = vfRepo.findByvfid(voc);
        return vfList;
}

    private String getPaging(Integer pageNum, int totalPage) {
        String pageHtml = null;
        int pageCnt = 5;
        String listName = "voc";

        PagingUtil paging = new PagingUtil(totalPage, pageNum, pageCnt, listName);
        pageHtml = paging.makePaging();

        return pageHtml;
    }


    //voc 리스트 출력
    public List<Voc> getVocList(Model model, Integer pageNum, HttpSession session){
        log.info("getVocList()");

        if(pageNum == null){
            pageNum = 1;
        }
        int listCnt = 5;
        Pageable pb = PageRequest.of((pageNum -1),listCnt, Sort.Direction.DESC,"vocno");

        Page<Voc> result = vRepo.findByVocnoGreaterThan(0L,pb);
        List<Voc> vList = result.getContent();
        int totalPage = result.getTotalPages();

        String paging = getPaging(pageNum,totalPage);

        model.addAttribute("vList", vList);
        model.addAttribute("paging", paging);

        session.setAttribute("pageNum",pageNum);

        return (List<Voc>) model;
    }


} // voc 서비스 끝
