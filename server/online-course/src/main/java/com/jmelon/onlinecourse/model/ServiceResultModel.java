package com.jmelon.onlinecourse.model;

public class ServiceResultModel<T> {
    public T data;
    public boolean hasSucceeded;
    public String message;

    public ServiceResultModel(T data, boolean hasSucceeded, String message) {
        this.data = data;
        this.hasSucceeded = hasSucceeded;
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setHasSucceeded(boolean hasSucceeded) {
        this.hasSucceeded = hasSucceeded;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void failWithMessage(String message) {
        hasSucceeded = false;
        this.message = message;
    }

    public void failWithDataAndMessage(T data, String message) {
        hasSucceeded = false;
        this.data = data;
        this.message = message;
    }
}

