package org.mail.javamail;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by break on 2016/8/14.
 */
public class GetEmails {
   static Properties prop;
    static {
        prop = new Properties();
        InputStream in = Object.class.getResourceAsStream("/resource.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void fetchMail()
    {
        String account = prop.getProperty("emailaccount");
        String password = prop.getProperty("emailpassword");
        String pop3host = prop.getProperty("pop3host");
        String pop3port = prop.getProperty("pop3port");

        Properties properties = new Properties();
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.pop3.host", pop3host);
        properties.put("mail.pop3.port", pop3port);
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);
        try {
            Store store = emailSession.getStore("pop3s");
            store.connect(pop3host, account, password);
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            //获取未读数
            System.out.println(emailFolder.getUnreadMessageCount());
            //获取支持的操作
            Flags permanentFlags = emailFolder.getPermanentFlags();
            Flags.Flag[] userFlags = permanentFlags.getSystemFlags();
            for(Flags.Flag flag:userFlags)
                System.out.println(flag.toString());



        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

        System.out.println(prop.getProperty("emailaccount"));
        fetchMail();

    }
}
