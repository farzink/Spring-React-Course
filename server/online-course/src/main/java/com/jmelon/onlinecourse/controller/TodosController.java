package com.jmelon.onlinecourse.controller;


import com.jmelon.onlinecourse.aspect.Authorize;
import com.jmelon.onlinecourse.model.JsonContainerModel;
import com.jmelon.onlinecourse.model.Todo;
import com.jmelon.onlinecourse.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/todos")
public class TodosController extends BaseController {


    @Autowired
    TodoService todoService;

    @Authorize
    @GetMapping()
    public ResponseEntity<Collection<Todo>> getAll() {
        return null;
    }

    @Authorize
    @GetMapping("/1")
    public ResponseEntity<JsonContainerModel> get() {
        return ok(user.getId());
    }

    @PostMapping()
    public void create(@RequestBody Todo model) {
        todoService.create(model);
    }
}
