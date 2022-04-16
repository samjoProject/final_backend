package ims.backend.model.Institute;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class RegInstitute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // 회원번호
    private String instituteName;
    private String businessNum;
}
