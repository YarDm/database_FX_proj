package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
*в JavaFX, который входит в стандартную комплектацию JDK,
*стартовая точка приложения определяется методом старт и наслодования от Application
*/
public class DbFx_main extends Application {

    private Stage primaryStage;

    public DbFx_main(){

    }

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

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }

}
