package com.wave.entity;

import java.util.List;
import java.util.Set;

/**
 * This class is not persisted.
 * It's only a container that holds payroll data i.e. build from persisted time-report data
 */

public class PayrollReport {

    private Set<EmployeeReport> employeeReports;

    private PayrollReport() {}

    public static PayrollReport get(List<TimeReport> timeReports) {
        PayrollReport payrollReport = new PayrollReport();
        payrollReport.employeeReports = generateReport(timeReports);
        return payrollReport;
    }

    private static Set<EmployeeReport> generateReport(List<TimeReport> timeReports) {

        EmployeeReport.initialize();  /* Workaround for the design issue. Static lists keep filling up. So creating a method to initialize them.
                                      * Solution: EmployeeReport and PayPeriod classes to own a job of clearing themselves before every run. Use builders in the owner classes.
                                      * */

        timeReports.stream().forEach(timeReport ->
        {
            EmployeeReport employeeReport = EmployeeReport.of(timeReport.getDate(), timeReport.getEmployeeId());
            employeeReport.updateAmount(timeReport.getHoursWorked(), JobGroup.valueOf(timeReport.getJobGroup()));

        });

        return EmployeeReport.getEmployeeReports();
    }

    @Override
    public String toString() {
        return "PayrollReport{" +
                "employeeReports=" + employeeReports +
                '}';
    }
}