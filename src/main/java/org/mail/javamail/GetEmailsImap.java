package org.mail.javamail;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by break on 2016/8/14.
 */
public class GetEmailsImap {
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
        String imaphost = prop.getProperty("imaphost");
        String imapport = prop.getProperty("imapport");

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imap.host", imaphost);
        props.setProperty("mail.imap.port", imapport);
        Session emailSession = Session.getDefaultInstance(props);
        try {
            Store store = emailSession.getStore("imap");
            store.connect(imaphost, account, password);
            Folder indexBoxFolder = store.getFolder("INBOX");
            indexBoxFolder.open(Folder.READ_WRITE);
            System.out.println("getUnreadMessageCount,{}"+indexBoxFolder.getUnreadMessageCount());
            System.out.println("messagecount,{}"+indexBoxFolder.getMessageCount());
            Flags permanentFlags = indexBoxFolder.getPermanentFlags();
            Flags.Flag[] userFlags = permanentFlags.getSystemFlags();
            for(Flags.Flag flag:userFlags)
                    System.out.println(flag.toString());

            //获取所有邮件
            Message[] messages = indexBoxFolder.getMessages(2,5);
            for(Message message : messages){
                System.out.println(message.getMessageNumber());
            }
            Message result = indexBoxFolder.getMessage(12);
            System.out.println(result.getSubject());

            boolean isDeleted = result.isExpunged();
            System.out.println("is deleted succes :" +isDeleted);

            result.setFlag(Flags.Flag.ANSWERED,true);
            indexBoxFolder.close(true);

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

    public static void deleteMessage(Message message){
        try {
            message.setFlag(Flags.Flag.DELETED,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
