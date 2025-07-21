package kr.co.community.comment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.community.comment.dto.CommentDTO;
import kr.co.community.comment.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentServiceImpl commentService;

	@PostMapping("/create")
	public String create(@RequestParam(name = "boardId") int boardId, CommentDTO commentDTO,
						 @SessionAttribute("id") String sessionID,
						 Model model) {
		System.out.println(boardId);
		commentDTO.setAuthor(sessionID);
		commentDTO.setId(sessionID);
		commentService.create(boardId, commentDTO);
		return "redirect:/board/detail?boardId=" + boardId;
	}
}
