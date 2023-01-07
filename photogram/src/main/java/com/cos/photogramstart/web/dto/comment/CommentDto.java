package com.cos.photogramstart.web.dto.comment;

import lombok.Data;

@Data
public class CommentDto { // toEntity 필요 없음
    private String content;
    private int imageId;


}
