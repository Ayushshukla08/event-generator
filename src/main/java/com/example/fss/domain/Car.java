package com.example.fss.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Car implements Serializable {
    /**
     * City name where the car arrived
     */
    private String city;

    /**
     * Fuellid open or closed
     */
    private Boolean fuellid;

    public Car(String city, Boolean fuellid) {
        this.city = city;
        this.fuellid = fuellid;
    }

//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public Boolean getFuellid() {
//        return fuellid;
//    }
//
//    public void setFuellid(Boolean fuellid) {
//        this.fuellid = fuellid;
//    }
    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", fuellid=" + fuellid +
                '}';
    }
}
