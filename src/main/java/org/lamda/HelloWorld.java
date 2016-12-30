package org.lamda;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by break on 2016/8/7.
 */
public class HelloWorld {
    public static void main(String[] args2) throws UnsupportedEncodingException, ParseException {
//        System.out.println(System.currentTimeMillis());
//        List<String> args = Lists.newArrayList();
//        args.add("dds@xsss.com");
//        args.add("3677777@xrrs.com");
//        args.add("dsfdsfd@ddd.com");
//        args.add("uouoo@qq.com");
//        args.add("zhangyx@fxiaoke.com");
//        String[] strs = (String[]) args.toArray(new String[args.size()]);
//        System.out.println(strs);

//        Path path = Paths.get("mm.mp4");
//
//        String contentType = null;
//
//        try {
//
//            contentType = Files.probeContentType(path);
//
//        } catch (IOException e) {
//
//
//
//            e.printStackTrace();
//
//        }
//
//        System.out.println("File content type is : " + contentType);
//
//
//
//
//    String arg = "cid:1460969581forward_0$Ywenzhe.fan@boip.com.cn$Y1460969581.jpg";
//            String test = "1460969581forward_0$Ywenzhe.fan@boip.com.cn$Y1460969581.jpg";
//        String test2 = "1460969581forward_0Ywenz*he.fan@boip.com.cnY1460969581.jpg";;
//        test2 = test2.replaceAll("\\$", "RDS_CHAR_DOLLAR");// encode replacement;
//            arg = arg.replaceAll(Pattern.quote("cid:"+test), test2);
//        arg = arg.replaceAll("RDS_CHAR_DOLLAR", "\\$");
//
//        System.out.println(arg);
//
//        List<String> list = new ArrayList<>();
//        String [] strs = list.toArray(new String[list.size()]);
//        System.out.println("strs="+strs.length);
        //        Object[] objects = null;
//        System.out.println(Arrays.toString(objects));
//        int i =0; content +=",arg["+ Arrays.toString(args)+"]";
//      do{
//          i++;
//          if(i == 2){
//              break;
//
//          }
//          System.out.println("i="+i);
//      }while (true);
//        System.out.println(convert(content));
//        System.out.println(getFixStr(content));
//        String old = "<div style=\"overflow: hidden; background-color: rgb(255, 255, 255);\"><p><img style=\"max-width:100%;\" type=\"l\" " +
//                "src=\"http://www.fsfte2.com/open/emailproxy/web/resource?fileId=TN_b69313c1598447b7847976a67b3aec5e&fileName=无标题.png&messageId={messageId-messageId}\" data-height=\"undefined\" data-width=\"undefined\"/></p><p><br/></p><p><br/></p><p>------</p><p>zhangyx</p><p>FTE2的测试企业B公司</p><p><br/></p></div>";
//        String ss = "TN_b69313c1598447b7847976a67b3aec5e";
//        System.out.println(old.replaceAll(ss, "123"));
//        String s ="op";
//        int index_1 = s.lastIndexOf(".");
//        System.out.printf("index1="+index_1);
//        int index = s.lastIndexOf (".", (index_1- 1));
//        System.out.printf("index =" +index);
//        System.out.printf(s.substring(index+1) );
        String [] strs = new String[0];
        System.out.println(strs.length);
        String fsUserId="E.fs.5638";
        String[] fsUserIdSpilt = fsUserId.split(".");
        String hll = " ssd   dssd d  d  ";
        String hlls[] = hll.trim().split("\\s+");

        Date sendDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sendDate);
        calendar.add(Calendar.HOUR, -8);
        sendDate = calendar.getTime();


        String date = stampToDate(1482228769000L);
        System.out.println();
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate1 = dateFormat1.parse("2009-06-01");
        System.out.println(new Date().after(myDate1));
        String reg = "";

    }

    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    /**
     * 修改字符串中的unicode码
     *
     * @param s 源str
     * @return 修改后的str
     */
    public static String decode2(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\\' && chars[i + 1] == 'u') {
                char cc = 0;
                for (int j = 0; j < 4; j++) {
                    char ch = Character.toLowerCase(chars[i + 2 + j]);
                    if ('0' <= ch && ch <= '9' || 'a' <= ch && ch <= 'f') {
                        cc |= (Character.digit(ch, 16) << (3 - j) * 4);
                    } else {
                        cc = 0;
                        break;
                    }
                }
                if (cc > 0) {
                    i += 5;
                    sb.append(cc);
                    continue;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
    public static String stampToDate(long lt){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

}
