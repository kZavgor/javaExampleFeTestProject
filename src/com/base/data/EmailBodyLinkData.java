package com.base.data;


public class EmailBodyLinkData {

    private String linkValue;
    private String linkText;

    public EmailBodyLinkData(String linkText, String linkValue) {
        this.linkText = linkText;
        this.linkValue = linkValue;
    }

    public String getLinkText() {
        return linkText;
    }

    public String getLinkValue() {
        return linkValue;
    }
}
