package FinalAll;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 常量类
 */
public class Final {
    /**
     * 小程序appid
     */
    @FinalAnnotation(value = "小程序appid")
    //public static final String APPID = "wx803a921cbb383c1d";
    public static final String APPID = "wx541659d553d53225";
    /**
     * 小程序AppSecret开发者密码
     */
    @FinalAnnotation(value = "小程序AppSecret开发者密码")
    //public static final String APPSECRET = "f42707f8204b06e486b24bafb880381e";
    public static final String APPSECRET = "e0d1b72acfcfb88e96df250110fb9742";

    /**
     * 数据库URL
     */
    @FinalAnnotation(value = "数据库URL")
    //private static final String MYSQL_URL = "jdbc:mysql://121.36.50.22:24410/text";
    //private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/gdei";
    private static String MYSQL_URL = "jdbc:mysql://120.24.53.107:3306/gdei";

    /**
     * 数据库用户名
     */
    @FinalAnnotation(value = "数据库用户名")
    private static final String MYSQL_USERNAME = "Lamzy";

    /**
     * 数据库密码
     */
    @FinalAnnotation(value = "数据库密码")
    //private static final String MYSQL_PASSWORD = "123456789asd..";//text 数据库
    private static final String MYSQL_PASSWORD = "ahfAhf5577WWAA++";//gdei 数据库

}
