package Main;

import FinalAll.Final;
import Mysql.MysqlException;
import Mysql.Start;
import Request.cookie;
import Send.Email;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class text {
    public static void main(String[] args) {
        Email email = new Send.Email();
        for(int i = 0 ; i < 10 ; i ++) {
            email.sendMail("asd", "asd", "1255461704@qq.com");
        }
    }
}
