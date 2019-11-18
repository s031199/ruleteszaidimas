package simple.javafx.webservice.api;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Registracijosklaida {

    @FXML
    private Button geraiButton;

    @FXML
    void onclickgerai(ActionEvent event) {
        Stage stage = (Stage) geraiButton.getScene().getWindow();
        stage.close();
    }

}
