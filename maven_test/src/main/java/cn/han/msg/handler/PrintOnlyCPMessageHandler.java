package cn.han.msg.handler;

import cn.han.msg.core.CPMessage;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintOnlyCPMessageHandler implements CPMessageHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void handle(CPMessage msg) {
		logger.info(ToStringBuilder.reflectionToString(msg, ToStringStyle.MULTI_LINE_STYLE));
	}

}
