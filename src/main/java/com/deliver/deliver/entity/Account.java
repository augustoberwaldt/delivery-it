package com.deliver.deliver.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @NotBlank(message = "Nome e obrigatorio")
    private  String name;


    private  double originalValue;

    @Transient
    private  double correctedValue;

    private int  daysLate;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dtMaturity;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dtPayment;

    private int rate;

    public double getCorrectedValue() {
        return correctedValue;
    }

    public void setCorrectedValue(double correctedValue) {
        this.correctedValue = correctedValue;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(int daysLate) {
        this.daysLate = daysLate;
    }

    public Date getDtMaturity() {
        return dtMaturity;
    }

    public void setDtMaturity(Date dtMaturity) {
        this.dtMaturity = dtMaturity;
    }

    public Date getDtPayment() {
        return dtPayment;
    }

    public void setDtPayment(Date dtPayment) {
        this.dtPayment = dtPayment;
    }
}
