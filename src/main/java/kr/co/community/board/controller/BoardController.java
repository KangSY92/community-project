package kr.co.community.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.community.board.dto.BoardDTO;
import kr.co.community.board.service.impl.BoardServiceImpl;
import lombok.RequiredArgsConstructor;

/**
 * 게시판 관련 요청을 처리하는 컨트롤러 클래스입니다.
 * 글작성, 목록 조회 등의 기능을 담당합니다.
 */
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardServiceImpl boardService;
	
	/**
	 * 게시글 목록 페이지로 이동합니다.
	 * 
	 * @return 게시글 목록 화면의 뷰 이름 (board/board)
	 */
	@GetMapping("/list")
	public String boardList(Model model) {
		List<BoardDTO> boards = boardService.getList();
		model.addAttribute("boards", boards);
		return "board/board";
	}
	
	/**
	 * 게시글 작성 폼 페이지로 이동합니다.
	 * 
	 * @param model 뷰에 전달할 모델 객체
	 * @return 게시글 작성 폼 화면의 뷰 이름(board/write-post)
	 */
	@GetMapping("/create/form")
	public String createForm(Model model) {
		model.addAttribute("boardDTO", new BoardDTO());
		return "board/write-post";
	}
	
	/**
	 * 게시글을 등록하고 목록 페이지로 리다이렉트 합니다.
	 * 
	 * @param boardDTO 사용자가 입력한 게시글 정보
	 * @param sessionID 세션에 저장된 로그인 사용자 ID
	 * @return 게시글 목록 페이지로 리다이렉트 (redirect:/board/list)
	 */
	@PostMapping("/create")
	public String create(BoardDTO boardDTO,
						 @SessionAttribute("id") String sessionID) {
		boardDTO.setAuthor(sessionID);
		
		boardService.create(boardDTO, sessionID);
		return "redirect:/board/list";
	}
}
