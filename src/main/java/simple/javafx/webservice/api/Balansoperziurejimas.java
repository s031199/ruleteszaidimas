//package simple.javafx.webservice.api;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//public interface Balansoperziurejimas {
//    public void Balansoperziurejimas() {
//        PreparedStatement stmtbalansas = connection.prepareStatement("select Balansas from u838202579_123456789.Balansas where Balansas.ID = (select USER.ID FROM u838202579_123456789.USER where PrisijungimoVardas = ? and Slaptazodis = ?);");
//        try {
//            PreparedStatement statementbalansas = stmtbalansas;
//            statementbalansas.setString(1, TFprisijungimovardas.getText());
//            statementbalansas.setString(2, TFPrisijungimoSlaptazodis.getText());
//            ResultSet rsbalansas = statementbalansas.executeQuery();
//            if (rsbalansas.next()) {
//                pagrindinisTextFieldBalansas1.setText(rsbalansas.getString(1));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Klaida");
//        }
//    }
//}
