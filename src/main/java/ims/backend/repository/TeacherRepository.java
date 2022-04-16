package ims.backend.repository;

import ims.backend.model.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByUserEmail(String userEmail);
}
