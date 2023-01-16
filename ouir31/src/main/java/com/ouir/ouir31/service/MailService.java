package com.ouir.ouir31.service;

import com.ouir.ouir31.dto.Mail;
import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.User;
import com.ouir.ouir31.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Log
public class MailService {


    @Autowired
    private UserRepository uRepo;
    @Autowired
    private JavaMailSender mailSender;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private static final String FROM_ADDRESS = "ouir31@naver.com";
    public boolean userEmailCheck(String userEmail) {

        User user = null;
        user = uRepo.findByUemail(userEmail);

        try {
            if (user != null) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            return  false;
        }
    }

//////////////////////////////////////////////////////////////////////////

    public void mailSend(Mail maildto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(maildto.getAddress());
        message.setFrom(FROM_ADDRESS);
        message.setSubject(maildto.getTitle());
        message.setText(maildto.getMessage());

        mailSender.send(message);
    }
////////////////////////////////////////////////////////////////////

    public ReturnMsg createMailAndChangePassword(String uemail){
        ReturnMsg rm = new ReturnMsg();

        try {
            if (uRepo.findByUemail(uemail)==null){
                String str = getTempPassword();
                Mail maildto = new Mail();
                maildto.setAddress(uemail);
                maildto.setTitle("위어31 회원님의 회원가입 안내 이메일 입니다.");
                maildto.setMessage("안녕하세요. 위어31 회원님의 회원가입 안내 관련 이메일 입니다." + "회원님의 인증코드는 "
                        + str + " 입니다.");
                mailSend(maildto);
                rm.setMsg(str);
                rm.setFlag(true);
            }else {
                rm.setFlag(false);
            }
        }catch (Exception e){
            rm.setFlag(false);
        }
        return rm;
    }

    public ReturnMsg userpwdsearch(String email) {
        ReturnMsg rm = new ReturnMsg();

        User user = null;
        user = uRepo.findByUemail(email);

        try {
            if (uRepo.findByUemail(email)!=null){
                String str = getTempPassword();
                Mail mail = new Mail();
                mail.setAddress(email);
                mail.setTitle("위어31"+user.getUname()+"님의 개인정보 안내 메일입니다.");
                mail.setMessage("안녕하세요. 위어31"+user.getUname()+"님의 임시패스워드 안내 이메일 입니다." + "회원님의 인증코드는 "
                        + str + " 입니다.");
                mailSend(mail);
                newpasswordupdate(str,user.getUid());
                rm.setMsg("임시패스워드 발급 성공");
            }
        }catch (Exception e){
            rm.setMsg("실패하였습니다. 다시 시도하여 주십시오.");
        }
        return rm;
    }

    public void newpasswordupdate(String str, String uid){
        User user = uRepo.findByUid(uid);
        String pwd = encoder.encode(str);
        user.setUpwd(pwd);
        uRepo.save(user);
    }

    //임시 비밀번호 암호화 처리
    public static String getTempPassword() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public int checkMail(String uemail){
        return uRepo.countUserByUemail(uemail);
    }

}
