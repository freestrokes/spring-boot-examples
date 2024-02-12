package com.freestrokes.repository.board;

import com.freestrokes.domain.board.BoardEntity;
 //import com.freestrokes.vo.BoardVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, String> {

    // TODO
    // VO 인터페이스는 Page 객체의 제네릭으로 사용할 수 없는데
    // 이 부분에 대해 찾아보기
    Page<BoardEntity> findAll(Pageable pageable);

    // TODO
//    @Query("SELECT be.boardId FROM BoardEntity be")
//    List<BoardVo> getBoardList();

}
