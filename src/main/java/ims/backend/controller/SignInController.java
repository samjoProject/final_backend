package ims.backend.controller;

import java.util.HashMap;
import java.util.Map;

import ims.backend.service.SignInService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
