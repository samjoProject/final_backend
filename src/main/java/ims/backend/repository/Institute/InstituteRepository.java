package ims.backend.repository.Institute;

import java.util.List;

import ims.backend.model.Institute.Institute;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituteRepository extends JpaRepository<Institute, Integer> {
    Institute findByInstituteNameAndBusinessNumAndClassName(String instituteName, String businessNum, String classsName);
    List<Institute> findByClassNameContaining(String className);
}
