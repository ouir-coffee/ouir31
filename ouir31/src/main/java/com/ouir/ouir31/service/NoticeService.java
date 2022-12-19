package com.ouir.ouir31.service;

import com.ouir.ouir31.entity.Notice;
import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.repository.NoticeRepository;
import com.ouir.ouir31.util.PagingUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Log
public class NoticeService {

    @Autowired
    private NoticeRepository nRepo;

    private ModelAndView mv;

    public boolean noticeWrite(Notice notice, HttpSession session){
        log.info("noticeWrite()");
        boolean result = false;

        try {
            nRepo.save(notice);
            result = true;
        } catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }//공지 등록

    public ReturnMsg noticeUpdate(Notice notice,HttpSession session){
        log.info("noticeUpdate()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        try {
            nRepo.save(notice);
            rm.setFlag(true);
            rm.setMsg("수정되었습니다.");
        }catch (Exception e){
            e.printStackTrace();
            rm.setFlag(false);
            rm.setMsg("예기치 않은 오류가 발생하였습니다. 다시 시도해주세요.");
        }
        return rm;
    }//공지수정

    public ReturnMsg noticeDelete(long nno,HttpSession session){
        log.info("noticeDelete()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        Notice notice = new Notice();
        notice.setNno(nno);

        try {
            nRepo.deleteById(nno);
            rm.setFlag(true);
            rm.setMsg("삭제되었습니다.");
        }catch (Exception e){
            e.printStackTrace();
            rm.setFlag(false);
            rm.setMsg("예기치 않은 오류가 발생하였습니다. 다시 시도해주세요.");
        }
        return rm;
    } //공지 삭제

    public List<Notice> noticeList(){
        log.info("noticeList()");
        return nRepo.findAll();
    } //공지 리스트


    private String getPaging(Integer pageNum, int totalPage) {
        String pageHtml = null;
        int pageCnt = 5;
        String listName = "notice";

        PagingUtil paging = new PagingUtil(totalPage, pageNum, pageCnt, listName);
        pageHtml = paging.makePaging();

        return pageHtml;
    }


    public ModelAndView getNotice(long nno){
        log.info("getNotice()");
        mv = new ModelAndView();

        Notice notice = nRepo.findById(nno).get();
        mv.addObject("notice",notice);

        return mv;
    }


    public ModelAndView getNoticeList(Integer pageNum, HttpSession session){
        log.info("getNoticeList()");
        mv = new ModelAndView();

        if(pageNum == null){
            pageNum = 1;
        }
        int listCnt = 5;
        Pageable pb = PageRequest.of((pageNum -1),listCnt, Sort.Direction.DESC,"nno");

        Page<Notice> result = nRepo.findByNnoGreaterThan(0L,pb);
        List<Notice> nList = result.getContent();
        int totalPage = result.getTotalPages();

        String paging = getPaging(pageNum,totalPage);
        mv.addObject("nList",nList);
        mv.addObject("paging",paging);

        session.setAttribute("pageNum",pageNum);

        return mv;
    }


}