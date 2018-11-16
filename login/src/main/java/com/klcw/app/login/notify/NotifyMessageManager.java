package com.klcw.app.login.notify;


import com.klcw.app.login.bean.NotifyMessage;

public class NotifyMessageManager {
	private NotifyMessageListener listener;

	private NotifyMessageManager() {

	}

	private static final NotifyMessageManager MANAGER = new NotifyMessageManager();

	public static NotifyMessageManager getInstance() {
		return MANAGER;
	}

	public NotifyMessageListener getListener() {
		return listener;
	}

	public void setListener(NotifyMessageListener listener) {
		this.listener = listener;
	}

	public void sendNotifyMessage(NotifyMessage msg) {
		if (listener != null) {
			listener.onNotifyMessage(msg);
		}
	}
}
