package Request.BusinessProcessing;

import Mysql.MysqlException;
import Mysql.Query;
import Mysql.Start;
import Request.Receive;
import net.sf.json.JSONObject;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务处理-失物招领
 */
public class lostGain {

    /**
     * 招领启事获取信息
     * @param rec 传入参数
     * @return JSON文本
     */
    public static String recruitmentNotice(Receive rec){
        Map<String , String[]> data = rec.getData();
        if (data.get("cookie") == null || data.get("cookie")[0] == null || data.get("cookie")[0].length() < 5){
            //cookie 验证错误
            return universal.getErrorJson("ReRecruitmentNotice" , "501" , "cookie异常!").toString();
        }
        if (data.get("openid") == null || data.get("openid")[0] == null || data.get("openid")[0].length() < 5){
            //cookie 验证错误
            return universal.getErrorJson("ReRecruitmentNotice" , "502" , "openid异常!").toString();
        }
        Statement statement;
        try {
            statement = Start.getConnection().createStatement();
        } catch (SQLException e) {
            //e.printStackTrace();
            try {
                throw new MysqlException("数据库断开连接！" , true);
                //重新连接数据库
            } catch (MysqlException ex) {
                ex.printStackTrace();
            }
            return universal.getErrorJson("ReRecruitmentNotice" , "-2" , "运行时连接异常").toString();
        }
        String cookie = data.get("cookie")[0];
        String openid = data.get("openid")[0];
        //核对身份
        Map<String, Object> UserData = Query.quertOneOpenid(statement, openid);
        //获取用户信息
        if (UserData == null){
            //没有此用户信息
            return universal.getErrorJson("ReRecruitmentNotice" , "-1" , "运行时异常").toString();
        }
        if (!UserData.get("cookie").equals(cookie)){
            //cookie不正确
            return universal.getErrorJson("ReRecruitmentNotice" , "501" , "cookie异常,或已经过期").toString();
        }
        int pages = Query.quertExampleAll("recruitmentNotice" , "state = true");
        //总条数
        if (pages == 0){
            //没有内容
            return emptyMysql("ReRecruitmentNotice",cookie).toString();
        }
        //如果有内容
        if (data.get("inpages") == null || data.get("inpages")[0] == null || data.get("inpages")[0].length() < 1){
            //验证inpages
            return universal.getErrorJson("运行时异常!").toString();
        }
        int searchPages = Integer.parseInt(data.get("inpages")[0]);
        //获取需要查询的页面
        Map<String , Object>[] mysqlData = Query.quertNotice(statement ,"recruitmentNotice" ,searchPages);
        //获取数据库内容
        if (mysqlData == null){
            //没有内容
            return emptyMysql("ReRecruitmentNotice",cookie).toString();
        }
        //对数据进行处理
        for (int i = 0 ; i < 10 ; i ++){
            //遍历map
            if (mysqlData[i] == null){
                break;//跳出循环
            }
            String lsOpenid = (String)mysqlData[i].get("openid");
            if (lsOpenid == null){
                break;//跳出循环
            }
            mysqlData[i].remove("openid");//删除当前openid
            //如果有这个值
            Map<String , Object> userInfoOne = Query.quertOneOpenid(statement , lsOpenid);
            //获取该用户的信息
            if (userInfoOne == null){
                //如果为空直接跳过
                continue;
            }
            mysqlData[i].put("avatarUrl", userInfoOne.get("avatarUrl"));
        }
        try {
            if (statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            Log.Tips.WARM("statement关闭失败，但是问题不大");
        }
        //添加头像
        Map<String , Object> reData = new HashMap<>();
        reData.put("type" , "ReRecruitmentNotice");
        reData.put("code" , "0");
        reData.put("msg" , "操作成功");
        reData.put("cookie" , cookie);
        reData.put("data" , mysqlData);
        reData.put("pages" , pages);
        reData.put("inpages" , searchPages);
        return JSONObject.fromObject(reData).toString();
    }

    /**
     * 寻物启事获取信息
     * @param rec 传入参数
     * @return JSON文本
     */
    public static String searchNotice(Receive rec){
        Map<String , String[]> data = rec.getData();
        if (data.get("cookie") == null || data.get("cookie")[0] == null || data.get("cookie")[0].length() < 5){
            //cookie 验证错误
            return universal.getErrorJson("ReSearchNotice" , "501" , "cookie异常!").toString();
        }
        if (data.get("openid") == null || data.get("openid")[0] == null || data.get("openid")[0].length() < 5){
            //cookie 验证错误
            return universal.getErrorJson("ReSearchNotice" , "502" , "openid异常!").toString();
        }
        Statement statement;
        try {
            statement = Start.getConnection().createStatement();
        } catch (SQLException e) {
            //e.printStackTrace();
            try {
                throw new MysqlException("数据库断开连接！" , true);
                //重新连接数据库
            } catch (MysqlException ex) {
                ex.printStackTrace();
            }
            return universal.getErrorJson("ReSearchNotice" , "-2" , "运行时连接异常").toString();
        }
        String cookie = data.get("cookie")[0];
        String openid = data.get("openid")[0];
        //核对身份
        Map<String, Object> UserData = Query.quertOneOpenid(statement, openid);
        //获取用户信息
        if (UserData == null){
            //没有此用户信息
            return universal.getErrorJson("ReSearchNotice" , "-1" , "运行时异常").toString();
        }
        if (!UserData.get("cookie").equals(cookie)){
            //cookie不正确
            return universal.getErrorJson("ReSearchNotice" , "501" , "cookie异常,或已经过期").toString();
        }
        int pages = Query.quertExampleAll("searchNotice" , "state = true");
        //总条数
        if (pages == 0){
            //没有内容
            return emptyMysql("ReSearchNotice",cookie).toString();
        }
        //如果有内容
        if (data.get("inpages") == null || data.get("inpages")[0] == null || data.get("inpages")[0].length() < 1){
            //验证inpages
            return universal.getErrorJson("运行时异常!").toString();
        }
        int searchPages = Integer.parseInt(data.get("inpages")[0]);
        //获取需要查询的页面
        Map<String , Object>[] mysqlData = Query.quertNotice(statement ,"searchNotice" ,searchPages);
        //获取数据库内容
        if (mysqlData == null){
            //没有内容
            return emptyMysql("ReSearchNotice",cookie).toString();
        }
        //对数据进行处理
        for (int i = 0 ; i < 10 ; i ++){
            //遍历map
            if (mysqlData[i] == null){
                break;//跳出循环
            }
            String lsOpenid = (String)mysqlData[i].get("openid");
            if (lsOpenid == null){
                break;//跳出循环
            }
            mysqlData[i].remove("openid");//删除当前openid
            //如果有这个值
            Map<String , Object> userInfoOne = Query.quertOneOpenid(statement , lsOpenid);
            //获取该用户的信息
            if (userInfoOne == null){
                //如果为空直接跳过
                continue;
            }
            mysqlData[i].put("avatarUrl", userInfoOne.get("avatarUrl"));
        }
        try {
            if (statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            Log.Tips.WARM("statement关闭失败，但是问题不大");
        }
        //添加头像
        Map<String , Object> reData = new HashMap<>();
        reData.put("type" , "ReSearchNotice");
        reData.put("code" , "0");
        reData.put("msg" , "操作成功");
        reData.put("cookie" , cookie);
        reData.put("data" , mysqlData);
        reData.put("pages" , pages);
        reData.put("inpages" , searchPages);
        return JSONObject.fromObject(reData).toString();
    }

    /**
     * 空数据时返回jison
     * @param type 请求类型
     * @param cookie 请求cookie
     * @return 空数据时
     */
    private static JSONObject emptyMysql(String type, String cookie){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type" , type);
        jsonObject.put("code" , 0);
        jsonObject.put("msg","操作成功");
        jsonObject.put("cookie", cookie);
        jsonObject.put("data" , "Empty");
        jsonObject.put("pages",0);
        jsonObject.put("inpages",1);
        return jsonObject;
    }




}
