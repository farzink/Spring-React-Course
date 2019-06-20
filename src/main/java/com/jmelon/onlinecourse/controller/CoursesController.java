package com.jmelon.onlinecourse.controller;


import com.jmelon.onlinecourse.model.Course;
import com.jmelon.onlinecourse.service.CourseService;
import com.jmelon.onlinecourse.service.CourseServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    private static final Logger LOG = LogManager.getLogger(CoursesController.class);

    private final CourseService courseService ;

    @Autowired
    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public Collection<Course> getAllCourses(){
        return courseService.findAllCourses();
    }

    /*
    @GetMapping()
    public Collection<Course> getAll() {
        return courseService.getAll();
    }*/

    @GetMapping("/{id}")
    ResponseEntity<Course> getCourse(@PathVariable Long id){
        try {
            return StreamSupport.stream(this.courseService.findAllById(List.of(id)).spliterator(), false)
                    .findAny()
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (CourseServiceException e) {
            LOG.error("exception while getting a course", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "exception while getting a course with id " + id, e);
        }

    }

    @PostMapping()
    public void create(@RequestBody Course model) {
        courseService.create(model);
    }

    @PostMapping(path = "/{id}",consumes = "application/json", produces = "application/json")
    public void addCourseRegistration(@PathVariable Long id) {
        this.courseService.create();
    }
}
