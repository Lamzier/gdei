package Request.BusinessProcessing;

import net.sf.json.JSONObject;

/**
 * 业务处理 -- 通用业务处理方法
 */
public class universal {
    /**
     * 返回错误JSON数据
     * @param type type类型
     * @param code 错误等级
     * @param msg 提示语
     * @return 返回JSON数据
     */
    public static JSONObject getErrorJson(String type , String code , String msg){
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
    public static JSONObject getErrorJson(String type){
        return getErrorJson(type , "500","运行时异常");
    }

}
