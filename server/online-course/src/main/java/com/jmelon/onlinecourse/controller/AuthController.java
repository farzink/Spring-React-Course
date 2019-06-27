package com.jmelon.onlinecourse.controller;





import com.jmelon.onlinecourse.aspect.Authorize;
import com.jmelon.onlinecourse.model.JsonContainerModel;
import com.jmelon.onlinecourse.model.Todo;
import com.jmelon.onlinecourse.model.viewmodels.AuthUserViewModel;
import com.jmelon.onlinecourse.model.viewmodels.TokenViewModel;
import com.jmelon.onlinecourse.service.AuthUserService;
import com.jmelon.onlinecourse.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @Autowired
    private AuthUserService authUserService;


    @PostMapping("/register")
    public ResponseEntity<JsonContainerModel> register(@RequestBody AuthUserViewModel model) {
        if(authUserService.register(model)) {
            return ok(null);
        } else {
            return bad(null);
        }
    }

    @PostMapping("/token")
    public ResponseEntity<JsonContainerModel> token(@RequestBody AuthUserViewModel model) {
        var token = authUserService.token(model);
        if(token != null) {
            return ok(token);
        } else {
            return bad(null);
        }
    }
}
