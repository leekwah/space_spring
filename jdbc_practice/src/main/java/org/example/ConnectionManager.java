package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static final String ORG_H_2_DRIVER = "org.h2.Driver";
    public static final String DB_DRIVER = ORG_H_2_DRIVER;
    public static final String DB_URL = "jdbc:h2:mem://localhost/~/jdbc_practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
    public static final int MAX_POOL_SIZE = 40; // 커넥션 풀 사이즈 설정

    // 커넥션풀을 하나만 가지도록 설정
    private static final DataSource ds;

    static  {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(DB_DRIVER); // 상수로 DB_DRVIER로 설정
        hikariDataSource.setJdbcUrl(DB_URL); // 상수로 DB_URL로 설정
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("");
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE); // 최대 풀 사이즈
        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE); // 최소 아이들 설정

        ds = hikariDataSource;
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
