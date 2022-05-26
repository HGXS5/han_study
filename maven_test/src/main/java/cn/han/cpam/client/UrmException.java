package cn.han.cpam.client;

public class UrmException extends Exception{
	private static final long serialVersionUID = -5110502289963194526L;
	
	private String error;
	
	public UrmException(String error, String message) {
		super(message);
		this.error = error;
	}
	
	public String getError() {
		return error;
	}
}