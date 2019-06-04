package com.jmelon.onlinecourse.service;

import com.jmelon.onlinecourse.model.Todo;
import com.jmelon.onlinecourse.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public Collection<Todo> getAll() {
        return todoRepository.getAll();
    }

    public void create(Todo todo) {
        todoRepository.save(todo);
    }
}
