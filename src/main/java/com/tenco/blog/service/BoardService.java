package com.tenco.blog.service;

import com.tenco.blog.domain.Board;
import com.tenco.blog.dto.SaveFormDto;
import com.tenco.blog.dto.UpdateFormDto;
import com.tenco.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(SaveFormDto dto){
        Board board= boardRepository.save(dto.toEntity());
        return board.getId();
    }

    public Board getBoard(Long id){
        return boardRepository.getBoardById(id);
    }
    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
    @Transactional
    public void updateBoard(Long id, UpdateFormDto updateFormDto) {
        Board board = boardRepository.findById(id).orElse(null);
        board.updateTitle(updateFormDto.getTitle());
        board.updateContent(updateFormDto.getContent());
        boardRepository.save(board);

    }
    public Page<Board> getPaginatedBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

}
