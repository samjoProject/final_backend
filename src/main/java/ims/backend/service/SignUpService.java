package ims.backend.service;

import java.util.HashMap;
import java.util.Map;

import ims.backend.model.Institute.*;
import ims.backend.repository.Institute.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    RegInstituteRepository regInstituteRepository;

    @Autowired
    WaitInstituteRepository waitInstituteRepository;

    public Map<String, Object> saveInstituteService(Institute institute) {
        Map<String, Object> res = new HashMap<>(); //결과 출력 용 맵 선언
        System.out.println("1 : " + institute); //확인용 코드
        
        // 기관 명, 사업자 번호, 교육 과정 명 변수로 지정 
        String instituteName = institute.getInstituteName().toString();
        String businessNum = institute.getBusinessNum().toString();
        String className = institute.getClassName().toString(); 
        
        //입력받은 기관 명과 사업자 번호를 통해 인증을 받은 기관인지 체크
        WaitInstitute wait = new WaitInstitute();
        wait = waitInstituteRepository.findByInstituteNameAndBusinessNum(instituteName, businessNum);
        System.out.println("2 : " + wait); //확인용 코드
        
        if (wait != null) { //인증이 된 기관이면
            //인증된 데이터를 기반으로 변수 지정
            String waitIN = wait.getInstituteName().toString();
            String waitNum = wait.getBusinessNum().toString();

            //이미 저장된 교육기관, 교육 과정인지 확인
            Institute check = instituteRepository.findByInstituteNameAndBusinessNumAndClassName(instituteName,
            businessNum, className);

            if (check != null) { 
                res.put("code", 100);
                res.put("msg", "이미 등록되어있는 교육과정입니다.");
                return res; //이미 등록이 되어있을 경우 오류 메시지와 함께 메인화면으로
            } else {
                if (instituteName.equals(waitIN)) { // 기관명 체크
                    if (businessNum.equals(waitNum)) { // 사업자 번호 체크
                        if (className != null) { // 교육과정 명 체크
                            res.put("code", 200);
                            res.put("msg", "등록이 완료되었습니다.");
                            instituteRepository.save(institute); //등록 완료
                        } else {
                            res.put("msg", "과정 명이 비어있습니다."); //교육과정 명 공란
                        }
                    } else {
                        System.out.println("==========check============="); //확인용 코드
                        res.put("msg", "올바른 사업자 번호를 입력해주세요.."); //사업자 번호 체크 실패
                    }
                } else {
                    res.put("msg", "올바른 기관명을 입력해주세요."); //기관명 체크 실패
                }
            }
        } else {
            res.put("code", 404);
            res.put("msg", "인증되지 않은 기관입니다.");
        }
        return res;
    }

    public Map<String, Object> checkInstituteService(String instituteName, String businessNum) {
        Map<String, Object> res = new HashMap<>();
        RegInstitute test = new RegInstitute();
        WaitInstitute test2 = new WaitInstitute();

        test = regInstituteRepository.findByBusinessNum(businessNum);
        test2 = waitInstituteRepository.findByBusinessNum(businessNum);
        System.out.println(test); // 확인용
        System.out.println(test2); // 확인용
        if (test != null) {
            if (test.getInstituteName().equals(instituteName)) {
                if (test2 != null) {
                    res.put("code", 100);
                    res.put("msg", "이미 인증이 완료된 기관입니다.");
                    return res;
                } else {
                    res.put("code", 200);
                    res.put("msg", "인증이 완료되었습니다.");
                    return res;
                }
            } else {
                res.put("code", 201);
                res.put("msg", "이름이 다릅니다");
                return res;
            }
        } else {
            res.put("code", 202);
            res.put("msg", "사업자 등록번호가 다릅니다.");
            return res;
        }
    }

}
