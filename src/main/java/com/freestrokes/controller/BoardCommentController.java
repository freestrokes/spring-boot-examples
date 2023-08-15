package com.freestrokes.controller;

import com.freestrokes.constants.PathConstants;
import com.freestrokes.dto.BoardCommentDto;
import com.freestrokes.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    /**
     * 게시글 댓글 등록
     * @param boardCommentRequestDto
     * @return
     * @throws Exception
     */
    @PostMapping(path = PathConstants.BOARD_COMMENTS, produces = "application/json")
    public ResponseEntity<BoardCommentDto.ResponseDto> postBoardComment(
        @RequestBody BoardCommentDto.RequestDto boardCommentRequestDto
    ) throws Exception {
        BoardCommentDto.ResponseDto result = boardCommentService.postBoardComment(boardCommentRequestDto);
        return new ResponseEntity<BoardCommentDto.ResponseDto>(result, HttpStatus.OK);
    }

    /**
     * 게시글 댓글 수정
     * @param boardCommentId
     * @param boardCommentRequestDto
     * @return
     * @throws Exception
     */
    @PutMapping(path = PathConstants.BOARD_COMMENT, produces = "application/json")
    public ResponseEntity<BoardCommentDto.ResponseDto> putBoardComment(
        @PathVariable String boardCommentId,
        @RequestBody BoardCommentDto.RequestDto boardCommentRequestDto
    ) throws Exception {
        BoardCommentDto.ResponseDto result = boardCommentService.putBoardComment(boardCommentId, boardCommentRequestDto);
        return new ResponseEntity<BoardCommentDto.ResponseDto>(result, HttpStatus.OK);
    }

    /**
     * 게시글 댓글 삭제
     * @param boardCommentId
     * @return
     * @throws Exception
     */
    @DeleteMapping(path = PathConstants.BOARD_COMMENT, produces = "application/json")
    public ResponseEntity<?> deleteBoardComment(
        @PathVariable String boardCommentId
    ) throws Exception {
        boardCommentService.deleteBoardComment(boardCommentId);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
