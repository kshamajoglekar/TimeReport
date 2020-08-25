package com.wave.entity;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is not persisted.
 * It's only a container that holds payroll data i.e. build from persisted time-report data
 */

public class PayrollReport {

    private Set<EmployeeReport> employeeReports;

    private PayrollReport() {}

    private static PayrollReport payrollReport;
    static {
        payrollReport =new PayrollReport();
    }

    public static PayrollReport get(List<TimeReport> timeReports) {
        payrollReport.employeeReports = generateReport(timeReports);
        return payrollReport;
    }

    private static Set<EmployeeReport> generateReport(List<TimeReport> timeReports) {

        EmployeeReportHolder employeeReportHolder=EmployeeReportHolder.get();

        timeReports.stream().forEach(timeReport ->
        {
             employeeReportHolder.update(timeReport);
        });

        return employeeReportHolder.getEmployeeReports().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));

    }

    @Override
    public String toString() {
        return "PayrollReport{" +
                "employeeReports=" + employeeReports +
                '}';
    }
}