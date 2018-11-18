package com.infoservice.init.infothreads.models;

public class PushKeys {
    public String p256dh;
    public String auth;

    public PushKeys(String p256dh, String auth) {
        this.p256dh = p256dh;
        this.auth = auth;
    }

    public PushKeys() {
    }
}
