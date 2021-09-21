package GetInfo;


import FinalAll.Final;
import Request.Send;

/**
 * 微信小程序获取类
 */
public class WechatInfo {
    /**
     * 获取登录用户数据
     * @param code 登录凭证
     * @return
     */
    public String getUserInfo(String code){

        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + Final.APPID + //小程序appid
                "&secret=" + Final.APPSECRET + //小程序appSecret
                "&js_code=" + code +//登录时获取的code
                "&grant_type=authorization_code";//授权类型
        Send send = new Send(url);
        return send.doGet();//返回查询结果
    }


}
