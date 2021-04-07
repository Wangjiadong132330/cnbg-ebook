package com.cnbg.zs.ebook.common.lang;



import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import java.net.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/11 20:34
* @Description
*/
public class StringToolUtils {

private static final String SYMBOLS = "0123456789";
private static final Random RANDOM = new SecureRandom();

/**
* 获取长度为 6 的随机数字
* @return 随机数字
*/
public static String generatorRollNum() {
    char[] nonceChars = new char[6];

    for (int index = 0; index < nonceChars.length; ++index) {
        nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
    }

        return new String(nonceChars);
    }



    /**
    * SHA512 加密
    * @param pass 密码
    * @param salt 秘钥
    * @return
    */
    public static String digestPassword(String pass,String salt){
        String password = pass+salt;
        MessageDigest messageDigest = DigestUtils.getSha512Digest();
        byte[] bytes = messageDigest.digest(password.getBytes());
        return Hex.encodeHexString(bytes);
    }

    public static String getGeneratorUUID(){
        return  UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static List<Integer> stringListToIntegerList(String str){
        if(org.apache.commons.lang3.StringUtils.isBlank(str)){
            return null;
        }else{
            String[] fileArray = str.split(",");
            List<String> stringList = Arrays.asList(fileArray);
            List<Integer> idsList = stringList.stream().map(item->Integer.parseInt(item)).collect(Collectors.toList());
            return idsList;
        }

    }
    /**
    * 判断对象是否为空
    * @param obj
    * @return
    */
    public static boolean isEmptyObj(Object obj){
        return obj==null || org.apache.commons.lang3.StringUtils.isBlank(obj.toString());
    }

    /**
     * 根据参数返回字符串值
     * @param obj
     * @return
     */
    public static String isEmptyValue(Object obj){
        return isEmptyObj(obj)?null:obj.toString();
    }


    public static String arrayToString(String[] str){
        return org.apache.commons.lang3.StringUtils.join(str,",");
    }

    // 获取MAC地址的方法
    public static List<String> getMacList() throws Exception {
        java.util.Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            StringBuilder sb = new StringBuilder();
            ArrayList<String> tmpMacList=new ArrayList<>();
                while(en.hasMoreElements()){
                NetworkInterface iface = en.nextElement();
                List<InterfaceAddress> addrs = iface.getInterfaceAddresses();
                    for(InterfaceAddress addr : addrs) {
                        InetAddress ip = addr.getAddress();
                        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                    if(network==null){continue;}
                        byte[] mac = network.getHardwareAddress();
                    if(mac==null){continue;}
                        sb.delete( 0, sb.length() );
                    for (int i = 0; i < mac.length; i++) {sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));}
                        tmpMacList.add(sb.toString());
                    }        }
                    if(tmpMacList.size()<=0){return tmpMacList;}
                        /***去重，别忘了同一个网卡的ipv4,ipv6得到的mac都是一样的，肯定有重复，下面这段代码是。。流式处理***/
                        List<String> unique = tmpMacList.stream().distinct().collect(Collectors.toList());
                    return unique;
    }


}
