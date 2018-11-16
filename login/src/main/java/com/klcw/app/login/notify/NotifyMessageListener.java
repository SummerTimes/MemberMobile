package com.klcw.app.login.notify;


import com.klcw.app.login.bean.NotifyMessage;

public interface NotifyMessageListener {
    void onNotifyMessage(NotifyMessage msg);
}
