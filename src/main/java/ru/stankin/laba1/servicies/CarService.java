package ru.stankin.laba1.servicies;

import ru.stankin.laba1.entity.Car;

import java.util.Collection;
import java.util.List;

public interface CarService {
    Collection<Car> findAll();
    List<Car> findByMark(String mark);
    Car findById(long id);
    int save(Car car);
    int update(Car car);
    int deleteById(long id);
}
