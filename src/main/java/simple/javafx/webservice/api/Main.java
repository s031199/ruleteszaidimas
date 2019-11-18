package simple.javafx.webservice.api;
import java.util.function.BiFunction;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BiFunction<String, String,String> bi = (x, y) -> {
            return x + y;
        };
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Stage stageprisijungimas = new Stage();
        stageprisijungimas.setTitle(bi.apply("Prisijungimas", " programos"));
        stageprisijungimas.setScene(new Scene(root));
        stageprisijungimas.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}


