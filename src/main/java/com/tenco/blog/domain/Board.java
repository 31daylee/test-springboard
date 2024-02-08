package com.tenco.blog.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String author;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public Board(String title, String author, String content, LocalDateTime createdAt) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Board updateTitle(String title){
        this.title = title;
        return this;
    }
    public Board updateContent(String content){
        this.content = content;
        return this;
    }
}
