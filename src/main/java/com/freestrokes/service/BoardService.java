package com.freestrokes.service;

import com.freestrokes.domain.board.BoardCommentEntity;
import com.freestrokes.domain.board.BoardEntity;
import com.freestrokes.dto.request.board.BoardRequestDto;
import com.freestrokes.dto.response.board.BoardResponseDto;
import com.freestrokes.repository.board.BoardRepository;
//import com.freestrokes.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
//@Profile("!dev")
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // TODO: proxy 객체
    // 연관관계 매핑된 객체 조회시 hibernate interceptor에 의해 proxy 객체가 생성되는 경우가 있음
    // proxy 객체의 필드 중 하나를 get 해오면 영속 상태의 객체로 매핑됨.

    // TODO: @Transactional(readOnly = true)
    // 서비스 계층에서 트랙잭션을 시작하면 repository 계층에서도 해당 트랜잭션을 전파 받아서 사용.
    // 지연 로딩 시점까지 세션을 유지하여 LazyInitializationException 해결 가능.
    // 아래와 같이 세션 유지가 필요한 메서드에 @Transactional(readOnly = true) 붙여줌.
    // but, RDB에도 연관관계가 설정되어 있지 않은 경우엔 LazyInitializationException 계속 발생할 수 있음
    // 이러한 경우엔 @ManyToOne 설정해준 쪽에서 @EntityGraph 사용하여 해결.

    // TODO: interface + implements 구조
    // 반드시 필요한 경우에만 interface를 생성하여 구조화해준다.
    // 모든 클래스에 대해 interface를 만들어둘 필요는 없다.

    /**
     * 게시글 목록을 조회
     * @param pageable 페이징 정보
     * @return 게시글 목록
     */
    @Transactional(readOnly = true)
    public Page<BoardResponseDto> getBoards(Pageable pageable) {

        // TODO
        // Page 객체의 타입을 인터페이스로 사용할 수 없는 이유 찾아보기
//        List<BoardVo> boardList = boardRepository.getBoardList();

        Page<BoardEntity> findBoards = boardRepository.findAll(pageable);
        List<BoardResponseDto> boardsResponseDto = new ArrayList<>();
//
//        // 조회한 게시글 목록에 대한 DTO 변환
        findBoards.getContent().forEach(board -> {
            boardsResponseDto.add(
                BoardResponseDto.builder()
                    .boardId(board.getBoardId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .author(board.getAuthor())
                    .boardComments(
                        board.getBoardComments().stream().map(boardComment -> {
                            return BoardCommentEntity.builder()
                                .boardCommentId(boardComment.getBoardCommentId())
                                .board(board)
                                .content(boardComment.getContent())
                                .author(boardComment.getAuthor())
                                .build();
                        }).collect(Collectors.toList())
                    )
                    .build()
            );
        });

        // TODO: CASE1) 1:N 양방향 매핑 조회 후 DTO 변환 (stream 이용한 방법)
        // 게시글 조회
//        List<BoardResponseDto> boardsResponseDto = boardRepository.findAll(pageable)
//            .stream()
//            .map(board -> {
//                return BoardResponseDto.builder()
//                    .boardId(board.getBoardId())
//                    .title(board.getTitle())
//                    .content(board.getContent())
//                    .author(board.getAuthor())
//                    .boardComments(
//                        board.getBoardComments().stream().map(boardComment -> {
//                            return BoardCommentEntity.builder()
//                                .boardCommentId(boardComment.getBoardCommentId())
//                                .board(board)
//                                .content(boardComment.getContent())
//                                .author(boardComment.getAuthor())
//                                .build();
//                        }).collect(Collectors.toList())
//                    )
//                    .build();
//            })
//            .collect(Collectors.toList());

        //TODO: CASE2) 1:N 양방향 매핑 조회 후 DTO 변환 (for문 이용한 방법)
//        List<BoardEntity> findBoards = boardRepository.findAll();
//        List<BoardResponseDto> boardsResponseDto = new ArrayList<>();
//
//        for (BoardEntity board : findBoards) {
//            List<BoardCommentEntity> boardComments = new ArrayList<>();
//
//            // Board Comment DTO
//            if (board.getBoardComments().size() > 0) {
//                board.getBoardComments().stream().forEach(boardComment -> {
//                    boardComments.add(
//                        BoardCommentEntity.builder()
//                            .boardCommentId(boardComment.getBoardCommentId())
//                            .board(board)
//                            .content(boardComment.getContent())
//                            .author(boardComment.getAuthor())
//                            .build()
//                    );
//                });
//            }
//
//            // Board DTO
//            boardsResponseDto.add(
//                BoardResponseDto.builder()
//                    .boardId(board.getBoardId())
//                    .title(board.getTitle())
//                    .content(board.getContent())
//                    .author(board.getAuthor())
//                    .boardComments(boardComments)
//                    .build()
//            );
//        }
//
//        return boardsResponseDto;

        return new PageImpl<>(boardsResponseDto, pageable, findBoards.getTotalElements());

    }

    /**
     * 게시글 등록
     * @param boardRequestDto 게시글 정보
     * @return 등록한 게시글 정보
     */
    @Transactional
    public BoardResponseDto postBoard(BoardRequestDto boardRequestDto) {

        // TODO: Optional을 이용한 중복 체크가 필요한 경우
//        Optional<BoardEntity> existBoard = boardRepository.findByTitle(boardRequestDto.getTitle());
//        existBoard.ifPresent(item -> {
//            try {
//                throw new Exception();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });

        // 게시글 생성
        BoardEntity board = BoardEntity.builder()
            .title(boardRequestDto.getTitle())
            .content(boardRequestDto.getContent())
            .author(boardRequestDto.getAuthor())
            .build();

        // 게시글 저장
        boardRepository.save(board);

        return BoardResponseDto.builder()
            .boardId(board.getBoardId())
            .title(board.getTitle())
            .content(board.getContent())
            .author(board.getAuthor())
            .build();

    }

    /**
     * 게시글 ID를 이용하여 게시글을 수정.
     * @param boardId 게시글 ID
     * @param boardRequestDto 게시글 정보
     * @return 수정한 게시글 정보
     */
    @Transactional
    public BoardResponseDto putBoard(String boardId, BoardRequestDto boardRequestDto) {

        // 게시글 조회
        BoardEntity findBoard = boardRepository.findById(boardId).orElseThrow(NoSuchElementException::new);

        // TODO: @Transactional 어노테이션 사용하여 update 하려는 경우
        // @Transactional 어노테이션을 명시하여 repository save() 호출 없이 저장 가능.
        // board builder() 생성 없이 findBoard > updateBoard() 호출하는 것 만으로도 저장 가능
        findBoard.updateBoard(
            boardRequestDto.getTitle(),
            boardRequestDto.getContent(),
            boardRequestDto.getAuthor()
        );

        // TODO: @Transactional 어노테이션이 없이 update 하려는 경우
//        BoardEntity board = BoardEntity.builder()
//            .title(boardRequestDto.getTitle())
//            .content(boardRequestDto.getContent())
//            .author(boardRequestDto.getAuthor())
//            .build();
//        findBoard.updateBoard(
//            board.getTitle(),
//            board.getContent(),
//            board.getAuthor()
//        );
//        boardRepository.save(findBoard);

        return BoardResponseDto.builder()
            .boardId(findBoard.getBoardId())
            .title(findBoard.getTitle())
            .content(findBoard.getContent())
            .author(findBoard.getAuthor())
            .build();

    }

    /**
     * 게시글 ID를 이용하여 게시글을 삭제.
     * @param boardId 게시글 ID
     */
    @Transactional
    public void deleteBoard(String boardId) {
        // 게시글 삭제
        boardRepository.deleteById(boardId);
    }

}
