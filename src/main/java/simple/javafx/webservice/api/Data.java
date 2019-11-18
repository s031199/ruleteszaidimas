package simple.javafx.webservice.api;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Data implements AutoCloseable{

    private static Connection connection = Prisijungimas.init();

    public static void Registracija(String Vardas, String Pavarde, String PrisijungimoVardas, String Slaptazodis, String El, int Tel, int Amzius, String Lytis){
        String sql = "insert into USER values (0,?,?,?,?,?,?,?,?,0)";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Vardas);
            preparedStatement.setString(2, Pavarde);
            preparedStatement.setString(3, PrisijungimoVardas);
            preparedStatement.setString(4, Slaptazodis);
            preparedStatement.setString(5, El);
            preparedStatement.setInt(6, Tel );
            preparedStatement.setInt(7, Amzius );
            preparedStatement.setString(8, Lytis);

            preparedStatement.executeUpdate();
            System.out.println("Inserted new Record!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, ": Nepaejo");
            e.printStackTrace();
        }
    }

    public static ArrayList<String> display(){
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT PrisijungimoVardas, Slaptazodis FROM u838202579_123456789.USER ");
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
                ArrayList<String> arrlist = new ArrayList<String>();

                arrlist.add(rs.getString("PrisijungimoVardas")+ " " +rs.getString("Slaptazodis"));
                System.out.println(arrlist);
                return arrlist;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}