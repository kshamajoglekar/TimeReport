package com.wave.entity;

public enum JobGroup {
    A(20), B(30);

    public double getAmount() {
        return amount;
    }

    private final double amount;

    JobGroup(double amount) {
        this.amount = amount;
    }

}