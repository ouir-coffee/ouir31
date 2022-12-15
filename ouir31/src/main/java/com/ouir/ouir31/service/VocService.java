package com.ouir.ouir31.service;

import com.ouir.ouir31.entity.User;
import com.ouir.ouir31.entity.Voc;
import com.ouir.ouir31.entity.VocFile;
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


    private ModelAndView mv = new ModelAndView();

    //페이징
    private String getPaging(int pageNum, int totalPage) {
        String pageHtml = null;
        int pageCnt = 5;
        String listName = "?";
        PagingUtil paging = new PagingUtil(totalPage, pageNum, pageCnt, listName);
        pageHtml = paging.makePaging();
        return pageHtml;
    }


    @Transactional
    //Voc 글작성
    public String insertVoc(List<MultipartFile> files, Voc voc, HttpSession session, RedirectAttributes rttr) {
        String view = null;
        String msg = null;
        try {
            vRepo.save(voc);
            fileUpload(files, session, voc);
            msg = "저장완료";
            view = "redirect:list";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "실패";
            view = "redirect:list";
        }
        rttr.addFlashAttribute("msg", msg);
        return view;
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
    public ModelAndView getVoc(long vocno) {
        log.info("getVoc");
        mv = new ModelAndView();

        Voc voc = vRepo.findById(vocno).get();
        mv.addObject("voc", voc);

        //첨부파일 가져오기
        List<VocFile> vfList = vfRepo.findByvfid(voc);
        mv.addObject("vfList", vfList);
        return mv;
    }


    //전체 voc 리스트 출력(관리자용)
    public ModelAndView getVocList(Integer PageNum, HttpSession session) {
        log.info("getVocList");
        mv = new ModelAndView();

        if (PageNum == null) {
            PageNum = 1;
        }
        int listCnt = 5;
        Pageable pb = PageRequest.of((PageNum - 1), listCnt, Sort.Direction.DESC, "vocno");
        Page<Voc> result = vRepo.findByVocnoGreaterThan(0L, pb);
        List<Voc> vocList = result.getContent();
        int totalPage = result.getTotalPages();

        String paging = getPaging(PageNum, totalPage);
        mv.addObject("vocList", vocList);
        mv.addObject("paging", paging);

        session.setAttribute("pageNum", PageNum);

        return mv;
    }

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
    public String vocUpdate(List<MultipartFile> files, Voc voc, HttpSession session, RedirectAttributes rttr) {
        String msg = null;
        String view = null;

        try {
            vRepo.save(voc);
            fileUpload(files, session, voc);
            msg = "수정성공";
            view = "redirect:detail?bnum=" + voc.getVocno();

        } catch (Exception e) {
            e.printStackTrace();
        }
        rttr.addFlashAttribute("msg", msg);
        return view;
    }

// voc 삭제
    @Transactional
    public String vocDelete(long vocno, HttpSession session, RedirectAttributes rttr) {
    String msg = null;
    String view = null;

     Voc voc = new Voc();
     voc.setVocno(vocno);
     List<VocFile> vfList = vfRepo.findByvfid(voc);

     String realPath = session.getServletContext().getRealPath("/");
     realPath += "upload/";

     try {
         for (VocFile vf : vfList){
             String delPath = realPath + vf.getVfsysname();
             File file = new File(delPath);
            //파일이 존재하면 삭제
             if (file.exists()){
                 file.delete();
             }
         }
         //파일정보 삭제
         vfRepo.deleteByvfid(voc);
        //글 삭제
         vRepo.deleteById(vocno);
         msg = "삭제성공";
         view = "redirect:list";
     } catch (Exception e) {
         e.printStackTrace();
         msg = "삭제실패";
         view = "redirect:list";
     }
     rttr.addFlashAttribute("msg", msg);

     return view;
    }


} // voc 서비스 끝
