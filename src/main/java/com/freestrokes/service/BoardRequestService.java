package com.freestrokes.service;

import com.freestrokes.dto.request.board.BoardRequestDto;
import com.freestrokes.dto.response.board.BoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 게시글 요청을 위한 서비스 인터페이스
 */
public interface BoardRequestService {

    /**
     * 게시글 목록을 조회.
     * @param pageable 페이징 정보
     * @return 게시글 목록
     */
    Page<BoardResponseDto> getBoards(Pageable pageable);

    /**
     * 게시글 등록.
     * @param boardRequestDto 게시글 정보
     * @return 등록한 게시글 목록
     */
    BoardResponseDto postBoard(BoardRequestDto boardRequestDto);

    /**
     * 게시글 ID를 이용하여 게시글을 수정.
     * @param boardId 게시글 ID
     * @param boardRequestDto 게시글 정보
     * @return 수정한 게시글 정보
     */
    BoardResponseDto putBoard(String boardId, BoardRequestDto boardRequestDto);

    /**
     * 게시글 ID를 이용하여 게시글을 삭제.
     * @param boardId 게시글 ID
     */
    void deleteBoard(String boardId);

}
