package com.cos.photogramstart.web.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// NotNull = Null 값 체크
// NotEmpty = 빈값이거나 null 체크
// NotBlank = 빈값이거나 null 체크, 공백까지

@Data
public class CommentDto { // toEntity 필요 없음
    @NotBlank
    private String content;

    @NotNull // 빈값 체크
    private int imageId;


}
