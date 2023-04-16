package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {
    @BeforeEach // Test코드를 실행하기 앞서서 수행해야할 작업이 있다면, 여기에 코드를 작성하면 된다.
    void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql")); // db_schema.sql 이라는 파일을 ClassPath 에서 읽어와서 스크립트에 추가해준다.
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    void createTest() throws SQLException {
        UserDao userDao = new UserDao();

        userDao.create(new User("leekwah", "password", "name", "email")); // 인자 전달을 위해서

        User user = userDao.findByUserId("leekwah"); // 저장된 아이디에 대한 유저 정보를 가져온다.
        assertThat(user).isEqualTo(new User("leekwah", "password", "name", "email"));
    }
}
