package ims.backend.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ims.backend.model.*;
import ims.backend.model.Institute.*;
import ims.backend.repository.*;
import ims.backend.repository.Institute.*;
import ims.backend.service.SignUpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class SignUpController {
    
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    WaitInstituteRepository waitInstituteRepository;

    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    SignUpService SUS;

    @GetMapping("/checkDB")
    public Map<String, Object> checkPost(@RequestParam("userEmail") String userEmail) {
        System.out.println(userEmail);
        Student findStudent = new Student();
        Teacher findTeacher = new Teacher();
        Manager findManager = new Manager();

        findStudent = studentRepository.findByUserEmail(userEmail);
        findTeacher = teacherRepository.findByUserEmail(userEmail);
        findManager = managerRepository.findByUserEmail(userEmail);

        Map<String, Object> res = new HashMap<>();
        if (findStudent != null || findTeacher != null ||findManager !=null) {
            res.put("code", 201);
            res.put("msg", "데이터가 존재합니다. 로그인 창으로 이동합니다.");
            return res;
        } else {
            res.put("code", 200);
            res.put("msg", "데이터가 없습니다. 회원가입을 진행합니다.");
            return res;
        }
    }

    @GetMapping("/findclass")
    public List<Institute> findClass(){
        List<Institute> data = instituteRepository.findAll();
        return data;
    }
    
    @PostMapping("/checkins")
    public Map<String, Object> checkInstitute(String instituteName, String businessNum) {
        Map<String, Object> res = new HashMap<>();

        res = SUS.checkInstituteService(instituteName, businessNum);
        int i = (Integer) res.get("code");
        
        if (res.get("status") != null) {
            res.put("msg", "이미 인증이 된 기관입니다.");
            System.out.println("testing");
        } else {
            if (i == 200) {
                WaitInstitute wait = new WaitInstitute();
                wait.setInstituteName(instituteName);
                wait.setBusinessNum(businessNum);
                wait.setWaitingStatus(1);
                waitInstituteRepository.save(wait);
                System.out.println("저장한 데이터베이스 확인 : " + wait);
            } 
        }
        return res;
    }

    @PostMapping("/saveins")
    public Map<String, Object> saveInstitute(Institute institute){
        Map<String, Object> res = new HashMap<>();
        res = SUS.saveInstituteService(institute);
        waitInstituteRepository.deleteAllInBatch();
        return res;
    }

    @PostMapping("/signupmanager")
    public String signUpManager(Manager manager){
        Date now = new Date();
        manager.setSignUpDate(now);
        manager.setUserPers("3");
        System.out.println(manager);
        managerRepository.save(manager);
        return "회원가입이 완료되었습니다";
    }

    @PostMapping("/signupstudent")
    public String signUpStudent(Student student){
        Date now = new Date();
        student.setSignUpDate(now);
        student.setUserPers("0");
        System.out.println(student);
        studentRepository.save(student);
        return "회원가입이 완료되었습니다";
    }

    @PostMapping("/signupteacher")
    public String signUpTeacher(Teacher teacher){
        Date now = new Date();
        teacher.setSignUpDate(now);
        teacher.setUserPers("0");
        System.out.println(teacher);
        teacherRepository.save(teacher);
        return "회원가입이 완료되었습니다";
    }


}
