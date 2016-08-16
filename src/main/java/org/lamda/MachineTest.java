package org.lamda;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by zhangyx on 2016/8/16.
 */
public class MachineTest {
    public static void main(String[] args) {
        InetAddress ia=null;
        try {
            ia=ia.getLocalHost();
            String localname=ia.getHostName();
            String localip=ia.getHostAddress();
            System.out.println("本机名称是："+ localname);
            System.out.println("本机的ip是 ："+localip);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
