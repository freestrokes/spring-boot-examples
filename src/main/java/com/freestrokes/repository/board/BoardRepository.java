package com.freestrokes.repository.board;

import com.freestrokes.domain.board.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, String> {

    Page<BoardEntity> findAll(Pageable pageable);

}
