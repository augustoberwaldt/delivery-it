package com.deliver.deliver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AccountWrapper {

    private  String name;

    private  double originalValue;

    private  double correctedValue;


    private int  daysLate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dtPayment;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(double originalValue) {
        this.originalValue = originalValue;
    }

    public double getCorrectedValue() {
        return correctedValue;
    }

    public void setCorrectedValue(double correctedValue) {
        this.correctedValue = correctedValue;
    }

    public int getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(int daysLate) {
        this.daysLate = daysLate;
    }

    public Date getDtPayment() {
        return dtPayment;
    }

    public void setDtPayment(Date dtPayment) {
        this.dtPayment = dtPayment;
    }
}
