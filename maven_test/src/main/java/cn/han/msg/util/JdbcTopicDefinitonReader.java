package cn.han.msg.util;

import cn.han.msg.core.Topic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

public class JdbcTopicDefinitonReader {

	private JdbcTemplate jdbcTemplate;

	public JdbcTopicDefinitonReader(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Topic> loadTopicDefinitions(String flag) {
		if (StringUtils.isBlank(flag))
			return Collections.emptyList();
			
		String sql = "SELECT TopicName,`Type`,HandlerClass FROM t_topic WHERE `Type`=5";
		//String sql = "SELECT TopicName,`Type`,HandlerClass FROM t_topic WHERE `Type`=3 OR `Type`=4 OR `Type`=5";

		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			Topic topic = new Topic();
			topic.setName(rs.getString(1));
			topic.setType(rs.getString(2));
			topic.setHandlerClass(rs.getString(3));
			return topic;
		});
	}

}
