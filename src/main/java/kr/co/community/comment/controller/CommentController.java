package kr.co.community.comment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.community.comment.dto.CommentDTO;
import kr.co.community.comment.service.CommentService;
import lombok.RequiredArgsConstructor;

/**
 * 댓글 관련 요청을 처리하는 컨트롤러 클래스 입니다. 댓글 작성 등의 기능을 담당합니다.
 */
@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;

	/**
	 * 댓글을 등록하고, 해당 게시글 상세 페이지로 리다이렉트 합니다.
	 * 
	 * @param boardId 댓글이 달릴 게시글 ID
	 * @param commentDTO 사용자로부터 입력받은 댓글 데이터 DTO
	 * @param sessionID 로그인한 사용자의 ID (세션에서 추출)
	 * @param redirectAttributes 알림 메시지 전달용 객체
	 * @param model 뷰에 전달할 모델 객체
	 * @return 댓글이 등록된 게시글의 상세 페이지로 리다이렉트
	 */
	@PostMapping("/create")
	public String create(@RequestParam(name = "boardId") int boardId, CommentDTO commentDTO,
						 @SessionAttribute(value = "id", required = false) String sessionID,
						 RedirectAttributes redirectAttributes,
						 Model model) {
		
		if(sessionID == null) {
			redirectAttributes.addFlashAttribute("commentMsg", "로그인이 필요합니다.");
			return "redirect:/board/detail?boardId=" + boardId;
		} 
		
		commentDTO.setId(sessionID);
		commentDTO.setAuthor(sessionID);
		
		commentService.create(boardId, commentDTO);
		
			return "redirect:/board/detail?boardId=" + boardId;
		
	}
	
}
