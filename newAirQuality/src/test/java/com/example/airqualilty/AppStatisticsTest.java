package com.example.airqualilty;

import org.junit.jupiter.api.Test;

import javax.persistence.Entity;
import javax.persistence.Id;

import static org.junit.Assert.assertEquals;
public class AppStatisticsTest {

    @Test
    public void basicTestStatistics(){
        AppStatistics as = new AppStatistics();
        as.setId(0);

        as.add_hits();
        as.add_misses();
        as.add_hits();
        as.add_requests();

        assertEquals(java.util.Optional.ofNullable(as.getTotal_hits()), java.util.Optional.ofNullable(2));

        assertEquals(java.util.Optional.ofNullable(as.getTotal_misses()), java.util.Optional.ofNullable(1));

        assertEquals(java.util.Optional.ofNullable(as.getTotal_requests()), java.util.Optional.ofNullable(1));



    }

}
