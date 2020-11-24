package ru.stankin.laba1.servicies.serviciesImpl;

import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.stankin.laba1.entity.Car;
import ru.stankin.laba1.entity.CarRowMapper;
import ru.stankin.laba1.servicies.CarService;

import java.util.Collection;
import java.util.List;

@Data
@Component
public class CarServiceImpl implements CarService {
    private final JdbcTemplate jdbcTemplate;

    public CarServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Collection<Car> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM Car", new CarRowMapper());
    }

    public List<Car> findByMark(String mark) {
        String sqlRequest = "SELECT * FROM Car WHERE mark=?";
        return this.jdbcTemplate.query(sqlRequest, new Object[]{mark}, new CarRowMapper());
    }

    public Car findById(long id) {
        String sqlRequest = "SELECT * FROM Car WHERE car_id=?";
        return this.jdbcTemplate.queryForObject(sqlRequest, new Object[]{id}, new CarRowMapper());
    }

    public int save(Car car) {
        String sqlRequest = "INSERT INTO Car (car_id, mark, model, creation_date, sale_date, price, autoTransmission) VALUES (nextval('car_sequence'), ?, ?, ?, ?, ?, ?)";

        return this.jdbcTemplate.update(sqlRequest, car.getMark(), car.getModel(), car.getCreationDate(), car.getSaleDate(), car.getPrice(), car.isAutoTransmission());
    }

    public int update(Car car) {
        String sqlRequest = "UPDATE Car SET mark=?, model=?, autoTransmission=? WHERE car_id=?";

        return this.jdbcTemplate.update(sqlRequest, car.getMark(), car.getModel(), car.isAutoTransmission(), car.getId());
    }

    public int deleteById(long id) {
        String sqlRequest = "DELETE FROM Car WHERE car_id=?";

        return  this.jdbcTemplate.update(sqlRequest, id);
    }
}
