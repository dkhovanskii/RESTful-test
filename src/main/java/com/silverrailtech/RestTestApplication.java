package com.silverrailtech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class RestTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestTestApplication.class, args);
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
