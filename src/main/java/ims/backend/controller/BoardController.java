package ims.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ims.backend.model.Board;
import ims.backend.service.BoardService;


@RestController
@CrossOrigin(origins = "http://nanuri-client.s3-website-us-east-1.amazonaws.com")
@RequestMapping("/api")
public class BoardController {
    
    @Autowired
    private BoardService boardService;

    // 1. ListBoard 글목록
    @GetMapping("/board")
    public List<Board> list() {
        return boardService.list();     
    }

    // 2. CreateBoard 글쓰기
    @PostMapping("/post")
    public Board create(@RequestBody Board board) {
        return boardService.savePost(board);
    }
    
    // 3. ReadBoard 상세보기
    @GetMapping("/post/{id}")
    public ResponseEntity<Board> read(@PathVariable("id") Long id, Model model) {
        ResponseEntity<Board> board = boardService.getPost(id);
        model.addAttribute("post", board);
        System.out.println("1. Controller : ReadBoard 상세보기 테스트");
        return board;
    }

    // 4. UpdateBoard 수정하기
    @PutMapping("/post/{id}")
    public ResponseEntity<Board> update(@PathVariable("id") Long id, @RequestBody Board board){
		return boardService.updateBoard(id, board);
	}

    // 5. DeleteBoard 삭제하기
    @DeleteMapping("/post/{id}")
    public ResponseEntity<Board> delete(@PathVariable("id") Long id){
        System.out.println("1. Controller : DeleteBoard 삭제 테스트입니다.");
		return boardService.deleteBoard(id);
	} 

}

