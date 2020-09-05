package com.spring.kafka.models;

import com.spring.kafka.utils.JsonUtil;
import java.io.Serializable;


public class Message implements Serializable {
    private Header header;
    private String data;

    public Message() {}

    public Message(Object object) {
        this.data = JsonUtil.toJson(object);
    }

    public Message(Object object, String producer) {
        this.data = JsonUtil.toJson(object);
        this.header = new Header(producer);
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
