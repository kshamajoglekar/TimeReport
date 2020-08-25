package com.wave.entity;

import com.google.common.collect.ImmutableSet;
import lombok.Getter;

import java.util.Optional;
import java.util.Set;

public final class EmployeeReportHolder{

    /*Make it singleton*/
    private static EmployeeReportHolder employeeReportHolder;

    static {
        employeeReportHolder = new EmployeeReportHolder();
    }

    public static EmployeeReportHolder get(){
        return employeeReportHolder;
    }

    private EmployeeReportHolder() { }

    /*--*/

    @Getter
    private Set<EmployeeReport> employeeReports = Set.of();

    public void update(final TimeReport timeReport) {


        /*
        * payroll report -> to provide llist of employees
        * employeeReport -> to provide list of pay periods.
        * */


        final PayPeriod payPeriod = PayPeriodHolder.get().getPayPeriod(timeReport.getDate());

        Optional<EmployeeReport> optionalEmployeeReport = employeeReportHolder.employeeReports.stream()
                .filter(employeeReport -> (employeeReport.getEmployeeId().equalsIgnoreCase(timeReport.getEmployeeId()))
                        && (employeeReport.getPayPeriod().equals(payPeriod)))
                .findFirst();


        EmployeeReport employeeReport = optionalEmployeeReport
                .orElse(EmployeeReport.create(timeReport, payPeriod));

        employeeReport.updateAmount(timeReport.getHoursWorked(),JobGroup.valueOf(timeReport.getJobGroup()));

        employeeReportHolder.employeeReports = new ImmutableSet.Builder<EmployeeReport>()
                .addAll(employeeReportHolder.employeeReports)
                .add(employeeReport)
                .build();


    }

}


