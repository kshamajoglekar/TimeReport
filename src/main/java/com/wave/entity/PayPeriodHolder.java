package com.wave.entity;

import com.google.common.collect.ImmutableSet;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public final class PayPeriodHolder {

    private static Set<PayPeriod> payPeriods = Set.of();

    /*Make it singleton*/
    private static PayPeriodHolder payPeriodHolder;

    static{
        payPeriodHolder=new PayPeriodHolder();
    }

    private PayPeriodHolder(){}

    public static PayPeriodHolder get(){
        return payPeriodHolder;
    }
    /*--*/

    public PayPeriod getPayPeriod(final LocalDate date) {

        Optional<PayPeriod> optionalPayPeriod = payPeriods.stream().filter(payPeriod -> {
            if ((date.isEqual(payPeriod.getStartDate()) || date.isAfter(payPeriod.getStartDate()))
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

}
