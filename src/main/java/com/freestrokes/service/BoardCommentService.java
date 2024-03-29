package com.freestrokes.service;

import com.freestrokes.domain.board.BoardCommentEntity;
import com.freestrokes.domain.board.BoardEntity;
import com.freestrokes.dto.request.board.BoardCommentRequestDto;
import com.freestrokes.dto.response.board.BoardCommentResponseDto;
import com.freestrokes.repository.board.BoardCommentRepository;
import com.freestrokes.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
//@Profile("!dev")
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;

    /**
     * 게시글 댓글 등록
     * @param boardCommentRequestDto 게시글 댓글 정보
     * @return 등록한 게시글 댓글 정보
     */
    @Transactional
    public BoardCommentResponseDto postBoardComment(BoardCommentRequestDto boardCommentRequestDto) {

        // 게시글 조회
        BoardEntity findBoard = boardRepository.findById(boardCommentRequestDto.getBoardId()).orElseThrow(NoSuchElementException::new);

        // 게시글 댓글 생성
        BoardCommentEntity boardComment = BoardCommentEntity.builder()
            .board(findBoard)
            .content(boardCommentRequestDto.getContent())
            .author(boardCommentRequestDto.getAuthor())
            .build();

        // 게시글 댓글 등록
        boardCommentRepository.save(boardComment);

        return BoardCommentResponseDto.builder()
            .boardCommentId(boardComment.getBoardCommentId())
            .board(boardComment.getBoard())
            .content(boardComment.getContent())
            .author(boardComment.getAuthor())
            .build();

    }

    /**
     * 게시글 댓글 ID를 이용한 게시글 댓글 수정.
     * @param boardCommentId 게시글 댓글 ID
     * @param boardCommentRequestDto 게시글 댓글 정보
     * @return 수정한 게시글 댓글 정보
     */
    @Transactional
    public BoardCommentResponseDto putBoardComment(String boardCommentId, BoardCommentRequestDto boardCommentRequestDto) {

        // 게시글 댓글 조회
        BoardCommentEntity findBoardComment = boardCommentRepository.findById(boardCommentId).orElseThrow(NoSuchElementException::new);

        // TODO: CASE2) repository 메서드에 @EntityGraph 사용하여 연관 객체 조회
//        BoardCommentEntity findBoardComment = boardCommentRepository.findByBoardCommentId(boardCommentId).orElseThrow(NoSuchElementException::new);

        // 게시글 댓글 저장
        findBoardComment.updateBoardComment(
            boardCommentRequestDto.getContent(),
            boardCommentRequestDto.getAuthor()
        );

        // TODO: could not initialize proxy - no session
        // 연관관계의 FetchType LAZY로 설정된 객체는 조회시 바로 초기화되지 않고 proxy 객체로 처리 됨.
        // 이 상태에서 proxy 객체를 타입 캐스팅하려는 경우 could not initialize proxy - no session 에러가 발생하게 됨.
        // FetchType EAGER로 설정하면 해결되지만 N+1 문제가 발생할 수 있기 때문에 권장되지 않음.
        // CASE1) repository 메서드에 객체그래프 탐색을 위한 @EntityGraph를 사용
        // CASE2) proxy 객체의 값을 꺼내와서 DTO에 담아 반환

        return BoardCommentResponseDto.builder()
            .boardCommentId(findBoardComment.getBoardCommentId())
            // TODO: CASE1) proxy 객체의 값을 꺼내서 DTO에 담아 반환
            .board(
                BoardEntity.builder()
                    .boardId(findBoardComment.getBoard().getBoardId())
                    .title(findBoardComment.getBoard().getTitle())
                    .content(findBoardComment.getBoard().getContent())
                    .author(findBoardComment.getBoard().getAuthor())
                    .build()
            )
            // TODO: CASE2) repository 메서드에 @EntityGraph 사용하여 연관 객체 조회
//            .board(findBoardComment.getBoard())
            .content(findBoardComment.getContent())
            .author(findBoardComment.getAuthor())
            .build();

    }

    /**
     * 게시글 댓글 ID를 이용한 게시글 댓글 삭제
     * @param boardCommentId 게시글 댓글 ID
     */
    @Transactional
    public void deleteBoardComment(String boardCommentId) {
        // 게시글 댓글 삭제
        boardCommentRepository.deleteById(boardCommentId);
    }

}
