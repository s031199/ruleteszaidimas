package simple.javafx.webservice.api;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Klaidosispejimasv {
    @FXML
    private Button geraiBtn;


    public void onclickgerai(ActionEvent actionEvent) {
        Stage stage = (Stage) geraiBtn.getScene().getWindow();
        stage.close();
    }
}
