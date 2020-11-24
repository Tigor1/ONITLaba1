package ru.stankin.laba1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.stankin.laba1.controller.AppController;
import ru.stankin.laba1.servicies.serviciesImpl.CarServiceImpl;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName("org.postgresql.Driver");
        driver.setUrl("jdbc:postgresql://localhost:5432/Worker_time");
        driver.setUsername("igor");
        driver.setPassword("root");
        return driver;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public CarServiceImpl carServiceImpl() {
        return new CarServiceImpl(jdbcTemplate(dataSource()));
    }

    @Bean
    public AppController appController() {
        return new AppController(carServiceImpl());
    }
}
