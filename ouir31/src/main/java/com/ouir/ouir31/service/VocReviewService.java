package com.ouir.ouir31.service;


import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.Voc;
import com.ouir.ouir31.entity.VocReview;
import com.ouir.ouir31.repository.VocRepository;
import com.ouir.ouir31.repository.VocReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import javax.servlet.http.HttpSession;
import java.net.http.HttpHeaders;
import java.util.List;

@Service
@Log
@RequiredArgsConstructor

public class VocReviewService {
    @Autowired
    private VocReviewRepository vrRepo;

    @Autowired
    private VocRepository vRepo;

    @Transactional
    public ReturnMsg vocReviewSave(VocReview vocReview, HttpSession session){
        log.info("vocReviewSave()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        try {
            vrRepo.save(vocReview);
            log.info("vr_no : " + vocReview.getVrno());
            rm.setFlag(true);
            rm.setMsg("등록되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            rm.setFlag(false);
            rm.setMsg("다시 시도해주세요.");
        }
        return rm;
    } //댓글 등록

    @Transactional
    public ReturnMsg vocReviewUpdate(VocReview vocReview, HttpSession session){
        log.info("vocReviewUpdate");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        try {
            vrRepo.save(vocReview);
            rm.setFlag(true);
            rm.setMsg("수정되었습니다.");
        }catch (Exception e){
            e.printStackTrace();
            rm.setFlag(false);
            rm.setMsg("다시 시도해주세요.");
        }
        return rm;
    }//댓글 수정


    public ReturnMsg vocReviewDelete(Long vrno, HttpSession session){
        log.info("vocReviewDelete()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        try {
            vrRepo.deleteById(vrno);
            rm.setFlag(true);
            rm.setMsg("삭제되었습니다.");
        }catch (Exception e){
            rm.setFlag(false);
            rm.setMsg("다시 시도해주세요.");
        }
        return rm;
    }//댓글 삭제

    public List<VocReview> getVocReviewList(Long vocno){
        log.info("getVocReviewList()");

        Voc voc = vRepo.findById(vocno).get();
        List<VocReview> vocReviewList = vrRepo.findByVvrno(voc);

        return vocReviewList;
    } //댓글 리스트
}
