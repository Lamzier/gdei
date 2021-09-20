package Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * AES对称加密和解密
 */
public class ASE {
    private static KeyGenerator keygen ;
    private static SecretKey secretKey;
    private static Cipher cipher;
    private static ASE security = null;

    public static ASE getInstance() throws Exception{
        if(security == null){
            security = new ASE();
            keygen = KeyGenerator.getInstance("AES");
            secretKey = keygen.generateKey();
            cipher = Cipher.getInstance("AES");
        }
        return security;
    }

    /**
     * ASE加密
     * @param str 传入数据
     * @return 返回
     * @throws Exception 数据异常
     */
    public String encrypt(String str) throws Exception{
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);

        byte [] src =  str.getBytes();
        byte [] enc = cipher.doFinal(src);

        return parseByte2HexStr(enc);
    }

    /**
     * ASE解密
     * @param str 传入数据
     * @return 返回
     * @throws Exception 数据异常
     */
    public String decrypt(String str) throws Exception{
        cipher.init(Cipher.DECRYPT_MODE,secretKey);

        byte[] enc = parseHexStr2Byte(str);
        byte [] dec = cipher.doFinal(enc);

        return new String(dec);
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
/*
ASE.getInstance().encrypt(str) //加密
ASE.getInstance().decrypt(ss) //解密
 */
}