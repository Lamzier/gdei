package Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志文件提示类
 */
public class Tips {

     private static SimpleDateFormat simpleDate = new SimpleDateFormat("HH-mm-ss");
     private static SimpleDateFormat allDate = new SimpleDateFormat("yyyy年MM月dd日HH点mm分ss秒");

     /**
      * tip信息 默认前台显示
      * @param message 信息
      */
     public static void INFO(String message){
          INFO(message , true);
     }

     /**
      * 警告错误 默认前台显示
      * @param message 信息
      */
     public static void WARM(String message){
          WARM(message , true);
     }

     /**
      * 严重错误 默认前台显示
      * @param message 信息
      */
     public static void ERROR(String message){
          ERROR(message , true);//默认前台显示
     }

     /**
      * 严重错误
      * @param message tips
      * @param display 是否显示在前台
      */
     public static void ERROR(String message,boolean display){
          Date date = new Date();
          if (display) {
               System.err.println("[ERROR]" + simpleDate.format(date) + ":" + message);//显示报错信息
          }
          Storage.addData("[ERROR]" + allDate.format(date) + ":" + message);//把日志文件写入变量
     }

     /**
      * 警告错误
      * @param message tips
      * @param display 是否在前台显示
      */
     public static void WARM(String message, boolean display){
          Date date = new Date();
          if (display){
               System.out.println("[WARM]" + simpleDate.format(date) + ":" + message);//显示报错信息
          }
          Storage.addData("[WARM]" + allDate.format(date) + ":" + message);//把日志文件写入变量
     }

     /**
      * 提示信息
      * @param message tips
      * @param display 是否在前台显示
      */
     public static void INFO(String message, boolean display){
          Date date = new Date();
          if (display){
               System.out.println("[INFO]" + simpleDate.format(date) + ":" + message);//显示报错信息
          }
          Storage.addData("[INFO]" + allDate.format(date) + ":" + message);//把日志文件写入变量
     }


}
