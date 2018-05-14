package main;

import fxml.FrontViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
* в JavaFX, который входит в стандартную комплектацию JDK,
* стартовая точка приложения определяется методом @method: start() и наслодования от Application
*/
public class DbFx_main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/FrontView.fxml"));
            Scene scene = new Scene(root);

            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Отчетное задание БД Java");
            this.primaryStage.setScene(scene);
            this.primaryStage.show();

            // ссылка на контроллер, выполняет его создание и применение в управляющем методе
            FrontViewController fvc = new FrontViewController();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // можно не использовать метод main(), но в случае использования в нем вызывается метод launch()
    // с таким же массивом аргументов
    public static void main(String[] args){
        launch(args);
    }

}
