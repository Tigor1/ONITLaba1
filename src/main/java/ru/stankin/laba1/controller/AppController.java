package ru.stankin.laba1.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.springframework.stereotype.Component;
import ru.stankin.laba1.entity.Car;
import ru.stankin.laba1.servicies.CarService;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class AppController implements Initializable {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
    private CarService carService;

    @FXML TableView table;
    @FXML TableColumn<Long, Car> idC;
    @FXML TableColumn<String, Car> markC;
    @FXML TableColumn<String, Car> modelC;
    @FXML TableColumn<Integer, Car> creationDateC;
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

    //filter
    @FXML TextField creationDateFTF;
    @FXML DatePicker saleDateFDP;
    @FXML TextField priceFromFTF;
    @FXML TextField priceUpToFTF;
    @FXML CheckBox autoTransmissionFCB;

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

        StringConverter<LocalDate> strConverter = new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                    return null;
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        };

        saleDateFDP.setConverter(strConverter);
        saleDateDP.setConverter(strConverter);
    }

    @FXML
    public void addCar() {
        if (!makrAddTF.getText().isEmpty() && !modelTF.getText().isEmpty()
                && !creationDateTF.getText().isEmpty() && !priceTF.getText().isEmpty()
                && !saleDateDP.getEditor().getText().isEmpty()) {
            Car newCar = new Car(makrAddTF.getText(), modelTF.getText(),
                                Integer.parseInt(creationDateTF.getText()),
                                Date.valueOf(saleDateDP.getEditor().getText()),
                                Float.parseFloat(priceTF.getText()),
                                autoTransmissionCB.isSelected());

            newCar.setId(carService.save(newCar));
            table.getItems().add(newCar);
        }
    }

    @FXML
    public void filter() {
        String test = creationDateFTF.getText();

        int creationDate = !creationDateFTF.getText().isEmpty() && !creationDateFTF.getText().isEmpty()? Integer.parseInt(creationDateFTF.getText()) : -1;
        Date saleDate = !saleDateFDP.getEditor().getText().isBlank()? Date.valueOf(saleDateFDP.getEditor().getText()) : null;
        float priceFrom = !priceFromFTF.getText().isBlank()? Float.parseFloat(priceFromFTF.getText()) : -1;
        float priceUpTo = !priceUpToFTF.getText().isBlank()? Float.parseFloat(priceUpToFTF.getText()) : -1;

        List<Car> cars = carService.filter(creationDate, saleDate, priceFrom, priceUpTo, autoTransmissionFCB.isSelected());
        table.getItems().clear();
        table.getItems().addAll(cars);
    }

    @FXML
    public void dropFilter() {
        creationDateFTF.clear();
        saleDateFDP.getEditor().clear();
        priceFromFTF.clear();
        priceUpToFTF.clear();
        autoTransmissionFCB.setSelected(false);

        table.getItems().clear();
        table.getItems().addAll(carService.findAll());
    }

    @FXML
    public void deleteCar() {
        Car car = (Car) table.getSelectionModel().getSelectedItem();
        carService.deleteById(car.getId());
        table.getItems().remove(car);
    }
}
