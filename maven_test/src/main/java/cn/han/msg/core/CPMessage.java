package cn.han.msg.core;

import java.io.Serializable;

public class CPMessage implements Serializable {

	private static final long serialVersionUID = -3363704373883888788L;

	private String topic;

	private String vodCode;

	private String batchNo;

	private Object content;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getVodCode() {
		return vodCode;
	}

	public void setVodCode(String vodCode) {
		this.vodCode = vodCode;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
