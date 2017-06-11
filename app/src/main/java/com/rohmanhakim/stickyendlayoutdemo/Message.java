package com.rohmanhakim.stickyendlayoutdemo;

class Message {
    private String message;
    private String sender;
    private String date;

    Message(String sender, String message, String date){
        this.sender = sender;
        this.message = message;
        this.date = date;
    }

    String getMessage() {
        return message;
    }

    String getSender() {
        return sender;
    }

    String getDate() {
        return date;
    }
}
