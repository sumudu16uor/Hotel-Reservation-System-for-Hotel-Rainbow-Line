package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    DBconnect mdc = new DBconnect();
    Connection con = getConnection();
    Statement stmt;

    @FXML private Button btnlogin,btnClear;
    @FXML private PasswordField txtPwd ;
    @FXML private TextField txtUid;
    @FXML private Label lblLogin;

    private Connection getConnection() {
        Connection myConn = mdc.getMyConnection();
        return myConn;
    }

    public void btnloginOnAction(ActionEvent event){
        String myStatement;
        String uName = txtUid.getText();
        String uPass = txtPwd.getText();
        int count=0;

        myStatement="select * from admin where AdminID=\""+uName+"\"  and Password=\""+uPass+"\"";

        if (uName.equals("") ){
            lblLogin.setText("Username is empty");
        }else if(uPass.equals("") ){
            lblLogin.setText("Password is empty");
        }else {
            try {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(myStatement);

                while (rs.next()) {
                    count += 1;
                }

                if (count == 1) {
                    try {
                        Parent root1 = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Hotel Reservation System");
                        stage.setScene(new Scene(root1));
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = (Stage) btnlogin.getScene().getWindow();
                    stage.close();
                } else {
                    lblLogin.setText("Incorrect login details");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void BtnClearOnAction(ActionEvent event){
        Stage stage=(Stage) btnClear.getScene().getWindow();

        txtPwd.setText("");
        txtUid.setText("");
    }
}
