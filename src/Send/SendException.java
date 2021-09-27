package Send;

/**
 * 发送异常类
 */
public class SendException extends Exception{
    // 提供无参数的构造方法
    public SendException() {
    }

    // 提供一个有参数的构造方法
    public SendException(String message) {
        super(message);// 把参数传递给Throwable的带String参数的构造方法
        Log.Tips.WARM(message);
    }
}
