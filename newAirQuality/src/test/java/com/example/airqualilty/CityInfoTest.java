package com.example.airqualilty;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityInfoTest {
    @Test
    public void CreateCity() {
        CityInfo ci = new CityInfo();
        ci.setId("1");
        ci.setName("London");
        ci.setNo2(6.7);
        ci.setH(4.5);
        ci.setD(1.3);
        ci.setO3(3.4);
        ci.setP(1.1);

        assertEquals(ci.getId(), "1");
        assertEquals(ci.getName(), "London");
        assertEquals(ci.getNo2(), 6.7);
        assertEquals(ci.getH(), 4.5);
        assertEquals(ci.getD(), 1.3);
        assertEquals(ci.getO3(), 3.4);
        assertEquals(ci.getP(), 1.1);
    }
}
