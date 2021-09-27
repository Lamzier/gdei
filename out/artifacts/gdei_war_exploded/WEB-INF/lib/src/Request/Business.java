package Request;

import FinalAll.Final;
import Mysql.MysqlException;
import Mysql.Query;
import Mysql.Start;
import Mysql.Update;
import net.sf.json.JSONObject;

import javax.swing.text.Style;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务处理类
 */
public class Business {

    /**
     * 返回错误JSON数据
     * @param type type类型
     * @param code 错误等级
     * @param msg 提示语
     * @return 返回JSON数据
     */
    private static JSONObject getErrorJson(String type , String code , String msg){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",type);
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        return jsonObject;
    }
    /**
     * 返回错误的JOSN数据 , 普通报错
     * @param type type类型
     * @return 返回值
     */
    private static JSONObject getErrorJson(String type){
        return getErrorJson(type , "500","运行时异常");
    }

    /**
     * 微信登陆授权获取openid
     * @return 返回值
     */
    public static String wechatLogin(Receive rec){
        Map<String , String[]> data = rec.getData();
        System.out.println(JSONObject.fromObject(data));
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + Final.APPID + //小程序appid
                "&secret=" + Final.APPSECRET + //小程序appSecret
                "&js_code=" + data.get("code")[0] +//登录时获取的code
                "&grant_type=authorization_code";//授权类型
        String re = null;
        Send send = new Send(url);//新建get连接
        try {
            re = send.doGet();//get访问
        } catch (NetWorkException e) {
            e.printStackTrace();
            return getErrorJson("ReWechatLogin").toString();
        }
        send.disconnect();//关闭连接
        /*{"session_key":"2bilqfLBhVWc3wsaH6lZ1A==","openid":"oJHY-5LdshKplME5h6ST_TQnpOtU"}
        openid 用户唯一标识
        seesion_key 会话密匙
        */
        JSONObject reJson = JSONObject.fromObject(re);
        //写入数据库,查询该用户数据
        Map<String, Object> UserData = null;
        try {
            Statement statement = Start.getConnection().createStatement();
            UserData = Query.quertOneOpenid(statement , (String) reJson.get("openid"));
            //获取用户数据
            if (UserData == null){
                //首次登陆小程序的用户,注册到数据库
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH点mm分ss秒");
                Map<String , Object> registerData = new HashMap<>();
                registerData.put("openid" , reJson.get("openid"));
                registerData.put("session_key" , reJson.get("session_key"));
                if (data.get("empower")[0].equalsIgnoreCase("true")){
                    //如果授权了
                    registerData.put("avatarUrl" , data.get("avatarUrl")[0]);
                    registerData.put("nickName" , data.get("nickName")[0]);
                    if (data.get("gender")[0].equals("1")){
                        //男生
                        registerData.put("gender" , "男");
                    }else {
                        registerData.put("gender" , "女");
                    }
                    registerData.put("country" , data.get("country")[0]);
                    registerData.put("province" , data.get("province")[0]);
                    registerData.put("city" , data.get("city")[0]);
                    registerData.put("language" , data.get("language")[0]);
                    registerData.put("empowerDate" , simpleDateFormat.format(date));
                }else{
                    //没有授权
                    registerData.put("avatarUrl" , "Empty");
                    registerData.put("nickName" , "Empty");
                    registerData.put("gender" , "Empty");
                    registerData.put("country" , "Empty");
                    registerData.put("province" , "Empty");
                    registerData.put("city" , "Empty");
                    registerData.put("language" , "Empty");
                    registerData.put("empowerDate" , "Empty");
                }
                registerData.put("phone" , "Empty");
                registerData.put("binding_username" , "Empty");
                registerData.put("binding_password" , "Empty");
                registerData.put("updataDate" , simpleDateFormat.format(date));
                registerData.put("registerDate" , simpleDateFormat.format(date));
                Update.registerUser(statement , registerData);
                //写入用户数据
                UserData = Query.quertOneOpenid(statement , (String) reJson.get("openid"));
                //再次查询
                statement.close();//关闭独立连接
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            try {
                throw new MysqlException("数据库断开连接！" , true);
            } catch (MysqlException ex) {
                ex.printStackTrace();
                Log.Tips.ERROR("数据库连接异常！请重试！");
            }
            return getErrorJson("ReWechatLogin").toString();
        } catch (MysqlException e) {
            e.printStackTrace();
            return getErrorJson("ReWechatLogin").toString();
        }
        //成功获取到了用户数据
        JSONObject resJson = new JSONObject();
        resJson.put("type","ReWechatLogin");
        resJson.put("code","0");
        resJson.put("msg","操作成功");
        resJson.put("session_key",UserData.get("session_key"));
        resJson.put("avatarUrl",UserData.get("avatarUrl"));
        resJson.put("nickName",UserData.get("nickName"));
        resJson.put("gender",UserData.get("gender"));
        resJson.put("country",UserData.get("country"));
        resJson.put("province",UserData.get("province"));
        resJson.put("city",UserData.get("city"));
        resJson.put("language",UserData.get("language"));
        resJson.put("phone",UserData.get("phone"));
        resJson.put("binding_username",UserData.get("binding_username"));
        resJson.put("registerDate",UserData.get("registerDate"));
        resJson.put("cookie",UserData.get("cookie"));
        String empowerDate = (String) UserData.get("empowerDate");
        //如果没有授权过
        if(empowerDate.equalsIgnoreCase("Empty")){
            //如果没有授权过
            resJson.put("empower","false");
        }else{
            resJson.put("empower","true");
        }
        return resJson.toString();
    }





}
