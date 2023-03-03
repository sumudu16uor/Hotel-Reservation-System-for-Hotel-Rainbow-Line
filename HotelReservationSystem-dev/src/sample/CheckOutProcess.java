package sample;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class CheckOutProcess {
    DBconnect mdc = new DBconnect();
    Connection con = getConnection();
    Statement stmt;

    DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String coDate,coTime,ciDate,hh;
    private long days=0, total=0;

    CheckOutProcess(String coDate,String coTime){
        this.coDate =coDate;
        this.coTime=coTime;
    }


    private Connection getConnection() {
        Connection myConn = mdc.getMyConnection();
        return myConn;
    }

    public String mainProcess(){
        return validateInputs();
    }

    private int selectPrice(int no){
        if(no<=3){
            return 3000;
        }else if(no<=6){
            return 1000;
        }else{
            return 1500;
        }
    }

    private String validateInputs(){
        String myStatement;

        if(!(coDate.equals("") || coTime.equals(""))){
            myStatement="select * from reservation where ReservationID="+MainWindowController.reservationID;
            try {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(myStatement);
                while (rs.next()) {
                    ciDate = rs.getString("Check_in_date");
                    char ch;
                    for(int i=0;i<10;i++){
                        ch = coDate.charAt(i);
                        if(ch == '/'){
                            coDate = coDate.replace('/','-');
                        }
                    }

                    LocalDate localDate1 = LocalDate.parse(ciDate);
                    LocalDate localDate2 = LocalDate.parse(coDate);

                    days = Period.between(localDate1, localDate2).getDays();
                    total = days * selectPrice(rs.getInt("RoomNo"));
                    return "Rs. "+ total;

                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }



        }

        return null;

    }

    public String checkout(){

        return "success";
    }

}
