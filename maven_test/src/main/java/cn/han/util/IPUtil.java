package cn.han.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

public class IPUtil {

	public static List<String> getLocalIp() {
		List<String> ipList = new ArrayList<>();
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface netIf = (NetworkInterface) interfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = netIf.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
					// @formatter:off
					if (!inetAddress.getHostAddress().contains(":") &&
						!inetAddress.isLoopbackAddress() &&
						!inetAddress.isMulticastAddress() &&
						!inetAddress.isAnyLocalAddress() &&
						!inetAddress.isLinkLocalAddress() &&
						 inetAddress.isReachable(1000)) {
					// @formatter:on
						ipList.add(inetAddress.getHostAddress());
					}
				}
			}
		} catch (Exception e) {
		}

		return ipList;
	}

	public static Optional<String> getAnyLocalIp() {
		return getLocalIp().stream().findAny();
	}

}
