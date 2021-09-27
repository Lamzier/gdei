package Mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库查询类
 */
public class Query {

    /**
     * 查询一个用户的数据,通过openid查询
     */
    public static Map<String , Object> quertOneOpenid(Statement statement, String openid){
        PreparedStatement preparedStatement = null;//预处理语句
        ResultSet resultSet = null;
        Map<String , Object> data = null;
        try {
            preparedStatement = Start.getConnection().prepareStatement(
                    "select * from mainUserinfo where openid = ?"
            );
            preparedStatement.setString(1 , openid);
            resultSet = preparedStatement.executeQuery();//执行mysql操作
            if (resultSet.next()){
                data = new HashMap<>();
                data.put("id",resultSet.getInt("id"));
                data.put("session_key",resultSet.getString("session_key"));
                data.put("nickName",resultSet.getString("nickName"));
                data.put("phone",resultSet.getString("phone"));
                data.put("openid",resultSet.getString("openid"));
                data.put("binding_username",resultSet.getString("binding_username"));
                data.put("binding_password",resultSet.getString("binding_password"));
                data.put("updataDate" , resultSet.getString("updataDate"));
                data.put("registerDate" , resultSet.getString("registerDate"));
                data.put("avatarUrl" , resultSet.getString("avatarUrl"));
                data.put("gender" , resultSet.getString("gender"));
                data.put("country" , resultSet.getString("country"));
                data.put("province" , resultSet.getString("province"));
                data.put("city" , resultSet.getString("city"));
                data.put("language" , resultSet.getString("language"));
                data.put("empowerDate" , resultSet.getString("empowerDate"));
                data.put("cookie" , resultSet.getString("cookie"));
                //写入数据
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            Log.Tips.WARM("MYSQL预处理语句错误");
        }finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    //e.printStackTrace();
                    Log.Tips.WARM("resultSet关闭异常！但是问题不大！");
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    //e.printStackTrace();
                    Log.Tips.WARM("PreparedStatement关闭异常！但是问题不大!");
                }
            }
        }
        return data;
    }


}
