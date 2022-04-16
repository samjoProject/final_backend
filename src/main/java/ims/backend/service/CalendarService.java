package ims.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ims.backend.model.Calendar;
import ims.backend.repository.CalendarRepository;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    // 캘린더 목록 보여주기
    public List<Calendar> list() {
        System.out.println("2. Service : 일정 목록테스트");
        return calendarRepository.findAll();
    }

    // 2. CreateBoard
    public Calendar savePost(Calendar calendar) {
        System.out.println("2. Service :todo 일정 추가 테스트");
        return calendarRepository.save(calendar);

    }

    // @Transactional
    public ResponseEntity<Calendar> getPost(Long id) {
        Calendar calendar = calendarRepository.findById(id).get();
        System.out.println("일정 id 보기 테스트");
        return ResponseEntity.ok(calendar);
    }

    // 5. DeleteBoard
    public void deleteCalendar(Long id) {
        Calendar calendar = calendarRepository.findById(id).get();
        calendarRepository.delete(calendar);
        System.out.println("2. Service : Delete todo 삭제 테스트입니다.");
        // return ResponseEntity.ok(calendar);
    }

    // // 스케줄 보여주기
    // public List<Calendar> showCalendar(){
    // List<Calendar> calendarList = calendarRepository.findAll();
    // return calendarList;
    // }
}
