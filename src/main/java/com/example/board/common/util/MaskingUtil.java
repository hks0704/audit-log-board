package com.example.board.common.util;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public final class MaskingUtil {
	private MaskingUtil() {}

	public static String maskIp(String ip) {
		if (ip == null || ip.isBlank()) return ip;

		InetAddress address;
		try {
			address = InetAddress.getByName(ip);

			if (address instanceof Inet4Address) {
				return maskIpv4(ip);
			}

			if (address instanceof Inet6Address) {
				String normalizedIp = address.getHostAddress();
				return maskIpv6(normalizedIp);
			}

			return ip;
		} catch (UnknownHostException e) {
			return ip;
		}
	}

	private static String maskIpv4(String ipv4) {
		String[] tokens = ipv4.split("\\.");
		if (tokens.length != 4) return ipv4;
		return String.format("%s.%s.***.***", tokens[0], tokens[1]);
	}

	private static String maskIpv6(String ipv6) {
		String[] tokens = ipv6.split(":");
		return String.format("%s:%s:%s:%s:****:****:****:****", tokens[0], tokens[1], tokens[2], tokens[3]);
	}

}
