package sample;

import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventReservation {

    DBconnect mdc = new DBconnect();
    Connection con = getConnection();
    Statement stmt;

    private String name,add,nop,Id,tp;
    private double discount,total,payment;

    EventReservation(String name,String add,String nop,String Id,String tp,double discount,double total,double payment){
        this.name=name;
        this.add=add;
        this.nop=nop;
        this.Id=Id;
        this.tp=tp;
        this.discount=discount;
        this.total=total;
        this.payment=payment;
    }

    private Connection getConnection() {
        Connection myConn = mdc.getMyConnection();
        return myConn;
    }

    public String mainProcess(){
        return validateInputs();
    }

    private String validateInputs(){
        if (name.equals("") || Id.equals("") || add.equals("") || tp.equals("") || payment == 0) {
            return "one or more fields are empty";
        } else {
            if(total != 0){
                String regex = "\\d{10}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(tp);
                if(matcher.matches()){
                    if(Id.length() > 9){

                    }else{
                        return "invalid id";
                    }
                }else{
                    return "invalid Phone number";
                }
            }else{
                return "Please calculate before checking out";
            }
        }
        return resreveDate();
    }

    private String resreveDate(){
        return "success";
    }
}
