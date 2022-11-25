package com.kwah.b01.service;

import com.kwah.b01.dto.BoardDTO;
import com.kwah.b01.dto.PageRequestDTO;
import com.kwah.b01.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("Sample Title .....")
                .content("Sample Content .....")
                .writer("Sample Writer .....")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info("bno: " + bno);
    }

    @Test
    public void testModify() {
        // 변경에 필요한 데이터만 바꿈
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Updated Title 101 .....")
                .content("Updated Content 101 .....")
                .build();

        boardService.modify(boardDTO);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
    }
}
