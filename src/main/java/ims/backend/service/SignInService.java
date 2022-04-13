package ims.backend.service;

import java.util.HashMap;
import java.util.Map;

import ims.backend.model.*;
import ims.backend.model.Institute.*;
import ims.backend.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ManagerRepository managerRepository;
    
    public Map<String, Object> findDBSignIn(String userEmail) {
        Map<String, Object> res = new HashMap<>(); //결과 출력 용 맵 선언
        
        String userPers = new String();
        String className = new String();
        Student findStudent = new Student();
        Teacher findTeacher = new Teacher();
        Manager findManager = new Manager();

        findStudent = studentRepository.findByUserEmail(userEmail);
        findTeacher = teacherRepository.findByUserEmail(userEmail);
        findManager = managerRepository.findByUserEmail(userEmail);

        if (findStudent != null) {
            res.put("code", 200);
            res.put("msg", "로그인이 완료되었습니다.");
            userPers = findStudent.getUserPers();
            className = findStudent.getClassName();
        } else if (findTeacher != null) {
            res.put("code", 200);
            res.put("msg", "로그인이 완료되었습니다.");
            userPers = findTeacher.getUserPers();
            className = findTeacher.getClassName();
        } else if (findManager != null) {
            res.put("code", 200);
            res.put("msg", "로그인이 완료되었습니다.");
            userPers = findManager.getUserPers();
            className = findManager.getClassName();
        } else {
            res.put("code", 201);
            res.put("msg", "데이터가 없습니다. 회원가입을 진행해주십시오.");
            return res;
        }

        res.put("userPers", userPers);
        res.put("className", className);
        return res;
    }

}
