package com.wave.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.ImmutableSet;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

final public class PayPeriod implements Comparable<PayPeriod> {
    private static Set<PayPeriod> payPeriods = Set.of();
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;


    private PayPeriod() {
    }

    static void initialize() {
        payPeriods = Set.of();
    }

    private static int getPayPeriodStartDate(final LocalDate date) {
        return (date.getDayOfMonth() >= 15) ? 16 : 1;
    }

    private static int getPayPeriodEndDate(final LocalDate date) {
        return (date.getDayOfMonth() > 15) ? date.lengthOfMonth() : 15;
    }

    private static PayPeriod create(final LocalDate date) {
        final PayPeriod payPeriod = new PayPeriod();
        payPeriod.startDate = LocalDate.of(date.getYear(), date.getMonth(), getPayPeriodStartDate(date));
        payPeriod.endDate = LocalDate.of(date.getYear(), date.getMonth(), getPayPeriodEndDate(date));
        return payPeriod;
    }

    static PayPeriod of(final LocalDate date) {

        Optional<PayPeriod> optionalPayPeriod = payPeriods.stream().filter(payPeriod -> {
            if ((date.isEqual(payPeriod.startDate) || date.isAfter(payPeriod.startDate))
                    && (date.isEqual(payPeriod.getEndDate()) || date.isBefore(payPeriod.getEndDate()))) {
                return true;
            }
            return false;
        }).findFirst();

        PayPeriod payPeriod = optionalPayPeriod
                .orElse(PayPeriod.create(date));

        payPeriods = new ImmutableSet.Builder<PayPeriod>()
                .addAll(payPeriods).add(payPeriod).build();

        return payPeriod;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if ((obj != null) && (obj instanceof PayPeriod)) {
            PayPeriod other = (PayPeriod) obj;
            if (this.startDate.equals(other.startDate) && this.endDate.equals(other.endDate)) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return "PayPeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public int compareTo(PayPeriod other) {
        if (this == other || this.startDate.equals(other.startDate)) return 0;
        if (this.startDate.isBefore(other.startDate)) return -1;
        return 1;
    }
}
