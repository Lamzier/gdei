package Encoder;

import org.apache.commons.codec.binary.Base64;

/**
 * BASE双向解密和加密类
 */
public class Base {

    /**
     * 解密BASE64
     * @param data 传入数据
     * @return 结果
     */
    public static String base64Decode(String data){
        Base64 base64 = new Base64();
        return new String(base64.decode(data));
    }

    /**
     * 加密BASE64
     * @param data 传入数据
     * @return 结果
     */
    public static String base64Encode(String data){
        return new String(Base64.encodeBase64(data.getBytes()));
    }
}
