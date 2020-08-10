package com.paypal.bfs.test.employee.exception;

import java.util.Date;

public class ErrorInfo {
	private Date timestamp;
	private String msg;
	private String info;

	public ErrorInfo(Date timestamp, String msg, String info) {
		super();
		this.timestamp = timestamp;
		this.msg = msg;
		this.info = info;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
