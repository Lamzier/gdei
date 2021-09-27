package Main;

import Mysql.MysqlException;
import Mysql.Start;
import Request.cookie;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class text {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i <= 11 ; i ++){
            jsonObject.put("K" +i , "V"+ i);
            System.out.println(i);
        }
        System.out.println(jsonObject);


    }
}
