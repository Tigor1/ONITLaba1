package ru.stankin.laba1.dto;


import java.sql.Connection;

public class CarDto {
    private Connection connection;

    public CarDto(Connection connection) {
        this.connection = connection;
    }
}
