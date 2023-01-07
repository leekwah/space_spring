package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.comment.CommentRepository;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment 댓글쓰기(String content, int imageId, int userId) {

        // Tip 가짜 객체를 만들기
        // 객체를 만들 때, id 값만 담아서 INSERT 할 수 있다.
        // 대신 return 시에 image 객체는 id 값만 가지고 있는 빈 객체를 return 받는다.
        // (이렇게 하지 않으면, Repository 에서 findById() 메서드를 사용해야한다. -> 아래의 user 처럼)
        Image image = new Image();
        image.setId(imageId);

        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
           throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
        });


        Comment comment = new Comment();

        comment.setContent(content);
        comment.setImage(image);
        comment.setUser(userEntity);

        return commentRepository.save(comment);
    }

    @Transactional
    public void 댓글삭제() {

    }

}
