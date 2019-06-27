package com.jmelon.onlinecourse.service;


import com.jmelon.onlinecourse.model.Course;
import com.jmelon.onlinecourse.model.EnrolledCourseInfo;
import com.jmelon.onlinecourse.model.Enrolment;
import com.jmelon.onlinecourse.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public Collection<Course> getAll() {
        return courseRepository.getAll();
    }

    public void create(Course course) {
        courseRepository.save(course);
    }

    public Collection<EnrolledCourseInfo> getAllEnrolledCoursesByPersonId(Long id) {
        try {
            return courseRepository.getCoursesByPersonId(id);
        } catch(Exception ex) {
            return null;
        }
    }

    public Enrolment enrollByCourseAndPersonId(Long personId, Long courseId) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy"); // HH:mm:ss");
            Date date = new Date();
            var enrolmentDate = dateFormat.format(date);
            var result = courseRepository.enrollByCourseAndPersonId(personId, courseId, enrolmentDate);
            return result;
        } catch(Exception ex) {
            return null;
        }
    }

    public boolean leaveByCourseAndPersonId(Long personId, Long courseId) {
        try {
            courseRepository.leaveByCourseAndPersonId(personId, courseId);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public EnrolledCourseInfo getAllEnrolledCoursesByPersonId(Long personId, Long courseId) {
        try {
            return courseRepository.getEnrolledCourseInfoByPersonId(personId, courseId);
        } catch(Exception ex) {
            return null;
        }
    }
}








