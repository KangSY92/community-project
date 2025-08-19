package kr.co.community.board.exception;

import java.io.Serial;

public class BoardException extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = 202507081110001L;

	/**
	 * 메시지와 원인 예외를 함께 전달하는 생성자입니다.
	 * @param message 예외에 대한 설명 메시지
	 * @param cause 예외 원인이 되는 하위 예외 객체
	 */
	public BoardException(String message, Throwable cause) {
		super(message, cause);
	}

}
