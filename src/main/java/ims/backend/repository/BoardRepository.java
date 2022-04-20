package ims.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ims.backend.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    
}