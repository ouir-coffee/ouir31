package com.ouir.ouir31.service;

import com.ouir.ouir31.entity.Notice;
import com.ouir.ouir31.entity.ReturnMsg;
import com.ouir.ouir31.repository.NoticeRepository;
import lombok.extern.java.Log;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Log
public class NoticeService {

    @Autowired
    private NoticeRepository nRepo;

    public boolean noticeWrite(Notice notice){
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

    public ReturnMsg noticeUpdate(Notice notice){
        log.info("noticeUpdate()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        try {
            Notice n = nRepo.findById(notice.getNno()).get();
            if(n.equals(null)){
                rm.setFlag(false);
                rm.setMsg("게시글이 존재하지 않습니다.");
                return rm;
            }
            n.setNtitle(notice.getNtitle());
            n.setNcontents(notice.getNcontents());
            nRepo.save(n);
            rm.setFlag(true);
            rm.setMsg("수정되었습니다.");
        }catch (Exception e){
            e.printStackTrace();
            rm.setFlag(false);
            rm.setMsg("예기치 않은 오류가 발생하였습니다. 다시 시도해주세요.");
        }
        return rm;
    }//공지수정

    public ReturnMsg noticeDelete(long nno){
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
    }

    public List<Notice> noticeList(){
        log.info("noticeList()");
        return nRepo.findAll();
    }
}