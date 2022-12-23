package com.ouir.ouir31.service;

import com.ouir.ouir31.entity.Notice;
import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.NoticeFile;
import com.ouir.ouir31.repository.NoticeFileRepository;
import com.ouir.ouir31.repository.NoticeRepository;
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
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
@Log
public class NoticeService {

    @Autowired
    private NoticeRepository nRepo;

    @Autowired
    private NoticeFileRepository nfRepo;





    //공지 작성
    public ReturnMsg noticeWrite(Notice notice,List<MultipartFile> files, HttpSession session){
        log.info("noticeWrite()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        try {
            nRepo.save(notice);
            fileUpload(files,session,notice);
            rm.setFlag(true);
            rm.setMsg("등록되었습니다.");
        } catch (Exception e){
            e.printStackTrace();
            rm.setFlag(false);
            rm.setMsg("예기치 않은 오류가 발생하였습니다. 다시 시도해주세요.");
        }
        return rm;
    }


    //공지 수정
    public ReturnMsg noticeUpdate(Notice notice,List<MultipartFile> files, HttpSession session){
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
    }


    //공지 삭제 - 파일도 같이
    public ReturnMsg noticeDelete(long nno,HttpSession session){
        log.info("noticeDelete()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        Notice notice = new Notice();
        notice.setNno(nno);

        String realPath = session.getServletContext().getRealPath("/");
        realPath += "upload/";

        List<NoticeFile> nfList = nfRepo.findByNfnid(notice);

        try {
            for (NoticeFile nf : nfList){
                String delPath = realPath + nf.getNfsysname();
                File file = new File(delPath);

                if(file.exists()){
                    file.delete();
                }
            }
            nfRepo.deleteByNfnid(notice);
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

    //공지 가져오기
    public List<NoticeFile> getNotice(long nno){
        log.info("getNotice()");

            Notice notice = nRepo.findById(nno);

            List<NoticeFile> nfList = nfRepo.findByNfnid(notice);
            return nfList;
    }

    //페이징처리
    private String getPaging(Integer pageNum, int totalPage) {
        String pageHtml = null;
        int pageCnt = 5;
        String listName = "notice";

        PagingUtil paging = new PagingUtil(totalPage, pageNum, pageCnt, listName);
        pageHtml = paging.makePaging();

        return pageHtml;
    }


    //공지 리스트 출력
    public Model getNoticeList(Model model, Integer pageNum, HttpSession session){
        log.info("getNoticeList()");

        if(pageNum == null){
            pageNum = 1;
        }
        int listCnt = 5;
        Pageable pb = PageRequest.of((pageNum -1),listCnt, Sort.Direction.DESC,"nno");

        Page<Notice> result = nRepo.findByNnoGreaterThan(0L,pb);
        List<Notice> nList = result.getContent();
        int totalPage = result.getTotalPages();

        String paging = getPaging(pageNum,totalPage);

        model.addAttribute("nList",nList);
        model.addAttribute("paging",paging);

        session.setAttribute("pageNum",pageNum);

        return model; //////////수정해야함
    }

    //공지 파일 다운로드
    public ResponseEntity<Resource> fileDownload(NoticeFile noticeFile, HttpSession session)
            throws IOException {
        log.info("fileDownload()");

        String realpath = session.getServletContext().getRealPath("/");

        realpath += "upload/" + noticeFile.getNfsysname();

        InputStreamResource fResource = new InputStreamResource(new FileInputStream(realpath));

        String fileName = URLEncoder.encode(noticeFile.getNforiname(),"UTF-8");

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .cacheControl(CacheControl.noCache()).header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + fileName).body(fResource);
    }


    //파일 업로드
    private void fileUpload(List<MultipartFile> files,
                            HttpSession session, Notice notice) throws Exception {
        log.info("fileUpload()");

        String realPath = session.getServletContext().getRealPath("/");
        log.info("realpath : " + realPath);

        realPath += "upload/";
        File folder = new File(realPath);
        if (folder.isDirectory() == false) {
            folder.mkdir();
        }

        for (MultipartFile mf : files) {
            String oriname = mf.getOriginalFilename();
            if (oriname.equals("")) {
                return;
            }

           NoticeFile nf = new NoticeFile();
            nf.setNfnid(notice);
            nf.setNforiname(oriname);
            String sysname = System.currentTimeMillis()
                    + oriname.substring(oriname.lastIndexOf("."));
            nf.setNfsysname(sysname);

            File file = new File(realPath + sysname);

            mf.transferTo(file);
            System.out.println("realPath = " + realPath);
            nfRepo.save(nf);
        }
    }


}