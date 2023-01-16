package com.ouir.ouir31.service;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.User;
import com.ouir.ouir31.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
@Log
public class UserService {
    @Autowired
    private UserRepository uRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    String []sec = {};


    public ReturnMsg insertUser(User user) {
        log.info("insertUser()");
        ReturnMsg rm = new ReturnMsg();
        rm.setFlag(false);

        User udata = null;
        udata = uRepo.findByUid(user.getUid());
        User uemail = null;
        uemail = uRepo.findByUemail(user.getUemail());


        try{
            if(udata == null && uemail == null){
                user.setUpwd(encoder.encode(user.getUpwd()));
                uRepo.save(user);
                rm.setFlag(true);
            }else{
                rm.setFlag(false);
            }
        }catch (Exception e){
            e.printStackTrace();
            rm.setFlag(false);
        }
        return rm;
    }

    public Map<Object, Object> loginProc(User user, HttpSession session){
        log.info("loginProc()");
        Map<Object, Object> result = new HashMap<>();

        User uData = null;
        uData = uRepo.findByUid(user.getUid());

        try {

            if(uData != null || user.getUid().equals("Admin")){
                if(user.getUid().equals("Admin")){
                    adminlogin(user,session);

                    result.put("msg" , "관리자계정으로 로그인되었습니다");
                    result.put("uid","Admin");
                    result.put("success", true);

                    redirect();
                    return result;
                }

                String cPwd = uData.getUpwd();

                if(encoder.matches(user.getUpwd(),cPwd)){
                    session.setAttribute("loginName",uData.getUname());
                    session.setAttribute("loginId",uData.getUid());
                    log.info((String)session.getAttribute("loginName"));
                    session.setMaxInactiveInterval(30*60);
                    result.put("msg" , "로그인 성공");
                    result.put("success", true);
                    result.put("uid", uData.getUid());

                    return result;
                }else {
                    result.put("msg" , "비밀번호를 확인해주세요");
                    result.put("success", false);

                }
            }else {
                result.put("msg" , "아이디를 찾을 수 없습니다");
                result.put("success", false);

            }

        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg" , "로그인 실패");
            result.put("success", false);

        }

        return result;
    }

    public ResponseEntity<?> redirect() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/admin"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    public ReturnMsg userLogout(HttpSession session){
        ReturnMsg rm = new ReturnMsg();
        session.invalidate();
        rm.setFlag(true);
        rm.setMsg("로그아웃 되었습니다.");
        return rm;
    }

    public ReturnMsg userIdsearch(User uemail){
        ReturnMsg rm = new ReturnMsg();
        User sUemail = null;
        sUemail = uRepo.findByUemail(uemail.getUemail());
        log.info("sUemail");

        try {
            if (sUemail != null){
                String sId = sUemail.getUid();
                rm.setMsg(sId);
                rm.setFlag(true);

            }else {
                rm.setMsg("해당 email은 존재하지 않습니다.");
            }
        }catch (Exception e){
            rm.setMsg("병신아 코드잘못짰어");
        }

        return rm;
    }

    private void adminlogin(User uData,HttpSession session){
            if (uData.getUpwd().equals("Admin")) {
                uData.setUid("Admin");
                uData.setUname("관리자");
                session.setAttribute("loginName", uData.getUname());
                session.setAttribute("loginId", uData.getUid());
        }
    }

    public ReturnMsg userUpdate(User user, String check, HttpSession session) {
        ReturnMsg rm = new ReturnMsg();

        User upwd = uRepo.findByUid(session.getId());
        User ndata = user;
        ndata.setUid(session.getId());
        log.info(upwd.getUpwd());

        try {
            if (encoder.matches(check,upwd.getUpwd())) {
                String npwd = encoder.encode(ndata.getUpwd());
                ndata.setUpwd(npwd);
                uRepo.save(ndata);
                rm.setMsg("수정완료");
                rm.setFlag(true);
            } else {
                rm.setMsg("비밀번호를 확인해주세요");
            }
        } catch (Exception e) {
            rm.setMsg("이새끼 또 잘못짰어");
        }
        return rm;
    }

    public ReturnMsg userDelete(HttpSession session,String dCheck){
        User ddata = uRepo.findByUid(session.getId());
        ReturnMsg rm = new ReturnMsg();
        try {
            if(encoder.matches(dCheck,ddata.getUpwd())){
                uRepo.delete(ddata);
                session.invalidate();
                rm.setMsg("회원 탈퇴하였습니다.");
                rm.setFlag(true);
            }
        }catch (Exception e){
            rm.setMsg("회원 탈퇴 실패");
        }
        return rm;
    }
}
