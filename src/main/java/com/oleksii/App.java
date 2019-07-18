package com.oleksii;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import com.oleksii.dbScript.DbScriptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class App {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    private void initDb() {
        String sqlStatements[] = {
                DbScriptUtil.getScript()
        };

        Arrays.asList(sqlStatements).forEach(sql -> {
            jdbcTemplate.execute(sql);
        });
    }
}
