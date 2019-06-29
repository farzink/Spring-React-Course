package com.jmelon.onlinecourse.service;

import com.jmelon.onlinecourse.model.ServiceResultModel;

public abstract class BaseService {
    protected <T> ServiceResultModel createResultModel(T data, boolean hasSucceeded, String message) {
        return new ServiceResultModel(data, hasSucceeded, message);
    }
    protected <T> ServiceResultModel createResultModel(T data, boolean hasSucceeded) {
        return new ServiceResultModel(data, hasSucceeded, "");
    }
    protected <T> ServiceResultModel createResultModel(T data) {
        return new ServiceResultModel(data, true, "");
    }
    protected <T> ServiceResultModel createResultModel() {
        return new ServiceResultModel(null, true, "");
    }
}
