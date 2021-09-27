package Request;

/**
 * cookie类 32 位数组字母组合
 */
public class cookie {

    private static char[] c = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h',
            'i','j','k','l','n','m','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D',
            'E','F','G','H','I','J','K','L','N','M','O','P','Q','R','S','T','U','V','X','Y','Z'};
    //cookie的组成元素

    /**
     * 获取32位COOKIE
     * @return 返回cookie字符串
     */
    public static String getCookie(){
        int cLen = c.length;
        int cookielen = 32;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cookielen; i++){
            stringBuilder.append(c[(int)(Math.random() * cLen)]);
        }
        return stringBuilder.toString();
    }



}
