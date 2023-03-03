package sample;

import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    DBconnect mdc = new DBconnect();
    Connection con = getConnection();
    Statement stmt;


    ObservableList<String> pkgsList = FXCollections.observableArrayList("Package 1","Package 2","Package 3");
    ObservableList<String> timeSlotList = FXCollections.observableArrayList("Day","Night");

    @FXML Button navBtn1,navBtn2,navBtn3,navBtn4,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,ciCheck,ciCheckin,erCheck,erClear,erCal,erCheckout;
    @FXML Pane rsPane,rrPane,sPane,repPane;
    @FXML ChoiceBox pkgs,timeSlot;
    @FXML DatePicker ciDate,erDate;
    @FXML TextField ciTime,ciName,ciId,ciAdd,ciVehino,ciTp,erName,erTp,erAdd,erNop,erId,erDisc,erPayment;
    @FXML Label ciSelroom,ciDprice,ciResult,erError,noPlates,erTotalVal,erSubTotalVal,erHall;
    @FXML Pane erPanel;

    private int ro1=0,ro2=0,ro3=0,ro4=0,ro5=0,ro6=0,ro7=0,ro8=0,ro9=0,ro10=0;
    private int ros1=0,ros2=0,ros3=0,ros4=0,ros5=0,ros6=0,ros7=0,ros8=0,ros9=0,ros10=0;
    private int pkg=0,plate=0;
    private double subTotal=0,total=0,discount=0,hall = 10000;
    static int reservationID= 0,process=0;
    static String cuDate,ercDate,ercTime;

    DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");

    String neTime,nDate;

    LocalDateTime now = LocalDateTime.now();

    private Connection getConnection() {
        Connection myConn = mdc.getMyConnection();
        return myConn;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navBtn1.setStyle("-fx-background-color: #a3b5d9");
        pkgs.setItems(pkgsList);
        timeSlot.setItems(timeSlotList);
        reservedRoom();
        rsPane.toFront();

        pkgs.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    int selection=new_val.intValue();
                    if(selection == 0){
                        noPlates.setText("Rs. 990 per Plate");
                        pkg= selection+1;
                    }else if(selection == 1){
                        noPlates.setText("Rs. 790 per Plate");
                        pkg= selection+1;
                    }else if(selection == 2){
                        noPlates.setText("Rs. 690 per Plate");
                        pkg= selection+1;
                    }else{

                    }

                });

    }

    private void roomReserveSetter(String myStatement) throws SQLException {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(myStatement);
        while (rs.next()) {
            if (rs.getInt("RoomNo") == 1) {
                ro1 = 3;
                r1.setStyle("-fx-background-color: red");
                ros1 = rs.getInt("ReservationID");

            } else if (rs.getInt("RoomNo") == 2) {
                ro2 = 3;
                ros2 = rs.getInt("ReservationID");

            } else if (rs.getInt("RoomNo") == 3) {
                ro3 = 3;
                r3.setStyle("-fx-background-color: red");
                ros3 = rs.getInt("ReservationID");

            } else if (rs.getInt("RoomNo") == 4) {
                ro4 = 3;
                r4.setStyle("-fx-background-color: red");
                ros4 = rs.getInt("ReservationID");

            } else if (rs.getInt("RoomNo") == 5) {
                ro5 = 3;
                r5.setStyle("-fx-background-color: red");
                ros5 = rs.getInt("ReservationID");

            } else if (rs.getInt("RoomNo") == 6) {
                ro6 = 3;
                r6.setStyle("-fx-background-color: red");
                ros6 = rs.getInt("ReservationID");

            } else if (rs.getInt("RoomNo") == 7) {
                ro7 = 3;
                r7.setStyle("-fx-background-color: red");
                ros7 = rs.getInt("ReservationID");

            } else if (rs.getInt("RoomNo") == 8) {
                ro8 = 3;
                r8.setStyle("-fx-background-color: red");
                ros8 = rs.getInt("ReservationID");

            } else if (rs.getInt("RoomNo") == 9) {
                ro9 = 3;
                r9.setStyle("-fx-background-color: red");
                ros9 = rs.getInt("ReservationID");

            } else if (rs.getInt("RoomNo") == 10) {
                ro10 = 3;
                r10.setStyle("-fx-background-color: red");
                ros10 = rs.getInt("ReservationID");

            }
        }

    }

    private void reservedRoom() {

        String myStatement;
        myStatement="select * from reservation where  Check_out_date >= \""+date.format(now)+"\" or Check_out_date is NULL";

        neTime=time.format(now);
        nDate=date.format(now);
        cuDate=date.format(now);
        ciDate.setValue(LocalDate.now());
        ciTime.setText(time.format(now).toString());

        try {
            roomReserveSetter(myStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        System.out.println(myStatement);
    }

    public void ciClearOnAction(ActionEvent event){

        resetButtons();

        ciTime.setText("");
        ciDate.setValue(null);
        ciAdd.setText("");
        ciVehino.setText("");
        ciId.setText("");
        ciTp.setText("");
        ciName.setText("");
        ciSelroom.setText("No room Selected");
        ciResult.setText("");
        reservedRoom();
        ciDprice.setText("");

    }

    public void NavBtn1OnAction(ActionEvent event){
        navBtn1.setStyle("-fx-background-color: #a3b5d9");
        navBtn2.setStyle("-fx-background-color: #0c447b");
        navBtn3.setStyle("-fx-background-color: #0c447b");
        navBtn4.setStyle("-fx-background-color: #0c447b");
        rsPane.toFront();

    }

    public void NavBtn2OnAction(ActionEvent event){
        navBtn2.setStyle("-fx-background-color: #a3b5d9");
        navBtn1.setStyle("-fx-background-color: #0c447b");
        navBtn3.setStyle("-fx-background-color: #0c447b");
        navBtn4.setStyle("-fx-background-color: #0c447b");
        rrPane.toFront();
        erPanel.setVisible(false);
    }

    public void NavBtn3OnAction(ActionEvent event){
        navBtn3.setStyle("-fx-background-color: #a3b5d9");
        navBtn2.setStyle("-fx-background-color: #0c447b");
        navBtn1.setStyle("-fx-background-color: #0c447b");
        navBtn4.setStyle("-fx-background-color: #0c447b");
        sPane.toFront();

    }

    public void NavBtn4OnAction(ActionEvent event){
        navBtn4.setStyle("-fx-background-color: #a3b5d9");
        navBtn2.setStyle("-fx-background-color: #0c447b");
        navBtn1.setStyle("-fx-background-color: #0c447b");
        navBtn3.setStyle("-fx-background-color: #0c447b");
        repPane.toFront();

    }

    public void ciCheckinOnAction(ActionEvent event){

        int room = getSelectedRoom();

        String name=ciName.getText();
        String id=ciId.getText();
        String add=ciAdd.getText();
        String vehino=ciVehino.getText();
        String tp=ciTp.getText();

        CheckinProcess cp = new CheckinProcess(name,id,add,vehino,tp,neTime,nDate,room,cuDate);
        ciResult.setText(cp.mainProcess());

    }

    private int getSelectedRoom(){
        if (ro1 == 1){
            return 1;
        }else if(ro2 == 1){
            return 2;
        }else if(ro3 == 1){
            return 3;
        }else if(ro4 == 1){
            return 4;
        }else if(ro5 == 1){
            return 5;
        }else if(ro6 == 1){
            return 6;
        }else if(ro7 == 1){
            return 7;
        }else if(ro8 == 1){
            return 8;
        }else if(ro9 == 1){
            return 9;
        }else if(ro10 == 1){
            return 10;
        }else{
            return 0;
        }
    }

    public void ciCheckOnAction(ActionEvent event){
        String nTime = ciTime.getText();

        if(nTime.length() == 5){
            resetButtons();
            neTime = nTime;
            nDate = date.format(ciDate.getValue());

            String myStatement;

            myStatement="select * from reservation where Check_out_date >= \""+nDate+"\" or Check_out_date is NULL";


            try {
                roomReserveSetter(myStatement);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            System.out.println(myStatement);
        }

    }

    private void resetButtons(){
        if(ro1 != 3){
            r1.setStyle("-fx-background-color: green");
            ro1=0;
        }
        if(ro2 != 3){
            r2.setStyle("-fx-background-color: green");
            ro2=0;
        }
        if(ro3 != 3){
            r3.setStyle("-fx-background-color: green");
            ro3=0;
        }
        if(ro4 != 3){
            r4.setStyle("-fx-background-color: green");
            ro4=0;
        }
        if(ro5 != 3){
            r5.setStyle("-fx-background-color: green");
            ro5=0;
        }
        if(ro6 != 3){
            r6.setStyle("-fx-background-color: green");
            ro7=0;
        }
        if(ro8 != 3){
            r8.setStyle("-fx-background-color: green");
            ro8=0;
        }
        if(ro9 != 3){
            r9.setStyle("-fx-background-color: green");
            ro9=0;
        }
        if(ro10 != 3){
            r10.setStyle("-fx-background-color: green");
            ro10=0;
        }
        if(ro7 != 3){
            r7.setStyle("-fx-background-color: green");
            ro7=0;
        }

    }

    public void r7OnAction(ActionEvent event) throws IOException {
        if (ro7 == 1) {
            r7.setStyle("-fx-background-color: green");
            ro7 = 0;
        }else if(ro7 == 0){

            resetButtons();
            reservedRoom();

            ciDprice.setText("Rs. 1500.00 per day");
            r7.setStyle("-fx-background-color: #dede01");
            ro7 =1;
            ciSelroom.setText("Room 7 - non/AC - Double Bed");
        }else if(ro7== 3){
            reservationID = ros7;

            Parent root2 = FXMLLoader.load(getClass().getResource("CheckoutWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Check Out");
            stage.setScene(new Scene(root2));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    public void r1OnAction(ActionEvent event) throws IOException {
        if (ro1 == 1) {
            r1.setStyle("-fx-background-color: green");
            ro1 = 0;
        }else if(ro1 == 0){
            resetButtons();
            reservedRoom();

            ciDprice.setText("Rs. 3000.00 per day");
            r1.setStyle("-fx-background-color: #dede01");
            ro1 =1;
            ciSelroom.setText("Room 1 - AC - Double Bed");
        }else if(ro1== 3){
            reservationID = ros1;

            Parent root2 = FXMLLoader.load(getClass().getResource("CheckoutWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Check Out");
            stage.setScene(new Scene(root2));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    public void r2OnAction(ActionEvent event) throws IOException {
        if (ro2 == 1) {
            r2.setStyle("-fx-background-color: green");
            ro2 = 0;
        }else if(ro2 == 0){
            resetButtons();
            reservedRoom();

            ciDprice.setText("Rs. 3000.00 per day");
            r2.setStyle("-fx-background-color: #dede01");
            ro2 =1;
            ciSelroom.setText("Room 2 - AC - Double Bed");
        }else if(ro2== 3){
            reservationID = ros2;

            Parent root2 = FXMLLoader.load(getClass().getResource("CheckoutWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Check Out");
            stage.setScene(new Scene(root2));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    public void r3OnAction(ActionEvent event) throws IOException {
        if (ro3 == 1) {
            r3.setStyle("-fx-background-color: green");
            ro3 = 0;
        }else if(ro3 == 0){
            resetButtons();
            reservedRoom();

            ciDprice.setText("Rs. 3000.00 per day");
            r3.setStyle("-fx-background-color: #dede01");
            ro3 =1;
            ciSelroom.setText("Room 3 - AC - Double Bed");
        }else if(ro3== 3){
            reservationID = ros3;

            Parent root2 = FXMLLoader.load(getClass().getResource("CheckoutWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Check Out");
            stage.setScene(new Scene(root2));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    public void r4OnAction(ActionEvent event) throws IOException {
        if (ro4 == 1) {
            r4.setStyle("-fx-background-color: green");
            ro4 = 0;
        }else if(ro4 == 0){
            resetButtons();
            reservedRoom();

            ciDprice.setText("Rs. 1000.00 per day");
            r4.setStyle("-fx-background-color: #dede01");
            ro4 =1;
            ciSelroom.setText("Room 4 - non/AC - Single Bed");
        }else if(ro1== 4){
            reservationID = ros4;

            Parent root2 = FXMLLoader.load(getClass().getResource("CheckoutWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Check Out");
            stage.setScene(new Scene(root2));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    public void r5OnAction(ActionEvent event) throws IOException {
        if (ro5 == 1) {
            r5.setStyle("-fx-background-color: green");
            ro5 = 0;
        }else if(ro5 == 0){
            resetButtons();
            reservedRoom();

            ciDprice.setText("Rs. 1000.00 per day");
            r5.setStyle("-fx-background-color: #dede01");
            ro5 =1;
            ciSelroom.setText("Room 5 - non/AC - Single Bed");
        }else if(ro5== 3){
            reservationID = ros5;

            Parent root2 = FXMLLoader.load(getClass().getResource("CheckoutWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Check Out");
            stage.setScene(new Scene(root2));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    public void r6nAction(ActionEvent event) throws IOException {
        if (ro6 == 1) {
            r6.setStyle("-fx-background-color: green");
            ro6 = 0;
        }else if(ro6 == 0){
            resetButtons();
            reservedRoom();

            ciDprice.setText("Rs. 1000.00 per day");
            r6.setStyle("-fx-background-color: #dede01");
            ro6 =1;
            ciSelroom.setText("Room 6 - non/AC - Single Bed");
        }else if(ro6== 3){
            reservationID = ros6;

            Parent root2 = FXMLLoader.load(getClass().getResource("CheckoutWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Check Out");
            stage.setScene(new Scene(root2));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    public void r8OnAction(ActionEvent event) throws IOException {
        if (ro8 == 1) {
            r8.setStyle("-fx-background-color: green");
            ro8 = 0;
        }else if(ro8 == 0){
            resetButtons();
            reservedRoom();

            ciDprice.setText("Rs. 1500.00 per day");
            r8.setStyle("-fx-background-color: #dede01");
            ro8 =1;
            ciSelroom.setText("Room 8 - non/AC - Double Bed");
        }else if(ro8== 3){
            reservationID = ros8;

            Parent root2 = FXMLLoader.load(getClass().getResource("CheckoutWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Check Out");
            stage.setScene(new Scene(root2));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    public void r9OnAction(ActionEvent event) throws IOException {
        if (ro9 == 1) {
            r9.setStyle("-fx-background-color: green");
            ro9 = 0;
        }else if(ro9 == 0){
            resetButtons();
            reservedRoom();

            ciDprice.setText("Rs. 1500.00 per day");
            r9.setStyle("-fx-background-color: #dede01");
            ro9 =1;
            ciSelroom.setText("Room 9 - non/AC - Double Bed");
        }else if(ro9== 3){
            reservationID = ros9;

            Parent root2 = FXMLLoader.load(getClass().getResource("CheckoutWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Check Out");
            stage.setScene(new Scene(root2));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    public void r10OnAction(ActionEvent event) throws IOException {
        if (ro10 == 1) {
            r10.setStyle("-fx-background-color: green");
            ro10 = 0;
        }else if(ro10 == 0){
            resetButtons();
            reservedRoom();

            ciDprice.setText("Rs. 1500.00 per day");
            r10.setStyle("-fx-background-color: #dede01");
            ro10 =1;
            ciSelroom.setText("Room 10 - non/AC - Double Bed");
        }else if(ro10== 3){
            reservationID = ros10;

            Parent root2 = FXMLLoader.load(getClass().getResource("CheckoutWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Check Out");
            stage.setScene(new Scene(root2));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    public void erCheckOnAction(ActionEvent event){
        ercDate = erDate.getValue().toString();
        ercTime = timeSlot.getValue().toString();
        checkEventDate();
    }

    private void checkEventDate(){
        String myStatement;
        int count=0;
        myStatement= "SELECT * FROM event where date= '"+ercDate+"' and Time= '"+ercTime+"'";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(myStatement);
            while (rs.next()) {
                count++;

            }

            if(count == 0){
                erPanel.setVisible(true);
                erError.setText("");
            }else{
                erPanel.setVisible(false);
                erError.setText("Not Available");
                Parent root2 = FXMLLoader.load(getClass().getResource("EditWindow.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Event Details");
                stage.setScene(new Scene(root2));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

            }

            } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }


    }

    public void erClearOnAction(ActionEvent event){
        erName.setText("");
        erDisc.setText("");
        erAdd.setText("");
        erNop.setText("");
        erId.setText("");
        erTp.setText("");
        pkgs.setItems(pkgsList);
        noPlates.setText("");
        erSubTotalVal.setText("");
        erTotalVal.setText("");
        erHall.setText("");
        erPayment.setText("");
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void erCalOnAction(ActionEvent event){
        if(!(erNop.getText().equals(""))){
            if(isNumeric(erNop.getText())){

                    erHall.setText("Rs. " + hall + ".00");
                    if (pkg == 1) {
                        plate = 990;
                    } else if (pkg == 2) {
                        plate = 790;
                    } else if (pkg == 3) {
                        plate = 690;
                    } else {

                    }
                    if (Integer.parseInt(erNop.getText()) >= 200) {
                        hall = 0;
                        erHall.setText("Free");
                    }
                    if(isNumeric(erDisc.getText())) {
                        if (!erDisc.getText().equals("")) {
                            discount = Double.parseDouble(erDisc.getText());
                            System.out.println(discount);
                        }
                    }
                    System.out.println(discount);
                    subTotal = Double.parseDouble(String.format("%.2f", (double) (Integer.parseInt(erNop.getText()) * plate)));
                    total = Double.parseDouble(String.format("%.2f", ((1.1*subTotal) + hall - discount)));
                    erSubTotalVal.setText("Rs. " + subTotal);
                    erTotalVal.setText("Rs. " + total);

            }else{
                    erError.setText("invalid input");
            }
        }else{
            erError.setText("Fill the number of plates field");
        }
    }

    public void erCheckoutrOnAction(ActionEvent event){
        String name=erName.getText();
        String add=erAdd.getText();
        String nop=erNop.getText();
        String Id=erId.getText();
        String tp=erTp.getText();
        double payment= 0;
        if(isNumeric(erPayment.getText())) {
            if (!erPayment.getText().equals("")) {
                payment = -Double.parseDouble(erPayment.getText());
            }
        }

        EventReservation er = new EventReservation(name,add,nop,Id,tp,discount,total,payment);
        erError.setText(er.mainProcess());

    }

}
