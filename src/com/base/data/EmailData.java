package com.base.data;

public class EmailData {

    private String receiver;
    private String subject;
    private Object[] body;

    public EmailData(String receiver, String subject, Object ... bodyItems) {
        this.receiver = receiver;
        this.subject = subject;
        this.body = bodyItems;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public Object[] getBody() {
        return body;
    }
}
