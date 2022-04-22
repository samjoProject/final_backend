package ims.backend.controller;

import java.util.List;

// import com.example.demo.repository.CalendarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;


import ims.backend.model.Calendar;
import ims.backend.repository.CalendarRepository;
import ims.backend.service.CalendarService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://http://nanuri-client.s3-website-us-east-1.amazonaws.com")
public class CalendarController {

    // @Autowired
	// CalendarRepository calendarRepository ;
    @Autowired
    CalendarService calendarService;

    @GetMapping("/calendar")
    public List<Calendar> list() {
        System.out.println("1. Calendar test");
        // List<Calendar> calendar = calendarRepository.findAll();
        return calendarService.list();
    }

    @PostMapping("/calendar")
    public Calendar saveSchedule(@RequestBody Calendar calendar) {
       System.out.println("캘린더 일정등록 테스트");
        return calendarService.savePost(calendar);// templates/calendar.mustache -> 브라우저로 전송
    }

    @GetMapping("/calendar/{id}")
    public ResponseEntity<Calendar> read(@PathVariable("id") Long id, Model model) {
        ResponseEntity<Calendar> calendar = calendarService.getPost(id);
        model.addAttribute("post", calendar);
        System.out.println("일정 별 ID 보기 테스트");
        return calendar;
    }

    @DeleteMapping("/calendar")
    public void delete(@RequestBody Calendar calendar){
        System.out.println("삭제 테스트입니다.");
		calendarService.deleteCalendar(calendar.getId());
	} 
    
}
