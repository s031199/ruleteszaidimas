package simple.javafx.webservice.api;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller extends Main implements Initializable {

    @FXML
    private Text text;

    @FXML
    private TextField prisijungimoVardasTextField;

    @FXML
    private PasswordField slaptazodzioLaukasPasswordField;

    @FXML
    private Button prisijungimoButton, geraiBtn;

    @FXML
    private Hyperlink registracijosHyperLink;

    @FXML
    void onClickRegistracija(ActionEvent event) {
            try {
                FXMLLoader fxmlLoaderregistracijos = new FXMLLoader(getClass().getClassLoader().getResource("fxml/registracija.fxml"));
                Parent rootreg = fxmlLoaderregistracijos.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.DECORATED);
                stage.setTitle("Registracija!");
                stage.setScene(new Scene(rootreg));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Klaida");
            }
    }

    @FXML
    void onclickprisijungimas(ActionEvent event) {
            if (prisijungimoVardasTextField.getText().equals("") && slaptazodzioLaukasPasswordField.getText().equals("")) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/klaidosispejimasvs.fxml"));
                    Parent root1 = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setTitle("Klaida!");
                    stage.setScene(new Scene(root1));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Klaida");
                }
            }
            else if (prisijungimoVardasTextField.getText().equals("")) {
                try {
                    FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/klaidosispejimasv.fxml"));
                    Parent root2 = fxmlLoader1.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setTitle("Klaida!");
                    stage.setScene(new Scene(root2));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Klaida");
                }
            }
            else if (slaptazodzioLaukasPasswordField.getText().equals("")) {
                try {
                    FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/klaidosispejimass.fxml"));
                    Parent root3 = fxmlLoader3.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setTitle("Klaida!");
                    stage.setScene(new Scene(root3));
                    stage.show();

                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Klaida");
                }
            }
            else {
                Connection connection = Prisijungimas.init();
                String Prisijungimas = "select * from USER where PrisijungimoVardas = ? and Slaptazodis = ?";
                try {
                    PreparedStatement statement = connection.prepareStatement(Prisijungimas);
                    statement.setString(1, prisijungimoVardasTextField.getText());
                    statement.setString(2, slaptazodzioLaukasPasswordField.getText());
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        try {
                            //stageprisijungimas.hide();
                            //Platform.exit();
                            FXMLLoader fxmlLoaderPagrindinis = new FXMLLoader(getClass().getClassLoader().getResource("fxml/pagrindinis.fxml"));
                            Parent rootpagrindinis = fxmlLoaderPagrindinis.load();
                            Stage stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.initStyle(StageStyle.DECORATED);
                            stage.setTitle("Tesbet");
                            stage.setScene(new Scene(rootpagrindinis));
                            stage.show();

                            String perduotasnaudotojovardas = prisijungimoVardasTextField.getText();
                            String perduotasnaudotojoslaptazodis = slaptazodzioLaukasPasswordField.getText();
                            Pagrindinis display = fxmlLoaderPagrindinis.getController();
                            display.setText(perduotasnaudotojovardas, perduotasnaudotojoslaptazodis);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Klaida");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}



