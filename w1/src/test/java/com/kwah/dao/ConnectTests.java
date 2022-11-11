package com.kwah.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTests {
    @Test
    public void testConnection() throws Exception {

        Class.forName("org.mariadb.jdbc.Driver");
        // JDBC 드라이버 클래스를 메모리상으로 로딩하는 역할

        Connection connection = DriverManager.getConnection(
        // Connection 인터페이스 타입의 변수로 데이터베이스와 네트워크 연결을 의미
        // DriverManager.getConnection()은
                "jdbc:mariadb://localhost:3306/webdb",
                "root",
                "1234"
        );

        Assertions.assertNotNull(connection);
        // 데이터베이스와 정상적으로 연결이 된다면 Connection 타입의 객체는 null이 아니라는 것을 확신한다는 의미

        connection.close();
    }

    @Test
    public void testHikariCP() throws Exception {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        config.setUsername("root");
        config.setPassword("1234");
        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);
        Connection connection = ds.getConnection();

        System.out.println(connection);

        connection.close();


    }
}
