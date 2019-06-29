package com.jmelon.onlinecourse.service;

import com.jmelon.onlinecourse.model.*;
import com.jmelon.onlinecourse.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@Service
public class CertificateService {

    @Autowired
    CertificateRepository certificateRepository;

    @Autowired
    CourseService courseService;

    @Autowired
    PersonService personService;

    public Collection<Certificate> getAll() {
        var result = certificateRepository.getAll();
        return result;
    }

    public Person takeExam(Long personId, Long courseId) {
        try {
            EnrolledCourseInfo courseInfo = courseService.getEnrolledCourseByPersonId(personId, courseId).data;
            if(courseInfo == null) {
                throw new Exception();
            }
            if(courseInfo.getCourse().getCertificate() == null) {
                //this course has no certif
                throw new Exception();
            }
            if(courseInfo.getProgress() == 100) {
                var certificateId = courseInfo.getCourse().getCertificate();
                if(certificateId == null) {
                    throw new Exception();
                }
                return personService.addCetificate(personId, certificateId);

            }
            courseService.resetCourseProgress(personId, courseId);
            return null;

        } catch(Exception ex) {
            return null;
        }
    }

    public Collection<Certificate> getAchievedCertificatesByPersonId(Long personId) {
        try {
            var person = personService.getProfileById(personId);
            if(person ==  null){
                throw new Exception();
            }
            var currentCertificates = person.getAchievedCertificates().stream().collect(toList());
            return certificateRepository.getCertificatesByRange(currentCertificates);

        } catch(Exception ex ){
            return null;
        }
    }


}








