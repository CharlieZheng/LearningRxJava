package com.cdc.rxjavalearning.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Charlie on 2016/7/28.
 */
public class CommitParam {

    private String short_name;
    private String author_email;
    private String author_name;
    private String thread_id;
    private String author_url;
    private String message;

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getAuthor_url() {
        return author_url;
    }

    public void setAuthor_url(String author_url) {
        this.author_url = author_url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> createCommitParams() {
        Map<String, String> params = new HashMap<>();
        params.put("short_name", short_name);
        params.put("author_email", author_email);
        params.put("author_name", author_name);
        params.put("thread_id", thread_id);
        params.put("author_url", author_url);
        params.put("message", message);
        return params;
    }
}
