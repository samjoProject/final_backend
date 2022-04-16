package ims.backend.repository;

import ims.backend.model.Manager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    Manager findByUserEmail(String userEmail);
}
