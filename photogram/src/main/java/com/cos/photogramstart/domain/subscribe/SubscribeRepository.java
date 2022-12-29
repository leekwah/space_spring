package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
    @Modifying // DB 를 변경하는 쿼리에는 @Modifying 이 있어야한다. (INSERT, DELETE, UPDATE 를 native query 로 작성시에)
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true) // 쿼리에서 : 은 들어가는 걸 의미한다.
    void mSubscribe(int fromUserId, int toUserId);

    @Modifying // DB 를 변경하는 쿼리에는 @Modifying 이 있어야한다.
    @Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId =:toUserId", nativeQuery = true) // 쿼리에서 : 은 들어가는 걸 의미한다.
    void mUnSubscribe(int fromUserId, int toUserId);

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId AND toUserId = :pageUserId", nativeQuery = true)
    int mSebscribeState(int principalId, int pageUserId); // 1은 구독이 된 상태

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
    int mSubScribeCount(int pageUserId); // 구독자 수를 알 수 있음

    // Subscribe 모델이 아닌 것은 쿼리 사용 불가능
}
