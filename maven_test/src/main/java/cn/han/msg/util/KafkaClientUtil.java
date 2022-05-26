package cn.han.msg.util;

import cn.han.util.IPUtil;
import org.bson.types.ObjectId;

public class KafkaClientUtil {

	public static String getMachineId() {
		return String.valueOf(ObjectId.getGeneratedMachineIdentifier());
	}

	public static String getProcessId() {
		return String.valueOf(ObjectId.getGeneratedProcessIdentifier());
	}

	public static String generateIdSuffix() {
		return String.format("[%s#%s]", IPUtil.getAnyLocalIp().orElse(getMachineId()), getProcessId());
	}

}