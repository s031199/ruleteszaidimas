package simple.javafx.webservice.api;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Registracija extends Controller{

    @FXML
    private Button atsauktiButton, registruotisButton;

    @FXML
    private TextField elpastasTextField, vardasTextField, pakartokiteelpastaTextField, amziusTextField, telefononumerisTextField, pavardeTextField, lytisTextField, prisijungimovardasTextField;

    @FXML
    private PasswordField pakartokiteslaptazodiPasswordField, slaptazodisPasswordField;

    @FXML
    void onClickregistruotis(ActionEvent event) {
                Scanner reader = new Scanner(amziusTextField.getText());
                while (!reader.hasNextInt()) {
                    JOptionPane.showMessageDialog(null,
                            "Blogai įrašytas amžius",
                            "Klaida",
                            JOptionPane.WARNING_MESSAGE);
                    reader.next();
                    // ghhhhhhhhhhh
                }
                if(vardasTextField.getText().equals("") || pavardeTextField.getText().equals("") || prisijungimovardasTextField.getText().equals("") || slaptazodisPasswordField.getText().equals("") || pakartokiteslaptazodiPasswordField.getText().equals("") || elpastasTextField.getText().equals("") || pakartokiteelpastaTextField.getText().equals("") || telefononumerisTextField.getText().equals("") || amziusTextField.getText().equals("") || lytisTextField.getText().equals(""))
                {
                try {
                FXMLLoader rootregistracijospatvirtinimas = new FXMLLoader(getClass().getClassLoader().getResource("fxml/registracijosklaida.fxml"));
                Parent rootregistracijospatvirtinimas1 = null;
                rootregistracijospatvirtinimas1 = rootregistracijospatvirtinimas.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.DECORATED);
                stage.setTitle("Klaida!");
                stage.setScene(new Scene(rootregistracijospatvirtinimas1));
                stage.show();

                } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Klaida");
                }
                }
                else if(!vardasTextField.getText().equals("") && !pavardeTextField.getText().equals("") && !prisijungimovardasTextField.getText().equals("") && !slaptazodisPasswordField.getText().equals("") && !pakartokiteslaptazodiPasswordField.getText().equals("") && !elpastasTextField.getText().equals("") && !pakartokiteelpastaTextField.getText().equals("") && !telefononumerisTextField.getText().equals("") && !amziusTextField.getText().equals("") && !lytisTextField.getText().equals("") && slaptazodisPasswordField.getText().equals(pakartokiteslaptazodiPasswordField.getText()) && elpastasTextField.getText().equals(pakartokiteelpastaTextField.getText()))
                {
                    Data.Registracija(vardasTextField.getText(), pavardeTextField.getText(), prisijungimovardasTextField.getText(), slaptazodisPasswordField.getText(), elpastasTextField.getText(), telefononumerisTextField.getLength(), amziusTextField.getLength(), lytisTextField.getText());
                    System.out.println("Registracija pavyko");
                    try {
                        FXMLLoader fxmlLoaderregistracijapavyko = new FXMLLoader(getClass().getClassLoader().getResource("fxml/registracijapavyko.fxml"));
                        Parent rootregistracijapavyko = null;
                        rootregistracijapavyko = fxmlLoaderregistracijapavyko.load();
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initStyle(StageStyle.DECORATED);
                        stage.setTitle("Pavyko!");
                        stage.setScene(new Scene(rootregistracijapavyko));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
    }

    @FXML
    void onClickcancel(ActionEvent event) {
        Stage stage = (Stage) atsauktiButton.getScene().getWindow();
        stage.close();
    }

}
