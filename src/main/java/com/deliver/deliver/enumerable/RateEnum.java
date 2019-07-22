package com.deliver.deliver.enumerable;

public enum RateEnum {

    RATE1("2"),
    RATE2("3"),
    RATE3("5"),
    SWEAR1("0.1"),
    SWEAR2("0.2"),
    SWEAR3("0.3");

    private String value;

    RateEnum(String s) {
        this.value = s;
    }

    public String getValue() {
        return  this.value;
    }

}
