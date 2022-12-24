package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void 구독하기(int fromUserId, int toUserId) {
        // fromUser 과 같은 경우에는 Object 기 때문에, Native Query 를 쓰는 게 더 낫다.
        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }

    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId) { // 오류가 날 수가 없기 때문에, try catch 를 쓰지 않는다.
        // 만약에 구독 취소할 유저가 없을 경우에 추후에 하면 됨
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
