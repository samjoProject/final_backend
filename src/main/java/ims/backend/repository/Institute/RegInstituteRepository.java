package ims.backend.repository.Institute;

import ims.backend.model.Institute.RegInstitute;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegInstituteRepository extends JpaRepository<RegInstitute, Integer> {
    RegInstitute findByBusinessNum(String businessNum);
}
