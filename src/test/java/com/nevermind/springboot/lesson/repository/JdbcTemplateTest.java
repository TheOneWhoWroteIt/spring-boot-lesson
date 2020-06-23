package com.nevermind.springboot.lesson.repository;

import com.nevermind.springboot.lesson.IntegrationTestBase;
import com.nevermind.springboot.lesson.entity.CompanyEntity;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.collection.IsCollectionWithSize.*;
import static org.junit.jupiter.api.Assertions.*;

public class JdbcTemplateTest extends IntegrationTestBase {

    private static final String INSERT_SQL = "insert into company (name) values (?);";
    private static final String DELETE_RETURNING_SQL = "DELETE FROM company WHERE name=? RETURNING *";

    @Autowired
    private JdbcOperations jdbcOperations;

    @Test
    void insertTest(){
        int result = jdbcOperations.update(INSERT_SQL, "Microsoft");
        assertEquals(1, result);
    }

    @Test
    void returningTest(){
        String companyName = "Microsoft";
        int result = jdbcOperations.update(INSERT_SQL, companyName);

        List<CompanyEntity> queryResult = jdbcOperations.query(DELETE_RETURNING_SQL, new RowMapper<CompanyEntity>() {
            @Override
            public CompanyEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                return CompanyEntity.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build();
            }
        }, companyName);

        assertThat(queryResult, hasSize(1));
    }
}
