package Log;

/**+
 * 日志存储类
 */
public class Storage {
    /**
     * 日志数据存储变量
     */
    private static StringBuilder data = new StringBuilder("");

    /**
     * 添加日志数据
     * @param text 需要添加的数据
     */
    public static void addData(String text){
        data.append(text);
    }

    /**
     * 保存信息
     */
    public static void save(){

    }



}
