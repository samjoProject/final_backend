package ims.backend.model;

import lombok.Data;

import java.util.Date;
// import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;


@Entity
@Data
// @CreatedDate
//     private LocalDateTime createdDate;

public class Calendar {
    @Id
    @GeneratedValue
    private Long id;
    // private String category;
    private String date;
    // private long userId;
    private String title;
    // private String content;
    
}
