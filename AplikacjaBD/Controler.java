package AplikacjaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Controler {
    DBConnect dao = new DBConnect();
    public ArrayList<Row> pokaz() throws SQLException {
        ArrayList<Row> list = new ArrayList<>();
        String name, vorname, number;
        int  id;
        Statement st = dao.getCon().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM kontakty");
        while(rs.next()){
            id = rs.getInt("ID");
            name = rs.getString("imie");
            vorname = rs.getString("nazwisko");
            number = rs.getString("telefon");
           Row row = new Row(number,name,vorname,id);
           list.add(row);
        }
        st.close();
        return list;
    }
    public void usun(int ID) throws SQLException {
        String sql = "DELETE FROM kontakty WHERE ID = ?";
        PreparedStatement pst = dao.getCon().prepareStatement(sql);
        pst.setInt(1,ID);
        pst.execute();
        pst.close();
        dao.getCon().commit();

    }
    public void dodaj(String name, String vorname, String number) throws SQLException {
        String sql = "INSERT INTO kontakty (imie,nazwisko,telefon) VALUES (?,?,?)";
        PreparedStatement pst = dao.getCon().prepareStatement(sql);
        pst.setString(1,name);
        pst.setString(2,vorname);
        pst.setString(3,number);
        pst.execute();
        pst.close();
        dao.getCon().commit();

    }
    public ArrayList<Row> wyszukaj(String type, String str) throws SQLException {
        ArrayList<Row> list = new ArrayList<>();
        String name, vorname, number;
        int id;
         Statement st = dao.getCon().createStatement();
         String sql1 = "SELECT * FROM kontakty WHERE "+type+" LIKE '%"+str+"%' ";
         //PreparedStatement pst1 = dao.getCon().prepareStatement(sql1);
         ResultSet rs = st.executeQuery(sql1);
         while(rs.next()){
             name = rs.getString("imie");
             vorname = rs.getString("nazwisko");
             number = rs.getString("telefon");
             id = rs.getInt("ID");
             Row row = new Row(number,name,vorname,id);
             list.add(row);
         }
         st.close();
         return list;
    }

    public void zmien(String name, String vorname, String number, int id) throws SQLException {
        String name2 = "";
        String vorname2 = "";
        String number2 = "";
        Statement st = dao.getCon().createStatement();
        String sql1 = "SELECT * FROM kontakty WHERE ID = "+id+"";
        //PreparedStatement pst1 = dao.getCon().prepareStatement(sql1);
        ResultSet rs = st.executeQuery(sql1);
        while(rs.next()){
            name2 = rs.getString("imie");
            vorname2 = rs.getString("nazwisko");
            number2 = rs.getString("telefon");
            System.out.println("id "+id+" imie: "+name2+" nazwisko: "+vorname2+" numer " + number2);
        }
        st.close();
        if(name.equals("")){
            name = name2;
        }
        if(vorname.equals("")){
            vorname = vorname2;
        }
        if(number.equals("")){
            number = number2;
        }
            String sql = "UPDATE kontakty SET imie =?, nazwisko = ?, telefon=? WHERE ID = ? ";
            PreparedStatement pst = dao.getCon().prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,vorname);
            pst.setString(3,number);
            pst.setInt(4,id);
            pst.execute();
            pst.close();
            dao.getCon().commit();

    }

}
