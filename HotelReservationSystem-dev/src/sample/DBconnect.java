package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBconnect {

    private void registerMyConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("error registering drive");
        }
    }

    public Connection getMyConnection(){
        String url = "jdbc:mysql://localhost:3306/rainbow_hotel";
        String user = "root";
        String pw = "";

        Connection myConnection = null;
        try {
            myConnection = DriverManager.getConnection(url, user, pw);
        } catch (SQLException ex) {
            Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
        }

        return myConnection;

    }
}
