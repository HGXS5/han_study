package cn.han.cpam.client;


public class LoginResponse {
	private String ret;
	private User result;
	private String retInfo;
	
	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public User getResult() {
		return result;
	}

	public void setResult(User result) {
		this.result = result;
	}

	public String getRetInfo() {
		return retInfo;
	}

	public void setRetInfo(String retInfo) {
		this.retInfo = retInfo;
	}
}