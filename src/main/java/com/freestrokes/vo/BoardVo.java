package com.freestrokes.vo;

import com.freestrokes.domain.board.BoardCommentEntity;

import java.util.List;

public interface BoardVo {

    String getBoardId();
    String getTitle();
    String getContent();
    String getAuthor();
    List<BoardCommentEntity> getBoardComments();

}
