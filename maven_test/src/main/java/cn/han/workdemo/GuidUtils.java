package cn.han.workdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.util.*;
import java.security.*;

/**
 * Created by zhongbo on 2020/12/17.
 */
public class GuidUtils extends Object{
    private static final Logger logger = LoggerFactory.getLogger(GuidUtils.class);
    public String valueBeforeMD5 = "";
    public String valueAfterMD5 = "";
    private static Random myRand;
    private static SecureRandom mySecureRand;

    static {
        mySecureRand = new SecureRandom();
        long secureInitializer = mySecureRand.nextLong();
        myRand = new Random(secureInitializer);
    }

    /**
     * 生成32位GUID码
     * @return
     */
    public static String generatorGUID() {
        GuidUtils myGUID = new GuidUtils();
        return myGUID.toString();
    }

    public GuidUtils() {
        getRandomGUID(false);
    }

    public GuidUtils(boolean secure) {
        getRandomGUID(secure);
    }

    private void getRandomGUID(boolean secure) {
        MessageDigest md5 = null;
        StringBuffer sbValueBeforeMD5 = new StringBuffer();

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error: " + e);
        }

        try {
            InetAddress id = InetAddress.getLocalHost();
            long time = System.currentTimeMillis();
            long rand = 0;

            if (secure) {
                rand = mySecureRand.nextLong();
            } else {
                rand = myRand.nextLong();
            }

            sbValueBeforeMD5.append(id.toString());
            sbValueBeforeMD5.append(":");
            sbValueBeforeMD5.append(Long.toString(time));
            sbValueBeforeMD5.append(":");
            sbValueBeforeMD5.append(Long.toString(rand));

            valueBeforeMD5 = sbValueBeforeMD5.toString();
            md5.update(valueBeforeMD5.getBytes());

            byte[] array = md5.digest();
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < array.length; ++j) {
                int b = array[j] & 0xFF;
                if (b < 0x10)
                    sb.append('0');
                sb.append(Integer.toHexString(b));
            }

            valueAfterMD5 = sb.toString();

        } catch (UnknownHostException e) {
            logger.error("Error:" + e);
        }
    }

    public String toString() {
        String raw = valueAfterMD5.toUpperCase();
        StringBuffer sb = new StringBuffer();
        sb.append(raw.substring(0, 8));
        sb.append(raw.substring(8, 12));
        sb.append(raw.substring(12, 16));
        sb.append(raw.substring(16, 20));
        sb.append(raw.substring(20));
        return sb.toString();
    }

    /*
     * test of class
     */
    public static void main(String[] args) {

        GuidUtils myGUID = new GuidUtils(true);

        System.out.println("Seeding String=" + myGUID.valueBeforeMD5);
        System.out.println("rawGUID=" + myGUID.valueAfterMD5);
        System.out.println("RandomGUID=" + myGUID.toString());

    }
}
