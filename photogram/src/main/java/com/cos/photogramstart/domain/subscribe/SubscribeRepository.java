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
}
