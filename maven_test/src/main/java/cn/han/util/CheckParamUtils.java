package cn.han.util;

import cn.han.model.CommonAssetFile;
import cn.han.model.IgnoreParam;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CheckParamUtils {
	public static String checkParam(Object obj) {
		List<String> arrayList = new ArrayList();
		StringBuffer stringBuffer = new StringBuffer();
		try {
			for (Field f : obj.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				if (f.getAnnotation(IgnoreParam.class) != null) {
					continue;
				}
				Object o = f.get(obj);
				if (f.get(obj) == null) {
					arrayList.add(f.getName());
				} else {
					// 如果需要判断空字符串
					if (StringUtils.isBlank(String.valueOf(o))) {
						arrayList.add(f.getName());
					}
				}
			}
			// 拼接返回信息
			for (String param : arrayList) {
				stringBuffer.append("参数" + param + "缺失;");
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
	public static void main(String[] args) {
		CommonAssetFile file = new CommonAssetFile();
		file.setAssetId("");
		String checkParam = CheckParamUtils.checkParam(file);
		System.out.println(checkParam);
	}
}
