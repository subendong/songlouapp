package com.songlou.common;

public class ResultHelper {
	private int code;//״̬��
	private boolean success;//�Ƿ�ɹ�
	private String message;//����
	private Object data;//���������κ����͵�����
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
