package Send;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件类
 */
public class Email {
    private  String smtp_host = "smtp.qq.com"; //QQ邮件服务器
    private Map<String , String>[] from = new Map[2];//账户信息
    private  Properties properties = new Properties();
    //2948725521@qq.com
    //gkdebpusexjodgac

    /**
     * 构造方法
     */
    public Email(){
        properties.setProperty("mail.smtp.host", smtp_host);
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        //设置发件人信息
        from[0] = new HashMap<>();
        from[0].put("username" , "1255461704@qq.com");
        from[0].put("password" , "rszniegvviolbafb");
        from[1] = new HashMap<>();
        from[1].put("username" , "2948725521@qq.com");
        from[1].put("password" , "gkdebpusexjodgac");
    }

    /**
     * 获取随机MAP
     * @return map
     */
    private Map<String , String> getFrom(){
        int index = (int)(Math.random() * from.length);
        return from[index];
    }

    /**
     * 邮件发送类
     * @param subject 标题
     * @param content 内容
     * @param to 送达地址
     */
    public void sendMail(String subject, String content, String to){
        for (int i = 0 ; i < 5 ; i++) {
            //重试次数
            Map<String, String> fromMap = getFrom();
            String username = fromMap.get("username");
            String password = fromMap.get("password");
            Session session = Session.getInstance(properties);
            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(username));
                message.setRecipient(RecipientType.TO, new InternetAddress(to));
                message.setSubject(subject);
                message.setContent(content, "text/html;charset=utf-8");
                Transport transport = session.getTransport();
                transport.connect(smtp_host, username, password);
                transport.sendMessage(message, message.getAllRecipients());
            } catch (Exception e) {
                //e.printStackTrace();
                try {
                    throw new SendException(subject + "邮件发送失败(" + i + ")...To:" + to);
                } catch (SendException ex) {
                    ex.printStackTrace();
                }
                continue;//失败继续循环
            }
            break;//成功跳出循环
        }
    }












}
