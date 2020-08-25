package com.wave.entity;

import lombok.Getter;

public final class EmployeeReport implements Comparable<EmployeeReport> {

    @Getter
    private String employeeId;
    @Getter
    private PayPeriod payPeriod;
    @Getter
    private Double amountPaid;

    private EmployeeReport() {
        amountPaid = 0d;
    }

    public static EmployeeReport create(final TimeReport timeReport, final PayPeriod payPeriod) {
        EmployeeReport employeeReport = new EmployeeReport();
        employeeReport.employeeId = timeReport.getEmployeeId();
        employeeReport.payPeriod = payPeriod;

        return employeeReport;
    }

    void updateAmount(Double hoursPaid, JobGroup jobGroup) {
        this.amountPaid = this.amountPaid + (hoursPaid * jobGroup.getAmount());
    }

    @Override
    public String toString() {
        return "EmployeeReport{" +
                "employeeId='" + employeeId + '\'' +
                ", payPeriod=" + payPeriod +
                ", amountPaid=" + amountPaid +
                '}';
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

}
