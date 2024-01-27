package com.freestrokes.service;

import com.freestrokes.dto.request.board.BoardCommentRequestDto;
import com.freestrokes.dto.response.board.BoardCommentResponseDto;

/**
 * 게시글 댓글 요청을 위한 서비스 인터페이스
 */
public interface BoardCommentRequestService {

    /**
     * 게시글 댓글 등록
     * @param boardCommentRequestDto 게시글 댓글 정보
     * @return 등록한 게시글 댓글 정보
     */
    BoardCommentResponseDto postBoardComment(BoardCommentRequestDto boardCommentRequestDto);

    /**
     * 게시글 댓글 ID를 이용한 게시글 댓글 수정.
     * @param boardCommentId 게시글 댓글 ID
     * @param boardCommentRequestDto 게시글 댓글 정보
     * @return 수정한 게시글 댓글 정보
     */
    BoardCommentResponseDto putBoardComment(String boardCommentId, BoardCommentRequestDto boardCommentRequestDto);

    /**
     * 게시글 댓글 ID를 이용한 게시글 댓글 삭제
     * @param boardCommentId 게시글 댓글 ID
     */
    void deleteBoardComment(String boardCommentId);

}
