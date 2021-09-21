package Request;

import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * 处理发送HTTP请求类
 */
public class Send {//继承HttpServlet类
    HttpURLConnection conn = null;
    java.net.URL url = null;

    /**
     * 初始化参数
     * @param URL 发送的URL数据
     * @return  成功与否
     */
    public Send(String URL){
        try {
            //创建远程url连接对象
            url = new URL(URL);
            //通过远程url连接对象打开一个连接，强转成HTTPURLConnection类
            conn = (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置协议头
     * @param key 协议钥匙
     * @param value 协议价值
     * @return 成功与否
     */
    public boolean setRequestProperty(String key, String value){
        conn.setRequestProperty(key , value);
        return true;
    }

    /**
     * 获取返回协议头
     * @param key 协议头钥匙
     * @return 返回协议头内容
     */
    public String getRequestProperty(String key){
        return conn.getHeaderField(key);
    }

    /**
     * 发送POST请求
     * @param JsonData 传输的json数据 String类
     * @return 返回提交值
     */
    public String doPost(String JsonData){
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();

        try{
            //处理json数据
            conn.setRequestMethod("POST");
            //发送POST请求必须设置为true
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //设置连接超时时间和读取超时时间
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(10000);
            if (conn.getRequestProperty("Content-Type") == null){
                conn.setRequestProperty("Content-Type", "application/json");
            }
            if (conn.getRequestProperty("Accept") == null) {
                conn.setRequestProperty("Accept", "application/json");
            }
            //获取输出流
            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(JsonData);//写入数据
            out.flush();
            out.close();
            //取得输入流，并使用Reader读取
            if (200 == conn.getResponseCode()){
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line;
                while ((line = in.readLine()) != null){
                    result.append(line);
                    //System.out.println(line);//测试调试输出
                }
            }else{
                System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
        return result.toString();
    }

    /**
     * 发送POST请求
     * @param JsonData 传输的json数据 map类
     * @return 返回提交值
     */
    public String doPost(Map<String,String> JsonData){
        String json = JSONObject.fromObject(JsonData).toString();//获取json数据
        String result = doPost(json);
        return result;
    }

    /**
     * 发送get数据请求
     * @return 返回传输值
     */
    public String doGet(){
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder result = new StringBuilder();
        try{
            conn.setRequestMethod("GET");
            //设置连接超时时间和读取超时时间
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(60000);
            if (conn.getRequestProperty("Accept") == null){
                conn.setRequestProperty("Accept", "application/json");
            }
            //发送请求
            conn.connect();
            //通过conn取得输入流，并使用Reader读取
            if (200 == conn.getResponseCode()){
                is = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;
                while ((line = br.readLine()) != null){
                    result.append(line);
                    //System.out.println(line);//调试输出
                }
            }else{
                System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(br != null){
                    br.close();
                }
                if(is != null){
                    is.close();
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
        return result.toString();
    }

    /**
     * 关闭资源连接
     * @return 成功与否
     */
    public boolean disconnect(){
        conn.disconnect();
        return true;
    }

}
