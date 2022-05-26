package cn.han.config;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.internal.util.StringHelper;

public class CustomizedNamingStrategy extends ImprovedNamingStrategy {

	private static final long serialVersionUID = 5277175487036976501L;

	@Override
	public String classToTableName(String className) {
		return "t_" + addUnderscores(StringHelper.unqualify(className));
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		return StringUtils.capitalize(propertyName);
	}

}
