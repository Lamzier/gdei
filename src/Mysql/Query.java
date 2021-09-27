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
     * 查询寻物启事数据库总条数,无查询条件
     * @param name 数据库名称
     * @return 返回条数值
     *
     */
    public static int quertExampleAll(String name){
        return quertExampleAll(name , "");
    }

    /**
     * 查询寻物启事数据库总条数
     * @param name 数据库名称
     * @param where 查询条件
     * @return 返回条数值
     *
     */
    public static int quertExampleAll(String name, String where){
        String sql;
        if (where.length() <= 0){
            //没有查询条件
            sql = "SELECT count(1)  FROM " + name + ";";
        }else{
            //有查询条件
            sql = "SELECT count(1)  FROM " + name + " WHERE " + where + ";";
        }
        try {
            PreparedStatement preparedStatement = Start.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            //查询数据库
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            Log.Tips.WARM("MYSQL预处理语句错误");
        }
        return 0;
    }

    /**
     * 查询启事数据库
     * @param table 查询的数据库表
     * @param pages 查询的页码
     * @return 返回数据
     */
    public static Map<String , Object>[] quertNotice(Statement statement ,String table ,int pages){
        int start = (pages - 1) * 10;
        String sql = "select * from " + table + " where state = true order by `id` desc limit " + start + ",10;";
        Map <String , Object>[] searchRes = new Map[10];
        //查询结果map
        try {
            PreparedStatement preparedStatement = Start.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            //查询
            int x = 0;
            while (resultSet.next()){
                //查询所有数据
                searchRes[x] = new HashMap<>();
                searchRes[x].put("id", resultSet.getInt("id"));
                searchRes[x].put("openid", resultSet.getString("openid"));
                searchRes[x].put("nickName", resultSet.getString("nickName"));
                searchRes[x].put("time", resultSet.getString("time"));
                searchRes[x].put("yuanxi", resultSet.getString("yuanxi"));
                searchRes[x].put("conTitle", resultSet.getString("conTitle"));
                searchRes[x].put("conCon", resultSet.getString("conCon"));
                searchRes[x].put("conImage", resultSet.getString("conImage"));
                searchRes[x].put("wechatId", resultSet.getString("wechatId"));
                x++;
            }
            if (x == 0){
                searchRes = null;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            Log.Tips.WARM("MYSQL预处理语句错误");
            return null;
        }
        return searchRes;
    }

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
