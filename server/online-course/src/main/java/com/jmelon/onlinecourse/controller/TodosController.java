package com.jmelon.onlinecourse.controller;


import com.jmelon.onlinecourse.model.Todo;
import com.jmelon.onlinecourse.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/todos")
public class TodosController {

    @Autowired
    TodoService todoService;

    @GetMapping()
    public Collection<Todo> getAll() {
        return todoService.getAll();
    }

    @PostMapping()
    public void create(@RequestBody Todo model) {
        todoService.create(model);
    }
}
