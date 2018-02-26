package com.songlou.common;

public class ResultHelper {
	private int code;//状态码
	private boolean success;//是否成功
	private String message;//描述
	private Object data;//保存其它任何类型的数据
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ResultHelper(int code, boolean success, String message, Object data) {
		this.code = code;
		this.success = success;
		this.message = message;
		this.data = data;
	}
}
