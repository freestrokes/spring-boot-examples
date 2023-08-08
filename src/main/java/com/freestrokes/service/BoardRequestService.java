package com.freestrokes.service;

import com.freestrokes.dto.BoardDto;

import java.util.List;

/**
 * 게시글 요청을 위한 서비스 인터페이스
 */
public interface BoardRequestService {

    /**
     * 게시글 목록을 조회.
     * @return 게시글 목록
     */
    List<BoardDto.ResponseDto> getBoards();

    /**
     * 게시글 등록.
     * @param boardRequestDto 게시글 정보
     * @return 등록한 게시글 목록
     */
    BoardDto.ResponseDto postBoard(BoardDto.RequestDto boardRequestDto);

    /**
     * 게시글 ID를 이용하여 게시글을 수정.
     * @param boardId 게시글 ID
     * @param boardRequestDto 게시글 정보
     * @return 수정한 게시글 정보
     */
    BoardDto.ResponseDto putBoard(String boardId, BoardDto.RequestDto boardRequestDto);

    /**
     * 게시글 ID를 이용하여 게시글을 삭제.
     * @param boardId 게시글 ID
     */
    void deleteBoard(String boardId);

}
