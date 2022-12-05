package com.kwah.b01.repository;

import com.kwah.b01.domain.Board;
import com.kwah.b01.domain.Reply;
import com.kwah.b01.dto.BoardListReplyCountDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        // 실제 DB에 있는 bno
        Long bno = 1L;

        Board board = Board.builder().bno(bno).build();

        Reply reply = Reply.builder()
                .board(board)
                .replyText("댓글 ..........")
                .replyer("replyer1")
                .build();

        replyRepository.save(reply);
    }

    @Transactional
    @Test
    public void testBoardReplies() {
        Long bno = 1L;

        Pageable pageable = PageRequest.of(0,10, Sort.by("rno").descending());

        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);

        result.getContent().forEach(reply -> {
            log.info(reply);
        });
    }

    @Test
    public void testSearchReplyCount() {
        String[] types = {"t", "c", "w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);

        // total pages
        log.info(result.getTotalPages());
        // page size
        log.info(result.getSize());
        // pageNumber
        log.info(result.getNumber());
        // prev next
        log.info(result.hasPrevious() + ": "+result.hasNext());

        result.getContent().forEach(board -> log.info(board));
    }
}
