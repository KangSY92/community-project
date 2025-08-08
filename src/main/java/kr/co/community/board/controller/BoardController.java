package kr.co.community.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import kr.co.community.board.dto.BoardDTO;
import kr.co.community.board.dto.BoardFileDTO;
import kr.co.community.board.dto.PageDTO;
import kr.co.community.board.dto.RequestBoardCreateDTO;
import kr.co.community.board.dto.RequestBoardDTO;
import kr.co.community.board.dto.ResponseListDTO;
import kr.co.community.board.service.BoardService;
import kr.co.community.board.util.Pagenation;
import kr.co.community.comment.dto.CommentDTO;
import kr.co.community.comment.service.CommentService;
import lombok.RequiredArgsConstructor;

/**
 * 게시판 관련 요청을 처리하는 컨트롤러 클래스입니다. 글작성, 목록 조회 등의 기능을 담당합니다.
 */
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	private final CommentService commentService;

	/**
	 * 게시글 목록 페이지로 이동합니다.
	 * 
	 * @return 게시글 목록 화면의 뷰 이름 (board/board)
	 */
	@GetMapping("/list")
	public String boardList(RequestBoardDTO requestBoardDTO,
							Model model) {

		int totalCount = boardService.getTotalCount(requestBoardDTO); // 전체 게시글 수
		int pageLimit = 5; // (버튼에) 보여질 페이지 수
		int boardLimit = 5; // 한 페이지에 들어갈 게시글 수

		Pagenation pagenation = new Pagenation();
		
		PageDTO pi = pagenation.getpageDTO(totalCount, requestBoardDTO.getCurrentPage(), pageLimit, boardLimit);
		
		ResponseListDTO boards = boardService.getList(pi, requestBoardDTO);
		model.addAttribute("boards", boards.getList());
		model.addAttribute("search", boards.getSearch());
		model.addAttribute("pi", boards.getPage());
		return "board/board";
	}

	/**
	 * 게시글 작성 폼 페이지로 이동합니다.
	 * 
	 * @param model 뷰에 전달할 모델 객체
	 * @return 게시글 작성 폼 화면의 뷰 이름(board/write-post)
	 */
	@GetMapping("/create/form")
	public String createForm(Model model, @SessionAttribute(value="id", required=false) String sessionId, RedirectAttributes redirectAttributes) {

		if (sessionId != null) {
			model.addAttribute("requestBoardCreateDTO", new RequestBoardCreateDTO());
			model.addAttribute("boardFileDTO", new BoardFileDTO());
			return "board/write-post";
		} else {
			redirectAttributes.addFlashAttribute("boardCreateMsg", "글쓰기엔 로그인이 필요합니다.");
			return "redirect:/";
		}
	}

	/**
	 * 게시글을 등록하고 목록 페이지로 리다이렉트 합니다.
	 * 
	 * @param boardDTO  사용자가 입력한 게시글 정보
	 * @param sessionID 세션에 저장된 로그인 사용자 ID
	 * @param file 업로드할 첨부파일
	 * @return 게시글 목록 페이지로 리다이렉트 (redirect:/board/list)
	 */
	@PostMapping("/create")
	public String create(RequestBoardCreateDTO requestBoardCreateDTO,
						 @SessionAttribute("id") String sessionID
						 ) {
		requestBoardCreateDTO.setAuthor(sessionID);

		boardService.create(requestBoardCreateDTO);
		return "redirect:/board/list";
	}

	/**
	 * 게시글 상세 페이지로 이동합니다.
	 * 
	 * @param boardId 조회할 게시글 ID
	 * @param commentDTO 댓글 정보를 담는 DTO객체로, 댓글 개수 정보를 설정합니다.
	 * @param model   뷰에 전달할 모델 객체
	 * @return 게시글 상세 화면의 뷰 이름(board/view-post)
	 */
	@GetMapping("/detail")
	public String detail(@RequestParam(name = "boardId") int boardId,
						 @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
						 CommentDTO commentDTO, Model model) {
		boardService.viewCountplus(boardId);
		BoardDTO result = boardService.detail(boardId);
		model.addAttribute("board", result);
		model.addAttribute(commentDTO);
				
		BoardFileDTO boardFileDTO = boardService.fileInfo(boardId);
		model.addAttribute("boardFileDTO", boardFileDTO);
				
		int totalCount = commentService.commentCount(boardId);
		int pageLimit = 5;
		int boardLimit = 5; 
		
		Pagenation pagenation = new Pagenation();
		
		PageDTO pi = pagenation.getpageDTO(totalCount, currentPage, pageLimit, boardLimit);
		
		model.addAttribute("pi", pi);
		List<CommentDTO> comment = commentService.getList(boardId, pi);
		model.addAttribute("comment", comment);
		
		int commentCount = commentService.commentCount(boardId);
		commentDTO.setCommentCount(commentCount);
		return "board/view-post";
	}

	/**
	 * 게시글 삭제를 처리합니다. 로그인한 사용자의 ID와 게시글 작성자가 일치할 경우 삭제를 수행합니다.
	 * 세션을 HttpSeeion 객체로 받아 세션에 로그인 정보가 없을 경우에도 적절히 처리합니다.
	 * 삭제 성공 또는 실패 메세지를 RedirectAttributesdp에 담아 목록 페이지로 리다이렉트합니다.
	 * 
	 * @param boardId            삭제할 게시글의 ID
	 * @param author             게시글 작성자 ID
	 * @param sessionId          HttpSession 객체를 통해 세션에 저장된 로그인 사용자 ID를 가져옵니다.
	 * @param redirectAttributes 리다이렉트시 사용자 메시지 전달용 객체
	 * @return 게시글 목록 페이지로 리다이렉트 (삭제 성공/실패/로그인필요에 관계 없이 redirect:/board/list)
	 */
	@PostMapping("/delete")
	public String delete(@RequestParam(name = "boardId") int boardId, @RequestParam(name = "author") String author,
			@SessionAttribute(value="id", required=false) String sessionId, RedirectAttributes redirectAttributes) {
		

		if (sessionId == null) {
			redirectAttributes.addFlashAttribute("boardDeleteMsg", "로그인이 필요합니다.");
			return "redirect:/board/list";
		}else if (sessionId.equals(author)) {
			boardService.delete(boardId, author, sessionId);
			redirectAttributes.addFlashAttribute("boardDeleteMsg", "삭제되었습니다.");
			return "redirect:/board/list";
		} else {
			redirectAttributes.addFlashAttribute("boardDeleteMsg", "다른 사용자 게시글은 삭제할 수 없습니다.");
			return "redirect:/board/list";
		}

	}

	/**
	 * 게시글 수정 폼 페이지로 이동합니다.
	 * 세션을 HttpSession 객체로 받아 로그인 여부를 체크하며,
	 * 로그인 하지 않은 경우나 다른 사용자의 게시글을 수정하려 할 때는 적절히 리다이렉트 처리 합니다.
	 * 
	 * @param boardId 수정할 게시글의 ID
	 * @param sessionID HttpSession 객체에서 로그인한 사용자 ID를 조회합니다.
	 * @param redirectAttributes 리다이렉트 시 사용자 메시지 전달용 객체
	 * @param model 뷰에 전달할 모델 객체
	 * @return 수정 폼 페이지 또는 권한이 없을 시 상세 페이지로 리다이렉트
	 */
	@GetMapping("/edit/form")
	public String editForm(@RequestParam(name = "boardId") int boardId, @SessionAttribute(value="id", required=false) String sessionId,
			RedirectAttributes redirectAttributes, Model model) {
		
		if(sessionId == null) {
			redirectAttributes.addFlashAttribute("boardEditMsg", "로그인이 필요합니다.");
			return "redirect:/board/detail?boardId=" + boardId;
		}
		
		BoardDTO result = boardService.detail(boardId);
		String author = result.getAuthor();
		
		if (author.equals(sessionId)) {
			model.addAttribute("board", result);
			BoardFileDTO boardFileDTO = boardService.fileInfo(boardId);
			model.addAttribute("boardFileDTO", boardFileDTO);
			return "board/edit-post";
		} else {
			redirectAttributes.addFlashAttribute("boardEditMsg", "다른 사용자 게시글은 수정 할 수 없습니다.");
			return "redirect:/board/detail?boardId=" + boardId;
		}

	}

	/**
	 * 게시글 수정을 처리합니다. 기존 파일 삭제 여부 및 새 파일 업로드 모두 처리됩니다.
	 * 
	 * @param boardId 게시글 ID
	 * @param boardDTO 수정된 게시글 정보
	 * @param boardFileDTO 수정된 파일 정보
	 * @param sessionId 로그인 사용자 ID
	 * @param fileDelete 파일 삭제 여부 (true: 삭제)
	 * @param file 새로 업로드할 파일
	 * @param redirectAttributes 리다이렉트 메시지 전달용 객체
	 * @return 수정된 게시글 상세 페이지로 리다이렉트
	 */
	@PostMapping("/edit")
	public String edit(@RequestParam(name = "boardId") int boardId, BoardDTO boardDTO, BoardFileDTO boardFileDTO,
					   @SessionAttribute(value="id", required=false) String sessionId,
					   @RequestParam(name = "fileDelete") boolean fileDelete,
					   @RequestParam(value = "file", required = false) MultipartFile file,
					   RedirectAttributes redirectAttributes) {
		
		
		
		if(sessionId == null) {
			redirectAttributes.addFlashAttribute("boardEditMsg", "로그인이 필요합니다.");
			return "redirect:/board/detail?boardId=" + boardId;
		}
		
		BoardDTO result = boardService.detail(boardId);
		String author = result.getAuthor();
		

	
		
		if (author.equals(sessionId)) {
			if(fileDelete) {
				boardService.fileDelete(boardId);
			}
			boardService.edit(boardDTO, boardId, boardFileDTO, file);
			return "redirect:/board/detail?boardId=" + boardId;
			
		} else {
			redirectAttributes.addFlashAttribute("boardEditMsg", "다른 사용자 게시글은 수정 할 수 없습니다.");
			return "redirect:/board/detail?boardId=" + boardId;
		}
		

		
	}
}
