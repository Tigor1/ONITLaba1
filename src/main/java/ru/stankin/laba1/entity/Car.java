package ru.stankin.laba1.entity;

import lombok.Data;
import java.sql.Date;


@Data
public class Car {
    private long id;
    private String mark;
    private String model;
    private int creationDate;
    private Date saleDate;
    private float price;
    private boolean autoTransmission;

    public Car(long id, String mark, String model, int creationDate, Date saleDate, float price, boolean autoTransmission) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.creationDate = creationDate;
        this.saleDate = saleDate;
        this.price = price;
        this.autoTransmission = autoTransmission;
    }

    public Car(String mark, String model, int creationDate, Date saleDate, float price, boolean autoTransmission) {
        this.mark = mark;
        this.model = model;
        this.creationDate = creationDate;
        this.saleDate = saleDate;
        this.price = price;
        this.autoTransmission = autoTransmission;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAutoTransmission() {
        return autoTransmission;
    }

    public void setAutoTransmission(boolean autoTransmission) {
        this.autoTransmission = autoTransmission;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", creationDate=" + creationDate +
                ", saleDate=" + saleDate +
                ", price=" + price +
                ", autoTransmission=" + autoTransmission +
                '}';
    }
}
