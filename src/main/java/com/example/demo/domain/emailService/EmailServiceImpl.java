package com.example.demo.domain.emailService;

import com.example.demo.domain.dto.EmailComfirmDto;
import com.example.demo.domain.dto.SmsComfirmDto;
import com.example.demo.domain.mapper.EmailComfirmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService{


    @Autowired
    private EmailComfirmMapper emailComfirmMapper;

    @Autowired
    private JavaMailSender emailSender;

    public static final String ePw = createKey();

    private MimeMessage createMessage(String to)throws Exception{
        System.out.println("보내는 대상 : "+ to);
        System.out.println("인증 번호 : "+ePw);
        MimeMessage  message = emailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to);//보내는 대상
        message.setSubject("이메일 인증 테스트");//제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 환영합니다 대구요입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";

        // sms 인증번호 db에 저장
        EmailComfirmDto emailComfirmDto = new EmailComfirmDto();
        emailComfirmDto.setEmail(to);
        emailComfirmDto.setEmailComfirm(ePw);
        emailComfirmMapper.addEmailComfirm(emailComfirmDto);

        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("ahdzlrkwhgdk@gmail.com","대구요"));//보내는 사람

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 5; i++) { // 인증코드 5자리
            key.append((rnd.nextInt(10)));
        }

        return key.toString();
    }
    @Override
    public String sendSimpleMessage(String to)throws Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }

    public EmailComfirmDto checkEmail(String email){

        return emailComfirmMapper.selectOne(email);
    }

}
