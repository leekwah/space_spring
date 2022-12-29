package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscirbe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em; // Repository 는 EntityManager 를 구현해서 만들어져 있는 구현체

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

    @Transactional(readOnly = true) // SELECT 만 할 것이기 때문에
    public List<SubscribeDto> 구독리스트(int principalId, int pageUserId) {

        // SubscribeRepository 에서 불가능하기에 직접 EntityManager 를 통해서 써야함

        // 1. 쿼리 준비방법
        StringBuffer sb = new StringBuffer();

        // Query 끝에 한 칸을 띄워야지 오류가 안남
        // 그리고, 세미콜론을 첨부하면 안된다.
        sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
        sb.append("if ((SELECT true From Subscribe WHERE fromUserId = ? AND toUserId = u.id), 1, 0) subscribeState, ");
        sb.append("if ((? = u.id), 1, 0) equalUserState ");
        sb.append("FROM user u INNER JOIN subscribe s ON u.id = s.toUserId ");
        sb.append("WHERE s.fromUserId = ?");

        // 첫번째와 두번째 ? 는 로그인한 아이디 principalId 이다.
        // 마지막 ?  에 들어와야하는 것은 pageUserId 이다.

        // 2. 쿼리 바인딩 (쿼리 완성 방법)
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        // 3. 쿼리 실행 방법 (qlrm 라이브러리 필요 -> DTO에 DB 결과를 매핑하기 위해서)
        JpaResultMapper result = new JpaResultMapper();
        // 한 건을 리턴 받을 때는 UniqueResult 지만, 아닐 때는 list() 를 사용한다.
        // list(쿼리, 어떻게 리턴할 지)
        List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);

        return subscribeDtos;
    }

}
