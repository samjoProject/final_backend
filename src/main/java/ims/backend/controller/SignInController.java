package ims.backend.controller;

import java.util.HashMap;
import java.util.Map;

import ims.backend.service.SignInService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class SignInController {
    @Autowired
    SignInService SIS;

    @GetMapping("/checkDBSignIn")   
    public Map<String, Object> checkDBSignIn(@RequestParam("email") String userEmail) {
        Map<String, Object> res = new HashMap<>();
        res = SIS.findDBSignIn(userEmail);        
        return res;
    }
    
    @PostMapping("/getClassMember")
    public Map<String, Object> getClassMember(@RequestParam("className") String className){
        System.out.println("className :" +className);
        Map<String, Object> res = new HashMap<>();
        res = SIS.getClassMemberService(className);
        return res;
    }

    @PostMapping("/givePersStudent")
    public String givePersStudent(@RequestParam("userEmail") String userEmail){
        String res = new String();
        res = SIS.givePersStudentService(userEmail);
        return res;
    }
    @PostMapping("/givePersTeacher")
    public String givePersTeacher(@RequestParam("userEmail") String userEmail){
        String res = new String();
        res = SIS.givePersTeacherService(userEmail);
        return res;
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("userEmail") String userEmail){
        String res = new String();
        res = SIS.deleteStudentService(userEmail);
        return res;
    }

    @PostMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam("userEmail") String userEmail){
        String res = new String();
        res = SIS.deleteTeacherService(userEmail);
        return res;
    }
}   
