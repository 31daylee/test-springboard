package com.tenco.blog.dto;

import com.tenco.blog.domain.Board;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SaveFormDto {
    private String author;
    private String title;
    private String content;

    public Board toEntity(){
        return Board.builder()
                .author(author)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
