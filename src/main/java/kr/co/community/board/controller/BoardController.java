package kr.co.community.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.community.board.dto.BoardDTO;
import kr.co.community.board.service.impl.BoardServiceImpl;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardServiceImpl boardService;
	
	@GetMapping("/list")
	public String boardList() {
		return "board/board";
	}
	
	@GetMapping("/create/form")
	public String createForm(Model model) {
		model.addAttribute("boardDTO", new BoardDTO());
		return "board/write-post";
	}
	
	@PostMapping("/create")
	public String create(BoardDTO boardDTO,
						 @SessionAttribute("id") String sessionID) {
		System.out.println(sessionID);
		boardDTO.setId(sessionID);
		
		boardService.create(boardDTO, sessionID);
		return "redirect:/board/list";
	}
}
