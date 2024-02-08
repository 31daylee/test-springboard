package com.tenco.blog.dto;

import com.tenco.blog.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFormDto {
    private String title;
    private String content;

}
