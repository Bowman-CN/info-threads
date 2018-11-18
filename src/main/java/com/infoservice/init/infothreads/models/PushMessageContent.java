package com.infoservice.init.infothreads.models;

public class PushMessageContent {
    public String title;
    public  String body;
    public  String icon ;
    public  int[] vibrate;

    public PushMessageContent(String title, String body, String icon, int[] vibrate) {
        this.title = title;
        this.body = body;
        this.icon = icon;
        this.vibrate = vibrate;
    }

    public PushMessageContent() {
    }
}
