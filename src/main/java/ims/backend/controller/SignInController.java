package ims.backend.controller;

import java.util.HashMap;
import java.util.Map;

import ims.backend.model.Manager;
import ims.backend.model.Student;
import ims.backend.model.Teacher;
import ims.backend.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class SignInController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ManagerRepository managerRepository;
    
    @GetMapping("/checkDBSignIn")   
    public Map<String, Object> checkDBSignIn(@RequestParam("email") String email) {
        System.out.println(email);
        Student findStudent = new Student();
        Teacher findTeacher = new Teacher();
        Manager findManager = new Manager();

        findStudent = studentRepository.findByUserEmail(email);
        findTeacher = teacherRepository.findByUserEmail(email);
        findManager = managerRepository.findByUserEmail(email);

        Map<String, Object> res = new HashMap<>();
        if (findStudent != null || findTeacher != null ||findManager !=null) {
            res.put("code", 200);
            res.put("msg", "로그인이 완료되었습니다.");
            return res;
        } else {
            res.put("code", 201);
            res.put("msg", "데이터가 없습니다. 회원가입을 진행해주십시오.");
            return res;
        }
    }
}
