package GetInfo;

import Request.NetWorkException;
import Request.Send;
import net.sf.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * 广动第二师范学院信息操作检查类
 */
public class GdeiInfo {
    /**
     * 登录操作
     * @param username 用户名
     * @param password 密码
     * @return 返回值
     */
    public static Map<String , Object> login(String username , String password){
        //设置登录URL
        Send send = new Send("https://tb.gdei.edu.cn/login");
        //设置协议头
        send.setRequestProperty("Connection" , "keep-alive");
        send.setRequestProperty("Accept" , "*/*");
        send.setRequestProperty("Origin" , "https://tb.gdei.edu.cn");
        send.setRequestProperty("X-Requested-With" , "XMLHttpRequest");
        send.setRequestProperty("User-Agent" , "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3877.400 QQBrowser/10.8.4506.400");
        send.setRequestProperty("Content-Type" , "application/x-www-form-urlencoded; charset=UTF-8");
        send.setRequestProperty("Referer" , "https://tb.gdei.edu.cn/login");
        send.setRequestProperty("Accept-Language" , "zh-CN,zh;q=0.9");
        String data =  "username=" + username +
                "&password=" + password;
        String text = null;
        try {
            text = send.doPost(data);
        } catch (NetWorkException e) {
            e.printStackTrace();
        }
        JSONObject jsondata = JSONObject.fromObject(text);
        Map<String , Object> res = new HashMap<>();
        res.put("cookie" , send.getRequestProperty("Set-Cookie"));
        res.put("data" , jsondata);
        send.disconnect();//关闭连接
        return res;
    }



}
