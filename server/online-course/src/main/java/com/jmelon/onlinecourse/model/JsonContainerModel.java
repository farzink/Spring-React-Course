package com.jmelon.onlinecourse.model;

public class JsonContainerModel<T> {
    public T data;
    public int status;

    public JsonContainerModel() {
    }

    public JsonContainerModel(T data, int status) {
        this.data = data;
        this.status = status;
    }
}
