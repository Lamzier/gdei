package Request;

import net.sf.json.JSONObject;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 接受请求类
 */
public class Receive{

    private final Map<String , String[]> data;
    private final Map<String , String> header;
    private final Method method;
    private final String ip;


    /**
     * 初始函数
     * @param data 传入数据
     */
    public Receive(Map<String , String[]> data , Map<String, String> header , Method method , String ip){
        //收到数据
        this.data = data;
        this.header = header;
        this.method = method;
        this.ip = ip;
        //赋予数据
    }

    /**
     * 回调输出结果
     * @return 返回结果
     */
    public String Retracement(){
        if (data == null){return Re_OTHER();}
        //处理过程
        String reData;
        switch (method){
            case GET:
                reData = Re_GET();
                break;
            case POST:
                reData = Re_POST();
                break;
            case OTHER:
                reData = Re_OTHER();
                break;
            default:
                reData = Re_OTHER();
                break;
        }
        return reData;
    }

    /**
     * get请求处理
     * @return 返回内容
     */
    public String Re_GET(){
        if (data.get("type") == null){return Re_OTHER();}
        String type = data.get("type")[0];
        String re = null;
        switch (type){
            default:
                re = Re_OTHER();
                break;
        }

        return re;
    }

    /**
     * POST请求处理
     * @return 返回内容
     */
    public String Re_POST(){
        if (data.get("type") == null){return Re_OTHER();}
        String type = data.get("type")[0];
        String re = null;
        switch (type){
            case "wechatLogin":
                re = Business.wechatLogin(this);
                break;
            default:
                //什么都不是
                re = Re_OTHER();
                break;
        }



        return re;
    }

    /**
     * OTHER请求处理
     * @return 返回内容
     */
    public String Re_OTHER(){
        Map<String , String> reDataMap = new LinkedHashMap<>();
        reDataMap.put("type" , "ERROR");
        reDataMap.put("code","-1");
        reDataMap.put("msg" , "运行时异常");
        JSONObject json = JSONObject.fromObject(reDataMap);
        return json.toString();
    }





    /**
     * 关闭资源占用
     */
    public void close(){

    }

    /**
     * 获取发送用户的ip地址 127.0.0.1 / 0:0:0:0:0:0:1
     * @return 获取内容
     */
    public String getIp() {
        return ip;
    }

    /**
     * 获取提交方式
     * @return 获取内容
     */
    public Method getMethod() {
        return method;
    }

    /**
     * 获取协议头
     * @return 获取内容
     */
    public Map<String, String> getHeader() {
        return header;
    }

    /**
     * 获取data
     * @return 获取内容
     */
    public Map<String, String[]> getData() {
        return data;
    }
}
