package GetInfo;


import FinalAll.Final;
import Request.Send;

/**
 * 登录类
 */
public class Login {
    /**
     * 获取登录用户数据
     * @param code 登录凭证
     * @return
     */
    public String getUserInfo(String code){
        Send send = new Send();
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + Final.APPID + //小程序appid
                "&secret=" + Final.APPSECRET + //小程序appSecret
                "&js_code=" + code +//登录时获取的code
                "&grant_type=authorization_code";//授权类型
        return send.doGet(url);//返回查询结果
    }


}
