package com.wave.entity;

import com.google.common.collect.ImmutableSet;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

final class EmployeeReport implements Comparable<EmployeeReport> {

    @Getter
    private static Set<EmployeeReport> employeeReports = Set.of();
    @Getter
    private String employeeId;
    @Getter
    private PayPeriod payPeriod;
    @Getter
    private Double amountPaid;

    private EmployeeReport() {
        amountPaid = 0d;
    }

    public static void initialize() {
        employeeReports = Set.of();
        PayPeriod.initialize();
    }

    private static EmployeeReport create(String employeeId, PayPeriod payPeriod) {
        EmployeeReport employeeReport = new EmployeeReport();
        employeeReport.employeeId = employeeId;
        employeeReport.payPeriod = payPeriod;
        return employeeReport;
    }

    static EmployeeReport of(final LocalDate date, final String employeeId) {

        final PayPeriod payPeriod = PayPeriod.of(date);

        Optional<EmployeeReport> optionalEmployeeReport = employeeReports.stream()
                .filter(employeeReport -> (employeeReport.employeeId.equalsIgnoreCase(employeeId))
                        && (employeeReport.payPeriod.equals(payPeriod)))
                .findFirst();

        EmployeeReport employeeReport = optionalEmployeeReport
                .orElse(EmployeeReport.create(employeeId, payPeriod));

        employeeReports = new ImmutableSet.Builder<EmployeeReport>()
                .addAll(employeeReports)
                .add(employeeReport)
                .build();

        return employeeReport;
    }

    void updateAmount(Double hoursPaid, JobGroup jobGroup) {
        this.amountPaid = this.amountPaid + (hoursPaid * jobGroup.getAmount());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof EmployeeReport)) {
            EmployeeReport other = (EmployeeReport) obj;
            return this.employeeId.equals(other.employeeId)
                    && this.payPeriod.equals(other.payPeriod);
        }
        return false;
    }


    @Override
    public int compareTo(EmployeeReport other) {
        if (this == other) return 0;
        if (this.employeeId.compareTo(other.employeeId) != 0) {
            return this.employeeId.compareTo(other.employeeId);
        } else {
            return this.payPeriod.compareTo(other.payPeriod);
        }
    }

    @Override
    public String toString() {
        return "EmployeeReport{" +
                "employeeId='" + employeeId + '\'' +
                ", payPeriod=" + payPeriod +
                ", amountPaid=" + amountPaid +
                '}';
    }
}


