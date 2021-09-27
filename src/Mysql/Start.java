package Mysql;

import FinalAll.Final;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库启动类
 */
public class Start {
    private static String MYSQL_URL;
    private static String MYSQL_USERNAME;
    private static String MYSQL_PASSWORD;
    private static Connection connection = null;

    /**
     * 启动类构造方法
     */
    public Start()throws MysqlException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            throw new MysqlException("驱动类加载失败！找不到驱动类!");
        }
        Final fi = new Final();
        Class f = fi.getClass();
        //获取该类
        Field[] fields = f.getDeclaredFields();
        //反射
        for(int i = 0 ; i < fields.length ; i ++){
            Field field = fields[i];
            if (field.getName().equals("MYSQL_URL")){
                field.setAccessible(true);//设置权限
                try {
                    MYSQL_URL = (String) field.get(fi);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }else if (field.getName().equals("MYSQL_USERNAME")){
                field.setAccessible(true);//设置权限
                try {
                    MYSQL_USERNAME = (String) field.get(fi);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }else if (field.getName().equals("MYSQL_PASSWORD")){
                field.setAccessible(true);//设置权限
                try {
                    MYSQL_PASSWORD = (String) field.get(fi);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        //完成获取基本信息
        try {
            connection = DriverManager.getConnection(MYSQL_URL , MYSQL_USERNAME , MYSQL_PASSWORD);
            //连接数据库
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new MysqlException("数据库连接失败！可能是账号密码错误！");
        }
        initMysql();//初始化数据库
    }

    /**
     * 初始化数据库
     */
    public void initMysql() throws MysqlException{
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new MysqlException("获取statement失败！正在重新连接数据库" , true);
        }
        Update.initCreateTable(statement);
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            Log.Tips.WARM("STATEMENT关闭失败，但是问题不大！");
        }
    }

    /**
     * 数据库重启
     * @return 成功与否
     */
    public static boolean reStart() throws MysqlException{
        try {
            if (connection != null) {
                connection.close();//关闭数据库连接
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            Log.Tips.WARM("数据库关闭连接失败！但是影响不大可以忽略！");//提示信息
        }
        try {
            connection = DriverManager.getConnection(MYSQL_URL , MYSQL_USERNAME , MYSQL_PASSWORD);
            //连接数据库
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new MysqlException("数据库连接失败！可能是账号密码错误！");
        }
        Log.Tips.INFO("数据库启动完毕！");
        return true;
    }

    /**
     * 获取数据库连接
     * @return 连接索引
     */
    public static Connection getConnection() {
        return connection;
    }
}
