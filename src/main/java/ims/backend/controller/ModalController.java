package ims.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModalController {

    @GetMapping("modalform")
    public String modalForm() {
        return "modal";
    }
    
}
