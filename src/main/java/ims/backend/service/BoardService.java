package ims.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ims.backend.model.Board;
import ims.backend.repository.BoardRepository;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    
    // 1. ListBoard
    public List<Board> list() {
        System.out.println("2. Service : ListBoard 목록테스트");
        return boardRepository.findAll();
    }

    // 2. CreateBoard
    public Board savePost(Board board) {
        System.out.println("2. Service : CreateBoard 글쓰기테스트");
        return boardRepository.save(board);
    }

    // 3. ReadBoard
    // @Transactional
    public ResponseEntity<Board> getPost(Long id) {
        Board board = boardRepository.findById(id).get();
        int cnt = board.getCounts(); // 조회수
        board.setCounts(cnt+1);
        Board counts = boardRepository.save(board);
        System.out.println("2. Service : ReadBoard 상세보기테스트");
        return ResponseEntity.ok(counts);
    }


    // 4. UpdateBoard
    public ResponseEntity<Board> updateBoard(Long id, Board updateBoard) {
        Board board = boardRepository.findById(id).get();
        board.setTitle(updateBoard.getTitle());
		board.setContent(updateBoard.getContent());
        Board endUpdatedBoard = boardRepository.save(board);

        return ResponseEntity.ok(endUpdatedBoard);
    }

    // 5. DeleteBoard
    public ResponseEntity<Board> deleteBoard(Long id) {
        Board board = boardRepository.findById(id).get();
        boardRepository.delete(board);
        System.out.println("2. Service : DeleteBoard 삭제 테스트입니다.");
        return ResponseEntity.ok(board);
    }

    

}
