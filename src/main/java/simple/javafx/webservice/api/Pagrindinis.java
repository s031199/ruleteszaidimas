package simple.javafx.webservice.api;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Pagrindinis extends Controller implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Text paskyraVardas, paskyraAmzius, pagrindinisSveiki, textBalansas, paskyraTelefononumeris, paskyraPavarde, paskyraElpastas, paskyraLytis, textlaimejote;

    @FXML
    private TextField TFprisijungimovardas, lytisTextField, elpastasTextField, TFPrisijungimoSlaptazodis, amziusTextField, telefononumerisTextField, pagrindinisTextFieldBalansas, vardasTextField, pavardeTextField, pagrindinisTextFieldBalansas1, textfieldruletepastatytasuma, textfieldruletezaidejoskaicus, textfieldruleteskaicius;

    @FXML
    private Menu menuAtsijungti, menuRulete, menuPagrindinis, menuPaskyra;

    @FXML
    private Button issaugotiButton;
    @FXML
    private Button buttonpapildytiBalansa;
    @FXML
    private Button atsauktiButton;
    @FXML
    private Button terminalmygtukas;;

    @FXML
    private Pane paneperduotosinfo, panePaskyra, panePagrindinis, panerulete;

    public void setText(String perduotasnaudotojovardas, String perduotasnaudotojoslaptazodis) {
        this.TFprisijungimovardas.setText(perduotasnaudotojovardas);
        this.TFPrisijungimoSlaptazodis.setText(perduotasnaudotojoslaptazodis);
    }
    Connection connection = Prisijungimas.init();

    @FXML
    public void onClickpagrindinis(ActionEvent event) throws SQLException {
        panePaskyra.setVisible(false);
        panePagrindinis.setVisible(true);
        panerulete.setVisible(false);
        PreparedStatement stmt = connection.prepareStatement("SELECT Vardas FROM u838202579_123456789.USER where PrisijungimoVardas= ?  and Slaptazodis = ? ");
        try {
            PreparedStatement statement = stmt;
            statement.setString(1, TFprisijungimovardas.getText());
            statement.setString(2, TFPrisijungimoSlaptazodis.getText());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                pagrindinisSveiki.setText("Sveiki " + rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Klaida");
        }
        PreparedStatement stmtbalansas = connection.prepareStatement("select Balansas from u838202579_123456789.Balansas where Balansas.ID = (select USER.ID FROM u838202579_123456789.USER where PrisijungimoVardas = ? and Slaptazodis = ?);");
        try {
            PreparedStatement statementbalansas = stmtbalansas;
            statementbalansas.setString(1, TFprisijungimovardas.getText());
            statementbalansas.setString(2, TFPrisijungimoSlaptazodis.getText());
            ResultSet rsbalansas = statementbalansas.executeQuery();
            if (rsbalansas.next()) {
                pagrindinisTextFieldBalansas.setText(rsbalansas.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Klaida");
        }
    }

    @FXML
    void ActionPapildytiBalansa(ActionEvent event) {
        try {
            PreparedStatement stmtpapildymas = connection.prepareStatement("UPDATE u838202579_123456789.Balansas SET Balansas.Balansas= Balansas.Balansas + 1500 WHERE Balansas.ID = (SELECT USER.ID FROM USER WHERE PrisijungimoVardas = ? AND Slaptazodis = ?)");
            PreparedStatement statementpapildymas = stmtpapildymas;
            statementpapildymas.setString(1, TFprisijungimovardas.getText());
            statementpapildymas.setString(2, TFPrisijungimoSlaptazodis.getText());
            int rspapildymas = statementpapildymas.executeUpdate();
            if (rspapildymas > 0) {
                PreparedStatement stmtbalansas = connection.prepareStatement("select Balansas from u838202579_123456789.Balansas where Balansas.ID = (select USER.ID FROM u838202579_123456789.USER where PrisijungimoVardas = ? and Slaptazodis = ?);");
                try {
                    PreparedStatement statementbalansas = stmtbalansas;
                    statementbalansas.setString(1, TFprisijungimovardas.getText());
                    statementbalansas.setString(2, TFPrisijungimoSlaptazodis.getText());
                    ResultSet rsbalansas = statementbalansas.executeQuery();
                    if (rsbalansas.next()) {
                        pagrindinisTextFieldBalansas.setText(rsbalansas.getString(1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Klaida");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Klaida");
        }
    }

    @FXML
    void onClickissaugoti(ActionEvent event) throws SQLException {
        Connection connectionpaskyra = Prisijungimas.init();
        PreparedStatement stmtpaskyra = connectionpaskyra.prepareStatement("SELECT Vardas, Pavarde, El, Amzius, Tel, Lytis FROM u838202579_123456789.USER where PrisijungimoVardas= ?  and Slaptazodis = ? ");
        try {
            PreparedStatement statementpaskyra = stmtpaskyra;
            statementpaskyra.setString(1, TFprisijungimovardas.getText());
            statementpaskyra.setString(2, TFPrisijungimoSlaptazodis.getText());
            ResultSet rspaskyra = statementpaskyra.executeQuery();
            if (rspaskyra.next()) {
                vardasTextField.setText((rspaskyra.getString(1)));
                pavardeTextField.setText((rspaskyra.getString(2)));
                elpastasTextField.setText((rspaskyra.getString(3)));
                amziusTextField.setText((rspaskyra.getString(4)));
                telefononumerisTextField.setText((rspaskyra.getString(5)));
                lytisTextField.setText((rspaskyra.getString(6)));
            }
            try {
                if (vardasTextField.getText().equals(rspaskyra.getString(1)) || pavardeTextField.getText().equals(rspaskyra.getString(2)) || elpastasTextField.getText().equals(rspaskyra.getString(3)) || amziusTextField.getText().equals(rspaskyra.getString(4)) || telefononumerisTextField.getText().equals(rspaskyra.getString(5)) || lytisTextField.getText().equals(rspaskyra.getString(6))) {
                    try {
                        PreparedStatement stmtpaskyraatnaujinta = connectionpaskyra.prepareStatement("UPDATE u838202579_123456789.USER set Vardas = ?, Pavarde = ?, El = ?, Amzius = ?, Tel = ?, Lytis = ? where PrisijungimoVardas= ?  and Slaptazodis = ? ");
                        PreparedStatement statementpaskyraatnaujinta = stmtpaskyraatnaujinta;
                        statementpaskyraatnaujinta.setString(1, vardasTextField.getText());
                        statementpaskyraatnaujinta.setString(2, pavardeTextField.getText());
                        statementpaskyraatnaujinta.setString(3, elpastasTextField.getText());
                        statementpaskyraatnaujinta.setString(4, amziusTextField.getText());
                        statementpaskyraatnaujinta.setString(5, telefononumerisTextField.getText());
                        statementpaskyraatnaujinta.setString(6, lytisTextField.getText());
                        statementpaskyraatnaujinta.setString(7, TFprisijungimovardas.getText());
                        statementpaskyraatnaujinta.setString(8, TFPrisijungimoSlaptazodis.getText());
                        int rspaskyraatnaujinta = statementpaskyraatnaujinta.executeUpdate();
                        if (rspaskyraatnaujinta > 0) {
                            PreparedStatement stmtpaskyrarodymas = connectionpaskyra.prepareStatement("select Vardas, Pavarde, El, Amzius, Tel, Lytis from u838202579_123456789.USER where PrisijungimoVardas= ?  and Slaptazodis = ? ");
                            try {
                                PreparedStatement statementpaskyraatnaujintarodymas = stmtpaskyrarodymas;
                                statementpaskyraatnaujintarodymas.setString(1, TFprisijungimovardas.getText());
                                statementpaskyraatnaujintarodymas.setString(2, TFPrisijungimoSlaptazodis.getText());
                                ResultSet rspaskyrarodymas = statementpaskyraatnaujintarodymas.executeQuery();
                                if (rspaskyrarodymas.next()) {
                                    vardasTextField.setText(rspaskyrarodymas.getString(1));
                                    pavardeTextField.setText(rspaskyrarodymas.getString(2));
                                    elpastasTextField.setText(rspaskyrarodymas.getString(3));
                                    amziusTextField.setText(rspaskyrarodymas.getString(4));
                                    telefononumerisTextField.setText(rspaskyrarodymas.getString(5));
                                    lytisTextField.setText(rspaskyrarodymas.getString(6));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("Klaida");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Klaida");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Klaida");
        }

    }

    @FXML
    void onClickpaskyra(ActionEvent event) {
        panePagrindinis.setVisible(false);
        panePaskyra.setVisible(true);
        panerulete.setVisible(false);
    }

    @FXML
    void onClickcancel(ActionEvent event) {

    }

    @FXML
    void onClickrulete(ActionEvent event) throws SQLException{
        panePaskyra.setVisible(false);
        panePagrindinis.setVisible(false);
        panerulete.setVisible(true);
        PreparedStatement stmtbalansas = connection.prepareStatement("select Balansas from u838202579_123456789.Balansas where Balansas.ID = (select USER.ID FROM u838202579_123456789.USER where PrisijungimoVardas = ? and Slaptazodis = ?);");
        try {
            PreparedStatement statementbalansas = stmtbalansas;
            statementbalansas.setString(1, TFprisijungimovardas.getText());
            statementbalansas.setString(2, TFPrisijungimoSlaptazodis.getText());
            ResultSet rsbalansas = statementbalansas.executeQuery();
            if (rsbalansas.next()) {
                pagrindinisTextFieldBalansas1.setText(rsbalansas.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Klaida");
        }
    }

    @FXML
    void onClickatsijungti(ActionEvent event) {
    }

    @FXML
    void onclickruletepaleidimas(ActionEvent event) throws SQLException {
        textlaimejote.setVisible(false);

        if(textfieldruletepastatytasuma.getText().equals("") || textfieldruletezaidejoskaicus.getText().equals(""))
        {
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
        else if(!textfieldruletepastatytasuma.getText().equals("") || !textfieldruletezaidejoskaicus.getText().equals(""))
        {
            PreparedStatement stmtpapildymas = connection.prepareStatement("UPDATE u838202579_123456789.Balansas SET Balansas.Balansas= Balansas.Balansas - ? WHERE Balansas.ID = (SELECT USER.ID FROM USER WHERE PrisijungimoVardas = ? AND Slaptazodis = ?)");
            try {
                PreparedStatement statementbalansas = stmtpapildymas;
                statementbalansas.setInt(1, Integer.parseInt(textfieldruletepastatytasuma.getText()));
                Naudotojoprisijungimai(statementbalansas);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Klaida");
            }

            Random generator = new Random();
            int rouletteNum, result, total;
            int resultArr[] = new int[36];
            rouletteNum = generator.nextInt(37);
            textfieldruleteskaicius.setText(String.valueOf(rouletteNum));
            if (rouletteNum == Integer.parseInt(textfieldruletezaidejoskaicus.getText()))
                result = 35;
            else
                result = 0;
            if(Integer.parseInt(textfieldruletezaidejoskaicus.getText()) == rouletteNum )
            {
                total = Integer.parseInt(textfieldruletepastatytasuma.getText()) *  result;
                PreparedStatement stmtpapildymas1 = connection.prepareStatement("UPDATE u838202579_123456789.Balansas SET Balansas.Balansas= Balansas.Balansas + ? WHERE Balansas.ID = (SELECT USER.ID FROM USER WHERE PrisijungimoVardas = ? AND Slaptazodis = ?)");
                try {
                    PreparedStatement statementbalansas = stmtpapildymas1;
                    statementbalansas.setInt(1, total);
                    Naudotojoprisijungimai(statementbalansas);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Klaida");
                }
                textlaimejote.setVisible(true);
            }
        }
    }

    private void Naudotojoprisijungimai(PreparedStatement statementbalansas) throws SQLException {
        statementbalansas.setString(2, TFprisijungimovardas.getText());
        statementbalansas.setString(3, TFPrisijungimoSlaptazodis.getText());
        int rsbalansas = statementbalansas.executeUpdate();
        if (rsbalansas > 0) {
                    PreparedStatement stmtbalansas1 = connection.prepareStatement("select Balansas from u838202579_123456789.Balansas where Balansas.ID = (select USER.ID FROM u838202579_123456789.USER where PrisijungimoVardas = ? and Slaptazodis = ?);");
                    try {
                        PreparedStatement statementbalansas4 = stmtbalansas1;
                        statementbalansas4.setString(1, TFprisijungimovardas.getText());
                        statementbalansas4.setString(2, TFPrisijungimoSlaptazodis.getText());
                        ResultSet rsbalansas1 = statementbalansas4.executeQuery();
                        if (rsbalansas1.next()) {
                            pagrindinisTextFieldBalansas1.setText(rsbalansas1.getString(1));
                        }
                }
                    catch (Exception e) {
                e.printStackTrace();
                System.out.println("Klaida");
            }
        }
    }


    @FXML
    void onClickTerminal(ActionEvent event) throws IOException {

        File file = new File("D:/Main programa/Bandymaslazybuinformacija/src/main/java/terminal/TerminalMain.java");
        Desktop.getDesktop().open(file);
        System.out.println("the output is :");
    }
}

