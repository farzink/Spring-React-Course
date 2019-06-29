package com.jmelon.onlinecourse.controller;


import com.jmelon.onlinecourse.aspect.Authorize;
import com.jmelon.onlinecourse.model.JsonContainerModel;
import com.jmelon.onlinecourse.model.Person;
import com.jmelon.onlinecourse.repository.CertificateRepository;
import com.jmelon.onlinecourse.service.CertificateService;
import com.jmelon.onlinecourse.service.CourseService;
import com.jmelon.onlinecourse.service.PersonService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/certificates")
public class CertificatesController extends BaseController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private PersonService personService;


    @GetMapping()
    public ResponseEntity<JsonContainerModel> getAll() {
        var result = certificateService.getAll();
        return ok(result);
    }

    @Authorize
    @GetMapping("exam/{id}")
    public ResponseEntity<JsonContainerModel> takeExam(@PathVariable Long id) {
        var result = certificateService.takeExam(user.getId(), id);
        return ok(result);
    }


    @Authorize
    @GetMapping("/achieved")
    public ResponseEntity<JsonContainerModel> achieved() {
        var result = certificateService.getAchievedCertificatesByPersonId(user.getId());
        return ok(result);
    }

    @GetMapping("/candidates/{id}")
    public ResponseEntity<JsonContainerModel> getCandidatesForCetificateById(@PathVariable Long id){
        return reply(personService.findPeopleWithCertainCertificate(id));
    }

    @GetMapping(value = "/candidates/{id}", produces = "text/csv")
    public void getCandidatesForCetificateByIdAsCSV(@PathVariable Long id, HttpServletResponse response) {
        try {
            /*String filename = "users.csv";
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + filename + "\"");*/
            StatefulBeanToCsv<Person> writer = new StatefulBeanToCsvBuilder<Person>(response.getWriter())
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withOrderedResults(false)
                    .build();
            writer.write((List<Person>)personService.findPeopleWithCertainCertificate(id).data);
        } catch (Exception ex) {

            //return replyAsCSV(personService.findPeopleWithCertainCertificate(id));
        }
    }
}
