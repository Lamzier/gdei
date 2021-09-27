package Mysql;

/**
 * 数据库异常类
 */
public class MysqlException extends Exception{
    // 提供无参数的构造方法
    public MysqlException() {
    }

    // 提供一个有参数的构造方法
    public MysqlException(String message) {
        super(message);// 把参数传递给Throwable的带String参数的构造方法
        Log.Tips.ERROR(message);
    }

    /**
     * 数据库异常类
     * @param message 异常提示语
     * @param restart 是否重启数据库
     */
    public MysqlException(String message , boolean restart){
        super(message);
        Log.Tips.ERROR(message);
        if (restart){
            try {
                Start.reStart();
            } catch (MysqlException e) {
                e.printStackTrace();
            }
        }
    }
}
