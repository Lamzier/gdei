package Mysql;

import Log.Tips;
import Request.cookie;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * 数据库修改类
 */
public class Update {


    /**
     * 用户注册到数据库里
     * @param statement 数据库索引
     * @param data 注册的数据
     */
    public static void registerUser(Statement statement , Map<String , Object> data) throws MysqlException{
        String sql = "INSERT INTO mainUserinfo(" +
                "`openid`,`session_key`,`avatarUrl`,`nickName`,`gender`,`country`,`province`,`city`," +
                "`language`,`phone`,`binding_username`,`binding_password`,`updataDate`,`registerDate`," +
                "`empowerDate`,`cookie`" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement = Start.getConnection().prepareStatement(sql);
            preparedStatement.setString(1 , (String) data.get("openid"));
            preparedStatement.setString(2 , (String) data.get("session_key"));
            preparedStatement.setString(3 , (String) data.get("avatarUrl"));
            preparedStatement.setString(4 , (String) data.get("nickName"));
            preparedStatement.setString(5 , (String) data.get("gender"));
            preparedStatement.setString(6 , (String) data.get("country"));
            preparedStatement.setString(7 , (String) data.get("province"));
            preparedStatement.setString(8 , (String) data.get("city"));
            preparedStatement.setString(9 , (String) data.get("language"));
            preparedStatement.setString(10 , (String) data.get("phone"));
            preparedStatement.setString(11 , (String) data.get("binding_username"));
            preparedStatement.setString(12 , (String) data.get("binding_password"));
            preparedStatement.setString(13 , (String) data.get("updataDate"));
            preparedStatement.setString(14 , (String) data.get("registerDate"));
            preparedStatement.setString(15 , (String) data.get("empowerDate"));
            preparedStatement.setString(16 , cookie.getCookie());
            //System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();//执行操作数据库
            //完成添加数据
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new MysqlException("数据库操作异常！");
        }
    }

    /**
     * 数据库初始化创建表
     */
    public static void initCreateTable(Statement statement){
        String sql = "CREATE TABLE IF NOT EXISTS mainUserinfo(" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "openid VARCHAR(64) NOT NULL," +
                "session_key VARCHAR(64) NOT NULL," +
                "cookie VARCHAR(64) NOT NULL," +
                "avatarUrl VARCHAR(256) NOT NULL," +
                "nickName VARCHAR(64) NOT NULL," +
                "gender VARCHAR(8) NOT NULL," +
                "country VARCHAR(64) NOT NULL," +
                "province VARCHAR(64) NOT NULL," +
                "city VARCHAR(64) NOT NULL," +
                "language VARCHAR(64) NOT NULL," +
                "phone VARCHAR(32) NOT NULL," +
                "binding_username VARCHAR(64) NOT NULL," +
                "binding_password VARCHAR(64) NOT NULL," +
                "updataDate VARCHAR(64) NOT NULL," +
                "registerDate VARCHAR(64) NOT NULL," +
                "empowerDate VARCHAR(64) NOT NULL" +
                ")engine=InnoDB default charset=utf8;";
        try {
            statement.executeUpdate(sql);
            //初始化数据库
        } catch (SQLException e) {
            e.printStackTrace();
            Tips.ERROR("初始化过程创建数据库表失败");
        }



    }
}
