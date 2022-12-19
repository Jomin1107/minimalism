package com.oracle.minimalism.jhService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oracle.minimalism.dto.MailDto;
import com.oracle.minimalism.dto.UserDto;
import com.oracle.minimalism.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;
    private JavaMailSender mailSender;
    
    // 임시비밀번호 메일로 전송될 때 내용
    public MailDto createMailAndChangePassword(String id , String email) {
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(email);
        dto.setTitle(id + "님의 Minimalism 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. Minimalism 임시비밀번호 안내 관련 이메일 입니다." + "[" + id + "]" + "님의 임시 비밀번호는 "
                + str + " 입니다.");
        updatePassword(str, id);
        return dto;
    }
    
    // 임시비밀번호가 전송됨과 동시에 user의 비밀번호를 임시비밀번호로 업데이트
    public void updatePassword(String str, String id) {
    	int update = 0;
        String password = encoder.encode(str);
        System.out.println("userid = " + id);
        UserDto user = userMapper.findbyId(id);
        System.out.println("user" + user);
        user.setPassword(password);
        update = userMapper.update1(user);
        System.out.println(update);
    }
    
    // 임시비밀번호는 영문+숫자
    public String getTempPassword() {
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
    
    // 임시비밀번호 보내는 발신자
    public void mailSend(MailDto mailDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom("minimalism2022@naver.com");
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        mailSender.send(message);
    }
}
