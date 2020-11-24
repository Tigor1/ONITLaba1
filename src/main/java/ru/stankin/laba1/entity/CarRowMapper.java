package ru.stankin.laba1.entity;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;



public class CarRowMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Car( rs.getLong("car_id"),
                        rs.getString("mark"),
                        rs.getString("model"),
                        rs.getDate("creation_date"),
                        rs.getDate("sale_date"),
                        rs.getFloat("price"),
                        rs.getBoolean("autoTransmission")
                        );
    }
}
