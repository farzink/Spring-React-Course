package com.jmelon.onlinecourse.controller;

import com.jmelon.onlinecourse.aspect.Authorize;
import com.jmelon.onlinecourse.model.JsonContainerModel;
import com.jmelon.onlinecourse.service.CourseService;
import com.jmelon.onlinecourse.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/courses")
public class CoursesController extends BaseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private PersonService personService;


    @Authorize
    @GetMapping()
    public ResponseEntity<JsonContainerModel> getAll() {
        return reply(courseService.getAll());
    }


    @Authorize
    @GetMapping("/enrolled")
    public ResponseEntity<JsonContainerModel> getAllEnrolledCourses() {
        return reply(courseService.getAllEnrolledCoursesByPersonId(user.getId()));
    }

    @Authorize
    @GetMapping("{id}/enroll")
    public ResponseEntity<JsonContainerModel> enroll(@PathVariable Long id) {
        return reply(courseService.enrollByCourseAndPersonId(user.getId(), id));
    }


    @Authorize
    @GetMapping("enrolled/{id}/progress")
    public ResponseEntity<JsonContainerModel> makeProgress(@PathVariable Long id) {
        return reply(courseService.makeRandomProgress(user.getId(), id));
    }

    @Authorize
    @GetMapping("enrolled/{id}/leave")
    public ResponseEntity<JsonContainerModel> leave(@PathVariable Long id) {
        return reply(courseService.leaveByCourseAndPersonId(user.getId(), id));
    }


    @Authorize
    @GetMapping("enrolled/{id}")
    public ResponseEntity<JsonContainerModel> getEnrolledCourseInfo(@PathVariable Long id) {
        return reply(courseService.getEnrolledCourseByPersonId(user.getId(), id));
    }


    @Authorize
    @GetMapping("{id}/visit")
    public ResponseEntity<JsonContainerModel> visit(@PathVariable Long id) {
        return reply(personService.addToVisitedCourses(user.getId(), id));
    }




}





