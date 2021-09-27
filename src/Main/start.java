package Main;

/*
import FinalAll.Final;
import Mysql.MysqlException;
import Mysql.Start;

 */
import Mysql.MysqlException;
import Mysql.Start;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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

}
