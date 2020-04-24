package com.example.airqualilty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.ServerSocket;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CityInfoServiceTest {


    @Mock(lenient = true)
    private CityInfoRepository cityInfoRepository;

    @InjectMocks
    private CityInfoService cityInfoService;



    @BeforeEach
    public void setUp() {
        CityInfo london = new CityInfo("1","London");
        List<CityInfo> London = Arrays.asList(london);

        CityInfo porto = new CityInfo("2","Porto");
        List<CityInfo> Porto = Arrays.asList(porto);

        CityInfo lisboa = new CityInfo("3","Lisboa");
        List<CityInfo> Lisboa = Arrays.asList(lisboa);

        cityInfoService.add(london);
        cityInfoService.add(porto);
        cityInfoService.add(lisboa);
        List<CityInfo> AllCityInfos = Arrays.asList(london, porto, lisboa);


    }
    @Test
    public void CityInfoBehaviour(){
        CityInfo london = new CityInfo("1","London");
        List<CityInfo> London = Arrays.asList(london);

        CityInfo porto = new CityInfo("2","Porto");
        List<CityInfo> Porto = Arrays.asList(porto);

        CityInfo lisboa = new CityInfo("3","Lisboa");
        List<CityInfo> Lisboa = Arrays.asList(lisboa);

        List<CityInfo> AllCityInfos = Arrays.asList(london, porto, lisboa);

        Mockito.when(cityInfoService.getCityInfoByName(london.getName())).thenReturn(London);
        Mockito.when(cityInfoService.getCityInfoByName(porto.getName())).thenReturn(Porto);
        Mockito.when(cityInfoService.getCityInfoByName(lisboa.getName())).thenReturn(Lisboa);
        Mockito.when(cityInfoService.getCityInfoByName("WrongKeyword")).thenReturn(null);

        //?
        //assertEquals(cityInfoService.getAllCityInfos(), AllCityInfos);
        //assertEquals(cityInfoService.getCityInfoById(london.getId()), "1");

        Mockito.when(cityInfoService.getCityInfoById("27")).thenReturn(null);

    }
    @Test
    public void whenkeyword_thenCitiesShouldBeFound() {
        String name = "Lisboa";
        List<CityInfo> results = cityInfoService.getCityInfoByName(name);

        for (CityInfo ci :results) {
            System.out.println(ci.getName());
            assertTrue( name.contains(ci.getName()) || ci.getName().contains(name) ,"The result when searching for keyword dont match");
        };
    }

    @Test
    public void whenWrongKeyword_thenCitiesShouldNotBeFound() {
        List<CityInfo> results =cityInfoService.getCityInfoByName("WrongKeyword");
        assertThat(results).isEmpty();
    }


    @Test
    public void whenInValidId_thenCityShouldNotBeFound() {
        CityInfo ci = cityInfoService.getCityInfoById("27");
        assertThat(ci).isNull();
    }

    @Test
    public void whenAskedForAllCities_thenReturnAll(){
        List<CityInfo> citiesList = cityInfoService.getAllCityInfos();
        assertEquals(citiesList.size(), cityInfoRepository.findAll().size());
    }
}
