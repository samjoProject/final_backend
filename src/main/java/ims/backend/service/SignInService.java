package ims.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ims.backend.model.*;
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
        String userName = new String();
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
            userName = findStudent.getUserName();
        } else if (findTeacher != null) {
            res.put("code", 200);
            res.put("msg", "로그인이 완료되었습니다.");
            userPers = findTeacher.getUserPers();
            className = findTeacher.getClassName();
            userName = findTeacher.getUserName();
        } else if (findManager != null) {
            res.put("code", 200);
            res.put("msg", "로그인이 완료되었습니다.");
            userPers = findManager.getUserPers();
            className = findManager.getClassName();
            userName = findManager.getUserName();
        } else {
            res.put("code", 201);
            res.put("msg", "데이터가 없습니다. 회원가입을 진행해주십시오.");
        }

        res.put("userPers", userPers);
        res.put("className", className);
        res.put("userName", userName);
        return res;
    }

    public Map<String, Object> getClassMemberService(String className){
        Map<String, Object> res = new HashMap<>();
        List<Student>dataStudent = studentRepository.findAll();
        List<Student>dataStudentOut = new ArrayList<Student>();
        List<Teacher>dataTeacher = teacherRepository.findAll();
        List<Teacher>dataTeacherOut = new ArrayList<Teacher>();
        int sizeStudent = dataStudent.size();
        int sizeTeacher = dataTeacher.size();

        for (int i = 0; i < sizeStudent; i++) {
            if(dataStudent.get(i).getClassName().equals(className)){
                dataStudentOut.add(dataStudent.get(i));
            }
        }
        for(int i=0; i< sizeTeacher; i++){
            if(dataTeacher.get(i).getClassName().equals(className)){
                dataTeacherOut.add(dataTeacher.get(i));
            }
        }
        res.put("student", dataStudentOut);
        res.put("teacher", dataTeacherOut);
        System.out.println(res);
        return res;
    }

    public String givePersStudentService(String userEmail){
        String res = new String();
        Student student = studentRepository.findByUserEmail(userEmail);
        System.out.println(student);
        student.setUserPers("1");
        studentRepository.save(student);
        res = student.getUserPers();
        return res;
    }
    public String givePersTeacherService(String userEmail){
        String res = new String();
        Teacher teacher = teacherRepository.findByUserEmail(userEmail);
        System.out.println(teacher);
        teacher.setUserPers("2");
        teacherRepository.save(teacher);
        res = teacher.getUserPers();
        return res;
    }

    public String deleteStudentService(String userEmail){
        String res = new String();
        Student student = studentRepository.findByUserEmail(userEmail);
        studentRepository.delete(student);
        res = "삭제 완료";
        return res;
    }

    public String deleteTeacherService(String userEmail){
        String res = new String();
        Teacher teacher = teacherRepository.findByUserEmail(userEmail);
        teacherRepository.delete(teacher);
        res = "삭제 완료";
        return res;
    }
}
