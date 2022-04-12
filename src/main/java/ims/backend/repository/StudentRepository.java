package ims.backend.repository;

import ims.backend.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByUserEmail(String userEmail);
}
