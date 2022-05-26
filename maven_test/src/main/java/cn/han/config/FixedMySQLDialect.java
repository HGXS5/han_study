package cn.han.config;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.type.StandardBasicTypes;

import java.sql.Types;

public class FixedMySQLDialect extends MySQL5Dialect {

	public FixedMySQLDialect() {
		registerHibernateType(Types.BIGINT, StandardBasicTypes.LONG.getName());
		registerHibernateType(Types.NULL,"string");
	}

}
