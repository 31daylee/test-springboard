package com.tenco.blog.controller;

import com.tenco.blog.domain.Board;
import com.tenco.blog.dto.SaveFormDto;
import com.tenco.blog.dto.UpdateFormDto;
import com.tenco.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    @GetMapping("/")
    public String index(Model model, @PageableDefault( size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Board> boardPage = boardService.getPaginatedBoards(pageable);
        List<Board> boardList = boardPage.getContent();
        model.addAttribute("boardList", boardList);

        model.addAttribute("posts", boardPage);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", boardPage.hasNext());
        model.addAttribute("hasPrev", boardPage.hasPrevious());
        model.addAttribute("currentPage", boardPage.getNumber() + 1);
        model.addAttribute("totalPages", boardPage.getTotalPages());

        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "/";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Long id, Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        return "board/updateForm";
    }

    @PostMapping("/board/save")
    public String save(SaveFormDto dto){
        Long id = boardService.save(dto);
        log.info("Saved board id {}: ", id);
        return "redirect:saveForm";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute UpdateFormDto updateFormDto) {
        boardService.updateBoard(id, updateFormDto);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Long id){
        boardService.deleteBoard(id);
        return "redirect:/";
    }
}
