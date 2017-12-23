package com.kasakad.fileio.kasakaidfileio;

import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

//@TestConfiguration は無意味。DI をしないので、使わない。
@Configuration
 @Profile("test")
// @TestPropertySource だと application.properties を読もうともしない。PropertySource にする。
@PropertySource("classpath:application-test.properties")
public class TestConfig {

    @Autowired
    private Environment environment;

    @Bean
    public TransactionAwareDataSourceProxy dataSource() {
        if ("jdbc:log4jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_ON_EXIT=FALSE".equals(environment.getProperty("spring.datasource.url"))) {
            // H2 の読み込みをさせないと、JPAが作成したデータベースにアプリケーションからアクセスできない。そのため、これで対処。
            EmbeddedDatabase ds = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
            DataSourceSpy proxyDs = new net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy(ds);
            return new TransactionAwareDataSourceProxy(proxyDs);
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));

        return new TransactionAwareDataSourceProxy(dataSource);
    }

    @Bean
    public MyResource myResource() {
        return new MyResource();
    }
}

