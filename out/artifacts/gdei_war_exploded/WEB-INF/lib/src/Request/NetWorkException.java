package Request;

/**
 * 网络异常类
 */
public class NetWorkException extends Exception{
    // 提供无参数的构造方法
    public NetWorkException() {
    }

    // 提供一个有参数的构造方法
    public NetWorkException(String message) {
        super(message);// 把参数传递给Throwable的带String参数的构造方法
        Log.Tips.ERROR(message);
    }
}
