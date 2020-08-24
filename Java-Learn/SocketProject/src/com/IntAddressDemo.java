package com;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class IntAddressDemo {
    static Logger logger=Logger.getLogger("info");
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress=InetAddress.getByName("192.168.50.73");
        // inetAddress.getHostName(); 获取主机名
        //inetAddress.getHostAddress(); 获取主机ip
        logger.info("获取getHostName的值 : "+inetAddress.getHostName());
        logger.info("获取getHostAddress的值 : "+inetAddress.getHostAddress());
        logger.info("获取getCanonicalHostName的值 : "+inetAddress.getCanonicalHostName());
        logger.info("获取getByName的值 : "+inetAddress.getByName("3SONG"));


    }
}
