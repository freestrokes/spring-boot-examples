package com.freestrokes.dto.response.board;

import com.freestrokes.domain.board.BoardCommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {

    // TODO: DTO 설명과 사용 목적
    // Entity는 DB 테이블과 매핑되어 있기 때문에 request, response에 대한 DTO로 사용하면 안 됨.
    // request, response에 대한 DTO는 별도로 만들어서 사용해야 함.
    // View, DB 레이어별로 계층 분리를 철저하게 해주는 것이 권장 됨.
    // DTO 클래스 내부에 static inner class를 이용하여 용도에 맞는 DTO를 생성하여 사용.

    private String boardId;
    private String title;
    private String content;
    private String author;

    // TODO: @JsonIgnore
    // 양방향 연관관계 매핑을 한 경우 순환 참조가 발생할 수 있음
    // @JsonIgnore 어노테이션을 추가하여 해결.
//        @JsonIgnore
    private List<BoardCommentEntity> boardComments;

}
