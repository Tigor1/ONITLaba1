package ru.stankin.laba1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.stankin.laba1.config.AppConfig;


@ComponentScan
public class App extends Application {
    private ApplicationContext context;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        this.context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/index.fxml"));
        loader.setControllerFactory(context::getBean);
        primaryStage.setTitle("information.security");
        Parent root = loader.load();
        Scene scene = new Scene(root, 1080, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
