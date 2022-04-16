package ims.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Teacher {
    @Id
    private String userEmail; //이메일

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // 회원번호
    private String userName; //이름
    private String userBirth; // 생년월일
    private String userPhone; //핸드폰 번호
    private String className; //교육 과정
    private int accountNum; //계좌번호
    private String userPers; //사용자 권한
    private Date signUpDate; 
}
