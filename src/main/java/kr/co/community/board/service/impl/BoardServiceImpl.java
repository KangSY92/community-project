package kr.co.community.board.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import kr.co.community.board.domain.Board;
import kr.co.community.board.domain.BoardFile;
import kr.co.community.board.dto.BoardDTO;
import kr.co.community.board.dto.BoardFileDTO;
import kr.co.community.board.dto.PageDTO;
import kr.co.community.board.dto.RequestBoardCreateDTO;
import kr.co.community.board.dto.RequestBoardDTO;
import kr.co.community.board.dto.RequestBoardEditDTO;
import kr.co.community.board.dto.ResponseBoardDetailDTO;
import kr.co.community.board.dto.ResponseListDTO;
import kr.co.community.board.mapper.BoardMapper;
import kr.co.community.board.service.BoardService;
import kr.co.community.member.util.FileUpload;
import lombok.RequiredArgsConstructor;

/**
 * BoardService 인터페이스의 구현체로, 게시판 관련 비지니스 로직을 담당합니다. 게시글 작성, 조회, 삭제 기능을 제공합니다.
 */
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	/** 게시판 데이트베이스 처리를 위한 매퍼 객체 */
	private final BoardMapper boardMapper;

	private final FileUpload fileUpload;

	/**
	 * 게시글을 등록합니다.
	 * 파일이 있을 경우 함께 업로드 처리하고 DB에 정보 저장까지 수행합니다.
	 * 
	 * @param boardDTO  새로 작성할 게시글 데이터
	 * @param sessionID 현재 로그인한 사용자 세션 ID
	 */
	@Override
	public void create(RequestBoardCreateDTO requestBoardCreateDTO) {
		Board board = requestBoardCreateDTO.toBoard();
		boardMapper.create(board);
		
		try {
			MultipartFile file = requestBoardCreateDTO.getFile(); 
			if (file != null && !file.isEmpty()) {
				
				BoardFileDTO boardFileDTO = new BoardFileDTO();
				
				fileUpload.upload(file, boardFileDTO);
				boardMapper.fileUpload(board, boardFileDTO, file);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 페이징 정보에 맞는 게시글 목록을 조회합니다.
	 * 
	 * @param pi 페이지 및 페이징 관련 정보가 담긴 DTO
	 * @return 게시글 리스트
	 */
	@Override
	public ResponseListDTO getList(PageDTO pi, RequestBoardDTO requestBoardDTO) {
		Board board = requestBoardDTO.toBoard();
		List<BoardDTO> resultList = boardMapper.getList(pi, board);
		return ResponseListDTO.from(resultList, pi, requestBoardDTO.getText());
	}

	/**
	 * 게시글의 전체 개수를 조회합니다.
	 * 
	 * @param boardDTO 검색 조건 등이 포함될 수 있는 게시글 DTO
	 * @return 전체 게시글 수
	 */
	@Override
	public int getTotalCount(RequestBoardDTO requestBoardDTO) {
		Board board = requestBoardDTO.toBoard();
		return boardMapper.getTotalCount(board);
	}

	/**
	 * 게시글 상세정보를 조회합니다.
	 * 
	 * @param boardId 상세 조회할 게시글 ID
	 * @return 게시글 상세 데이터
	 */
	@Override
	public ResponseBoardDetailDTO detail(int boardId) {
		BoardDTO board = boardMapper.detail(boardId);
		return ResponseBoardDetailDTO.from(board);
	}

	/**
	 * 게시글의 조회수를 1 증가시킵니다.
	 * 
	 * @param boardId 조회수 증각 대상 게시글 ID
	 * @return 증가 처리된 결과값
	 */
	@Override
	public int viewCountplus(int boardId) {
		return boardMapper.viewCount(boardId);
	}

	/**
	 * 게시글을 삭제합니다.
	 * 작성자와 로그인 사용자가 일차할 경우에만 삭제합니다.
	 * 
	 * @param boardId 삭제할 게시글 ID
	 * @param author 게시글 작성자 ID
	 * @param sessionid 현재 로그인한 사용자 ID
	 */
	@Override
	public void delete(int boardId, String author, String sessionid) {
		boardMapper.delete(boardId);

	}

	/**
	 * 게시글을 수정합니다.
	 * 새 파일이 있을 경우 해당파일을 저장하고 DB에 업데이트합니다.
	 * 
	 * @param boardDTO 수정할 게시글 데이터
	 * @param boardId  수정 대상 게시글 ID
	 */
	@Override
	public void edit(RequestBoardEditDTO requestBoardEditDTO) {
			//BoardDTO boardDTO, int boardId, BoardFileDTO boardFileDTO, MultipartFile file
		Board board = requestBoardEditDTO.toBoard();
		BoardFile boardFile = requestBoardEditDTO.toBoardFile();
		boardMapper.edit(board);
		try {
			if (requestBoardEditDTO.getFile() != null && !requestBoardEditDTO.getFile().isEmpty()) {
				fileUpload.upload(requestBoardEditDTO.getFile(), boardFile);
				boardMapper.fileUpdate(board, board, requestBoardEditDTO.getFile());

				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 게시글에 첨부된 파일 정보를 조회합니다.
	 * @param boardId 조회할 게시글 ID
	 * @return 게시글에 첨부된 파일 정보 DTO
	 */
	@Override
	public BoardFileDTO fileInfo(int boardId) {
		return boardMapper.fileInfo(boardId);
		
	}

	/**
	 * 게시글에 첨부된 파일 정보를 삭제합니다.
	 * 
	 * @param boardId 파일 삭제 대상 게시글 ID
	 */
	@Override
	public void fileDelete(int boardId) {
		boardMapper.fileDelete(boardId);
	}

}
