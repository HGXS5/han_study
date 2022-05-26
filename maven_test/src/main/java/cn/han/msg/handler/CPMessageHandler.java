package cn.han.msg.handler;

import cn.han.msg.core.CPMessage;

@FunctionalInterface
public interface CPMessageHandler {

	public void handle(CPMessage msg);

}
