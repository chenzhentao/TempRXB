package com.czt.temprxb.entity;

/**
 *  Created by {冯中萌} on 2017/4/19
 */

public class BaseSort {
    private String key;
    private String content;

    public BaseSort() {
    }

    public BaseSort(String key, String content) {
        this.key = key;
        this.content = content;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BaseSort{" +
                "key='" + key + '\'' +
                ", content=" + content +
                '}';
    }
}
