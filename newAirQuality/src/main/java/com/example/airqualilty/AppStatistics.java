package com.example.airqualilty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppStatistics {
    @Id
    private Integer id;

    private Integer total_requests = 0;
    private Integer total_hits = 0;
    private Integer total_misses = 0;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotal_requests() {
        return total_requests;
    }

    public void add_requests() {
        this.total_requests += 1;
    }

    public Integer getTotal_hits() {
        return total_hits;
    }

    public void add_hits() {
        this.total_hits += 1;
    }

    public Integer getTotal_misses() {
        return total_misses;
    }

    public void add_misses() {
        this.total_misses += 1;
    }



    @Override
    public String toString() {
        return "Cache Statistics: " + "\nTotal Requests: " +total_requests +"\nTotal Hits: " + total_hits + "\nTotal Misses: " + total_misses;
    }

}
