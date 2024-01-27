package com.freestrokes.dto.request.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentRequestDto {

    // TODO: DTO 설명과 사용 목적
    // Entity는 DB 테이블과 매핑되어 있기 때문에 request, response에 대한 DTO로 사용하면 안 됨.
    // request, response에 대한 DTO는 별도로 만들어서 사용해야 함.
    // View, DB 레이어별로 계층 분리를 철저하게 해주는 것이 권장 됨.
    // DTO 클래스 내부에 static inner class를 이용하여 용도에 맞는 DTO를 생성하여 사용.

    private String boardId;
    private String content;
    private String author;

}
