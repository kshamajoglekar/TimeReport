package com.wave.service;

import com.google.common.collect.Lists;
import com.wave.entity.PayrollReport;
import com.wave.entity.TimeReport;
import com.wave.repository.TimeReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollReportService {

    @Autowired
    private TimeReportRepository timeReportRepository;

    public PayrollReport getPayrollReport() {
        List<TimeReport> timeReports = Lists.newArrayList(timeReportRepository.findAll());

        return PayrollReport.get(timeReports);
    }
}
