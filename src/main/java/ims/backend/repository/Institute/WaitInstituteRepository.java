package ims.backend.repository.Institute;


import ims.backend.model.Institute.WaitInstitute;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitInstituteRepository extends JpaRepository<WaitInstitute, Integer> {
    WaitInstitute findByBusinessNum(String businessNum);
    WaitInstitute findByInstituteNameAndBusinessNum(String instituteName, String businessNum);
}
