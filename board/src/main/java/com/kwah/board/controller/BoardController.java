package com.kwah.board.controller;

import com.kwah.board.entity.Board;
import com.kwah.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/write")
    public String boardWriteForm() {
        return "boardWrite";
    }
    @PostMapping("/writePro")
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception {
            boardService.write(board, file);

            model.addAttribute("message", "글 작성 완료");
            // 만약에 실패시에는 if 로 model.addAttribute("message", "글 작성 실패");
            model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/list")
    public String boardList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, String searchKeyword) {

        Page<Board> list = null;

        if (searchKeyword == null) { // searchKeyword 가 없을 때
            list = boardService.boardList(pageable);
        } else { // 있을 때
            list = boardService.boardSearchList(searchKeyword, pageable);
        }
        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1) ;
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", boardService.boardList(pageable));
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardList";
    }

    @GetMapping("/view")
    public String boardView(Model model, int id) {

        model.addAttribute("board", boardService.boardView(id));
        return "boardView";
    }

    @GetMapping("/delete")
    public String boardDelete(int id) {
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/modify/{id}")
    public String boardModify(@PathVariable("id") int id, Model model) {

        model.addAttribute("board", boardService.boardView(id));

        return "boardModify";
    }

    @PostMapping("/update/{id}")
    public String boardUpdate(@PathVariable("id") int id, Board board, MultipartFile file) throws Exception {

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setTitle(board.getContent());

        boardService.write(boardTemp, file);

        return "redirect:/board/list";
    }
}
