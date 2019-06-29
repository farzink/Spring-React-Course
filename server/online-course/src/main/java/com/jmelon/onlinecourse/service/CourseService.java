package com.jmelon.onlinecourse.service;


import ch.qos.logback.core.encoder.EchoEncoder;
import com.jmelon.onlinecourse.model.Course;
import com.jmelon.onlinecourse.model.EnrolledCourseInfo;
import com.jmelon.onlinecourse.model.Enrolment;
import com.jmelon.onlinecourse.model.ServiceResultModel;
import com.jmelon.onlinecourse.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

@Service
public class CourseService extends BaseService {

    @Autowired
    CourseRepository courseRepository;

    public ServiceResultModel<Collection<Course>> getAll() {
        var result = this.createResultModel();
        try {
            result.setData(courseRepository.getAll());
            return result;
        } catch(Exception ex) {
            //todo: log the exception
            result.failWithMessage("something has gone wrong with request");
            return result;
        }
    }

    public void create(Course course) {
        courseRepository.save(course);
    }

    public ServiceResultModel<Collection<EnrolledCourseInfo>> getAllEnrolledCoursesByPersonId(Long id) {
        var result = this.createResultModel();
        try {
            result.setData(courseRepository.getCoursesByPersonId(id));
            return result;
        } catch(Exception ex) {
            //todo: log the exception
            result.failWithMessage("something has gone wrong with request");
            return result;
        }
    }

    public ServiceResultModel<Enrolment> enrollByCourseAndPersonId(Long personId, Long courseId) {
        var result = this.createResultModel();
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy"); // HH:mm:ss");
            Date date = new Date();
            var enrolmentDate = dateFormat.format(date);
            result.setData(courseRepository.enrollByCourseAndPersonId(personId, courseId, enrolmentDate));
            return result;
        } catch(Exception ex) {
            //todo: log the exception
            result.failWithMessage("something has gone wrong with request");
            return result;
        }
    }

    public ServiceResultModel<Boolean> leaveByCourseAndPersonId(Long personId, Long courseId) {
        var result = this.createResultModel();
        try {
            courseRepository.leaveByCourseAndPersonId(personId, courseId);
            result.setData(true);
            return result;
        } catch(Exception ex) {
            //todo: log the exception
            result.failWithDataAndMessage(false, "something has gone wrong with request");
            return result;
        }
    }

    public ServiceResultModel<EnrolledCourseInfo> getEnrolledCourseByPersonId(Long personId, Long courseId) {
        var result = this.createResultModel();
        try {
            result.setData(courseRepository.getEnrolledCourseInfoByPersonId(personId, courseId));
            return result;
        } catch(Exception ex) {
            //todo: log the exception
            result.failWithDataAndMessage(null,"something has gone wrong with request");
            return result;
        }
    }

    public ServiceResultModel<Enrolment> makeRandomProgress(Long personId, Long courseId) {
        var result = this.createResultModel();
        try {
            EnrolledCourseInfo enrolledCourse = courseRepository.getEnrolledCourseInfoByPersonId(personId, courseId);
            if(enrolledCourse == null) {
                throw new Exception();
            }
            int currentProgress = enrolledCourse.getProgress();
            if(currentProgress >= 100) {
                throw new Exception();
            }
            int randomProgress = new Random().nextInt(24);
            if(randomProgress + currentProgress > 100) {
                randomProgress = 100;
            } else {
                randomProgress +=currentProgress;
            }
            result.setData(courseRepository.makeProgress(personId, courseId, randomProgress));
            return result;
        } catch(Exception ex) {
            //todo: log the exception
            result.failWithDataAndMessage(null,"something has gone wrong with request");
            return result;
        }
    }


    public ServiceResultModel<Enrolment> resetCourseProgress(Long personId, Long courseId) {
        var result = this.createResultModel();
        try {
            EnrolledCourseInfo enrolledCourse = courseRepository.getEnrolledCourseInfoByPersonId(personId, courseId);
            if(enrolledCourse == null) {
                throw new Exception();
            }
            result.setData(courseRepository.makeProgress(personId, courseId, 0));
            return result;
        } catch(Exception ex) {
            //todo: log the exception
            result.failWithDataAndMessage(null,"something has gone wrong with request");
            return result;
        }
    }
}








