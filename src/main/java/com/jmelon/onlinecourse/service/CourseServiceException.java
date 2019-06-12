package com.jmelon.onlinecourse.service;

import com.jmelon.onlinecourse.model.Course;

public class CourseServiceException extends Exception {

    CourseServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
