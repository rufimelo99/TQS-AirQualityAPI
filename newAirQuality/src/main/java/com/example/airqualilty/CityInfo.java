package com.example.airqualilty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
public class CityInfo{
    @Id
    private String id;
    private String name;
    private Double pm25;
    private Double pm10;
    private Double o3;
    private Double no2;
    private Double so2;
    private Double co;
    private Double t;
    private Double w;
    private Double r;
    private Double h;
    private Double d;
    private Double p;


    public CityInfo(){

    }

    public CityInfo(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPm25() {
        return pm25;
    }

    public void setPm25(Double pm25) {
        this.pm25 = pm25;
    }

    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }

    public Double getO3() {
        return o3;
    }

    public void setO3(Double o3) {
        this.o3 = o3;
    }

    public Double getNo2() {
        return no2;
    }

    public void setNo2(Double no2) {
        this.no2 = no2;
    }

    public Double getSo2() {
        return so2;
    }

    public void setSo2(Double so2) {
        this.so2 = so2;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public Double getT() {
        return t;
    }

    public void setT(Double t) {
        this.t = t;
    }

    public Double getW() {
        return w;
    }

    public void setW(Double w) {
        this.w = w;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Double getH() {
        return h;
    }

    public void setH(Double h) {
        this.h = h;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }
}