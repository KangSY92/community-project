package kr.co.community.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import kr.co.community.board.dto.BoardDTO;
import kr.co.community.board.dto.PageDTO;
import kr.co.community.board.service.impl.BoardServiceImpl;
import kr.co.community.board.util.Pagenation;
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
	
	private final Pagenation pagenation;
	/**
	 * 게시글 목록 페이지로 이동합니다.
	 * 
	 * @return 게시글 목록 화면의 뷰 이름 (board/board)
	 */
	@GetMapping("/list")
	public String boardList(BoardDTO boardDTO,
							@RequestParam(value="currentPage", defaultValue="1") int currentPage,
							Model model) {
		
		int totalCount = boardService.getTotalCount(boardDTO); // 전체 게시글 수
		int pageLimit = 5; // (버튼에) 보여질 페이지 수
		int boardLimit = 5; // 한 페이지에 들어갈 게시글 수
		
		PageDTO pi = pagenation.getpageDTO(totalCount, currentPage, pageLimit, boardLimit);

		List<BoardDTO> boards = boardService.getList(pi);
		model.addAttribute("boards", boards);
		model.addAttribute("pi", pi);
		return "board/board";
	}
	
	/**
	 * 게시글 작성 폼 페이지로 이동합니다.
	 * 
	 * @param model 뷰에 전달할 모델 객체
	 * @return 게시글 작성 폼 화면의 뷰 이름(board/write-post)
	 */
	@GetMapping("/create/form")
	public String createForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		
		Object status = session.getAttribute("id");
		if(status != null) {
			model.addAttribute("boardDTO", new BoardDTO());
			return "board/write-post";
		} else {
			 redirectAttributes.addFlashAttribute("boardCreateMsg", "글쓰기엔 로그인이 필요합니다.");
			return "redirect:/";
		}
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
