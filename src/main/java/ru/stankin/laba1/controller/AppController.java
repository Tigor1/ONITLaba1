package ru.stankin.laba1.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;
import ru.stankin.laba1.entity.Car;
import ru.stankin.laba1.servicies.CarService;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

@Component
public class AppController implements Initializable {
    private CarService carService;

    @FXML TableView table;
    @FXML TableColumn<Long, Car> idC;
    @FXML TableColumn<String, Car> markC;
    @FXML TableColumn<String, Car> modelC;
    @FXML TableColumn<Date, Car> creationDateC;
    @FXML TableColumn<Date, Car> saleDateC;
    @FXML TableColumn<Float, Car> priceC;
    @FXML TableColumn<Boolean, Car> autoTransmissionC;

    @FXML Button addBtn;
    @FXML Button deleteBtn;
    @FXML Button dropFilterBtn;
    @FXML Button filterBtn;

    @FXML TextField makrAddTF;
    @FXML TextField modelTF;
    @FXML TextField creationDateTF;
    @FXML TextField priceTF;
    @FXML DatePicker saleDateDP;
    @FXML CheckBox autoTransmissionCB;

    public AppController(CarService carService) {
        this.carService = carService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        markC.setCellValueFactory(new PropertyValueFactory<>("mark"));
        modelC.setCellValueFactory(new PropertyValueFactory<>("model"));
        creationDateC.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        saleDateC.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
        autoTransmissionC.setCellValueFactory(new PropertyValueFactory<>("autoTransmission"));
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getItems().addAll(carService.findAll());
    }

    @FXML
    public void addCar() {
        if (!makrAddTF.getText().isEmpty() && !modelTF.getText().isEmpty()
                && !creationDateTF.getText().isEmpty() && !priceTF.getText().isEmpty()
                && !saleDateDP.getEditor().getText().isEmpty()) {
            Car newCar = new Car(makrAddTF.getText(), modelTF.getText(),
                                Date.valueOf(creationDateTF.getText() + "-01-01"),
                                Date.valueOf(saleDateDP.getEditor().getText()),
                                Float.parseFloat(priceTF.getText()),
                                autoTransmissionCB.isSelected());

            newCar.setId(carService.save(newCar));
            table.getItems().add(newCar);
        }
    }
}
