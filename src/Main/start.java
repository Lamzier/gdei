package Main;

import Encoder.Base;
import GetInfo.GdeiInfo;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;
import java.util.Map;

/**
 * 启动类
 */
public class start {
    public static void main(String[] args) {
        //启动函数
        //Login login = new Login();
        //System.out.println(login.getUserInfo("asd"));
        Map<String , Object> map = GdeiInfo.login("liangzhanyi" , "20020614Lamzy..");
        System.out.println(map.get("cookie"));
        JSONObject json = (JSONObject) map.get("data");
        System.out.println(json.get("msg"));


    }
}
