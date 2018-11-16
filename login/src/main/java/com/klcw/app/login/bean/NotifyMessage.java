package com.klcw.app.login.bean;

import java.io.Serializable;

public class NotifyMessage implements Serializable {
	private String msgCode;
	private String rtnMsg;

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getRtnMsg() {
		return rtnMsg;
	}

	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}

}
