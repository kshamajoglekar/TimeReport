package com.wave.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wave.entity.PayrollReport;
import com.wave.exception.ApplicationException;
import com.wave.service.PayrollReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/payload-report")
public class PayrollReportController {

    private ObjectMapper mapper=new ObjectMapper();

    @Autowired
    PayrollReportService payrollReportService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPayloadReport() throws ApplicationException {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        PayrollReport payrollReport=payrollReportService.getPayrollReport();
        String payrollReportJson ="{}";
        try {
            payrollReportJson = mapper.writeValueAsString(payrollReport);
        } catch (JsonProcessingException e) {
            throw new ApplicationException("Error while converting payrollreport to json"+e);
        }

        System.out.println(payrollReport);
        return payrollReportJson;
    }

}
