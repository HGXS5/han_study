package cn.han.workdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

/**
 * @author luchong
 * @version 2020 11.05 上午10:34:02
 * @notes Getting the ip&port address of the current machine using Java
 */
public class NetworkUtils {
    private static String localAddress = "127.0.0.1";
    private static Logger logger = LoggerFactory.getLogger(NetworkUtils.class);
    public static String getAvailIp(){
        List<String> nicList = getNICList();
        if (nicList != null && !nicList.isEmpty()) {
            for (String ip : nicList) {
                if (ip.startsWith(PropertyManager.getProperty("stbUrlPrefix"))) {
                    return ip;
                }
            }
            return getIp4IP();
        }
        return localAddress;
    }
    /**
     * 获取当前机器端口号
     */
    public static String getLocalPort() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objectNames = mBeanServer.queryNames(new ObjectName("*:type=Connector,*"), null);
        if (objectNames == null || objectNames.size() <= 0) {
            throw new IllegalStateException("Cannot get the names of MBeans controlled by the MBean server.");
        }

        for (ObjectName objectName : objectNames) {
            String protocol = String.valueOf(mBeanServer.getAttribute(objectName, "protocol"));
            String port = String.valueOf(mBeanServer.getAttribute(objectName, "port"));
            // windows下属性名称为HTTP/1.1, linux下为org.apache.coyote.http11.Http11NioProtocol
            if (protocol.equals("HTTP/1.1") || protocol.equals("org.apache.coyote.http11.Http11NioProtocol")) {
                return port;
            }
        }

        throw new IllegalStateException("Failed to get the HTTP port of the current server");
    }

    /**
     * 获取当前机器的IP
     */
    public static String getIp4IP() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && ip.getHostAddress().indexOf(":") == -1) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取ip异常,返回127.0.0.1",e);
            return localAddress;
        }
        return localAddress;
    }

    /**
     * 获取当前机器的IP
     */
    public static List<String> getNICList() {
        List<String> ipList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && !ip.getHostAddress().contains(":")) {
                        ipList.add(ip.getHostAddress());
                    }
                }
            }

        } catch (Exception e) {
            logger.error("获取ip异常,返回127.0.0.1",e);
            ipList.clear();
            ipList.add(localAddress);
            return ipList;
        }
        return ipList;
    }

    //获取本地ip
    public static String getLocalIp() {
        String defaultIp = "127.0.0.1";
        try {
            Enumeration<NetworkInterface> eis = NetworkInterface.getNetworkInterfaces();//定义网络接口枚举类，获得网络接口
            while (eis.hasMoreElements()) {//遍历所有的网络接口
                NetworkInterface ei = eis.nextElement();
                Enumeration<InetAddress> ias = ei.getInetAddresses();//同样再定义网络地址枚举类
                while (ias.hasMoreElements()) {
                    InetAddress ia = ias.nextElement();//声明一个InetAddress类型ip地址
                    if (ia != null && ia instanceof Inet4Address) {//InetAddress类包括Inet4Address和Inet6Address
                        if (!defaultIp.equals(ia.getHostAddress())) {
                            return ia.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取本地ip错误： " + e);
        }

        return defaultIp;
    }

    /**
     * 获取当前机器的IP
     */
    public static String getHostIP() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        byte[] ipAddr = addr.getAddress();
        String ipAddrStr = "";
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i] & 0xFF;
        }

        return ipAddrStr;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getHostIP());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}