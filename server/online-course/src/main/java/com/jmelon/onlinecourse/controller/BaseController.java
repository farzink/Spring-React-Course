package com.jmelon.onlinecourse.controller;

import com.jmelon.onlinecourse.model.AuthUser;
import com.jmelon.onlinecourse.model.JsonContainerModel;
import com.jmelon.onlinecourse.model.ServiceResultModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


public class BaseController {
    protected AuthUser user = null;

    BaseController() {
    }

    public void setUser(AuthUser model) {
        user = model;
    }


    protected <T> ResponseEntity<JsonContainerModel> ok(T data) {
        var container = new JsonContainerModel<T>();
        container.data = data;
        container.status = 200;
        return new ResponseEntity<JsonContainerModel>(container, HttpStatus.OK);
    }

    protected <T> ResponseEntity<JsonContainerModel> created(T data) {
        var container = new JsonContainerModel<T>();
        container.data = data;
        container.status = 201;
        return new ResponseEntity<JsonContainerModel>(container, HttpStatus.CREATED);
    }

    protected <T> ResponseEntity<JsonContainerModel> bad(T data) {
        var container = new JsonContainerModel<T>();
        container.data = data;
        container.status = 400;
        return new ResponseEntity<JsonContainerModel>(container, HttpStatus.BAD_REQUEST);
    }

    protected <T> ResponseEntity<JsonContainerModel> reply(ServiceResultModel model) {
        return model.hasSucceeded ?
                ok(model.data) :
                bad(model.data);
    }

    protected <T> ResponseEntity<JsonContainerModel> replyAsCSV(ServiceResultModel model) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return model.hasSucceeded ?
                new ResponseEntity<JsonContainerModel>(new JsonContainerModel(model.data, 200), headers, HttpStatus.OK):
                bad(model.data);
    }

}
