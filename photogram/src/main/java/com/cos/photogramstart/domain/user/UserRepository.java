package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// 어노테이션이 없어도 JpaRepository 를 상속하면 IoC 등록이 자동으로 된다.
public interface UserRepository extends JpaRepository<User, Integer> { // JpaRepository<Object, PK> 를 적는다.
    // JPA query method 사용 (Query Creation)
    User findByUsername(String username);
}
