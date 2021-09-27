package Main;

import Mysql.MysqlException;
import Mysql.Start;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * tomcat启动类
 */
public class start implements ServletContextListener {

    /**
     * tomcat启动时
     * @param arg0 参数
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        Log.Tips.INFO("----------------------------------------------------");
        Log.Tips.INFO("TOMCAT服务器正在开启！");
        try {
            Start start = new Mysql.Start();
        } catch (MysqlException e) {
            e.printStackTrace();
            //数据库启动失败!
        }
        Log.Tips.INFO("TOMCAT服务器已开启！");
    }

    /**
     * tomcat关闭时
     * @param arg0 参数
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0){
        Log.Tips.INFO("TOMCAT服务器正在关闭！");
        Log.Tips.INFO("TOMCAT服务器已关闭！");
    }


    public static void main(String[] args) {
        //启动函数



    }
}
