package com.wave.controller;

import com.wave.exception.ApplicationException;
import com.wave.exception.ValidationException;
import com.wave.service.TimeReportUploadService;
import com.wave.validator.CSVValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RequestMapping("/time-report")
@RestController
public class TimeReportUploadController {


    @Autowired
    public TimeReportUploadService timeReportUploadService;

    @Autowired
    public CSVValidator validator;
    /**
     * API: This APi loads the time report in to database. performs validation before doing so
     */
    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadCsvFile(@RequestParam("file") MultipartFile file) throws ApplicationException, ValidationException {

       validator.validate(file);
        timeReportUploadService.uploadTimeReport(file);

        return "File uploaded";
    }

}
