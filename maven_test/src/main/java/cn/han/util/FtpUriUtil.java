package cn.han.util;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Objects;

public class FtpUriUtil {

	public static boolean isSeamFtp(String luri, String ruri) {
		return Objects.equals(getFtpUrl(luri), getFtpUrl(ruri));
	}

	public static Pair<String, Integer> getHostAndPort(String ftpUrl) {
		String[] ss = ftpUrl.split("/+");
		if (ss.length >= 2) {
			ss = ss[1].split("@");
			if (ss.length == 2) {
				ss = ss[1].split(":");
				if (ss.length == 2)
					return Pair.of(ss[0], Integer.parseInt(ss[1]));

				return Pair.of(ss[0], 21);
			}

			if (ss.length == 1) {
				ss = ss[0].split(":");
				if (ss.length == 2)
					return Pair.of(ss[0], Integer.parseInt(ss[1]));

				return Pair.of(ss[0], 21);
			}
		}

		return Pair.of("", 21);
	}

	public static Pair<String, String> getUsernameAndPassword(String ftpUrl) {
		String[] ss = ftpUrl.split("/+");
		if (ss.length >= 2) {
			ss = ss[1].split("@");
			if (ss.length == 2) {
				ss = ss[0].split(":");
				return Pair.of(ss[0], ss[1]);
			}
		}

		return Pair.of("", "");
	}

	public static String getFtpUrl(String uri) {
		String[] ss = uri.split("/+");
		if (ss.length < 2)
			return "";

		return ss[0].concat("//").concat(ss[1]);
	}

	public static String getFtpPath(String ftpUrl) {
		String[] ss = ftpUrl.split("/+");
		if (ss.length > 2) {
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i < ss.length; i++) {
				sb.append("/").append(ss[i]);
			}
			return sb.toString();
		}

		return "/";
	}

	public static String noEndingSlash(String ftpUrl) {
		if (Objects.isNull(ftpUrl) || !ftpUrl.endsWith("/"))
			return ftpUrl;

		return ftpUrl.substring(0, ftpUrl.length() - 1);
	}

}
