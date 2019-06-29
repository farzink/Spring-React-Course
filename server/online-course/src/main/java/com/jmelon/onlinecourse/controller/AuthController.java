package com.jmelon.onlinecourse.controller;


import com.jmelon.onlinecourse.aspect.Authorize;
import com.jmelon.onlinecourse.model.JsonContainerModel;
import com.jmelon.onlinecourse.model.viewmodels.AuthUserViewModel;
import com.jmelon.onlinecourse.service.AuthUserService;
import com.jmelon.onlinecourse.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private PersonService personService;


    @PostMapping("/register")
    public ResponseEntity<JsonContainerModel> register(@RequestBody AuthUserViewModel model) {
        if (authUserService.register(model)) {
            return ok(null);
        } else {
            return bad(null);
        }
    }

    @PostMapping("/token")
    public ResponseEntity<JsonContainerModel> token(@RequestBody AuthUserViewModel model) {
        var token = authUserService.token(model);
        if (token != null) {
            return ok(token);
        } else {
            return bad(null);
        }
    }

    @Authorize
    @GetMapping("/visibility/{status}")
    public ResponseEntity<JsonContainerModel> changeVisibilityStatus(@PathVariable boolean status) {
        return ok(personService.updatePersonVisibilityStatus(user.getId(), !status));
    }

    @Authorize
    @GetMapping("/me")
    public ResponseEntity<JsonContainerModel> getProfileById() {
        return ok(personService.getProfileById(user.getId()));
    }
}
