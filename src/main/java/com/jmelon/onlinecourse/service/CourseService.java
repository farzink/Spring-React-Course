package com.jmelon.onlinecourse.service;

import com.jmelon.onlinecourse.model.Course;
import com.jmelon.onlinecourse.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Service to access courses via the repository.
 *
 * @author Created by Dorian.
 */

@Service
public class CourseService {

    private static final Logger LOG = LoggerFactory.getLogger(CourseService.class);

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    /*public Collection<Course> getAll() {
        return courseRepository.getAll();
    }*/

    public Collection<Course> findAllCourses() {
        final Collection<Course> courses = new LinkedList<>();
        this.courseRepository.findAll().forEach(courses::add);
        return courses;
    }



    /*** Get all the courses that have certain IDs ***/
    public Iterable<Course> findAllById(Iterable<Long> ids) throws CourseServiceException {
        try {
            return courseRepository.findAllById(ids);
        } catch (IllegalArgumentException ex) {
            throw new CourseServiceException("finding Ids failed", ex);
        }
    }


    public void create(Course course) {
        courseRepository.save(course);
    }
}
