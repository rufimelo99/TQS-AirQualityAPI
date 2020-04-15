package com.example.airqualilty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityInfoService {

    @Autowired
    private CityInfoRepository cityInfoRepository;
    /*
    private List<CityInfo> cityInfos = new ArrayList<>(Arrays.asList(
            new CityInfo("1", "Lisboa"),
            new CityInfo("2", "Porto")
    ));
    */
    public List<CityInfo> getAllCityInfos(){
        //return cityInfos;
        List<CityInfo> cityInfos = new ArrayList<>();
                cityInfoRepository.findAll()
                .forEach(cityInfos::add);
                return cityInfos;

    }

    public CityInfo getCityInfoById(String id){
        //return cityInfos.stream().filter(t -> t.getId().equals(id)).findFirst().get();
        return cityInfoRepository.findById(id).orElse(null);
    }

    public List<CityInfo> getCityInfoByName(String name){
        List<CityInfo> cityInfos = new ArrayList<>();
        cityInfos = cityInfoRepository.findAll();
        List<CityInfo> final_cityInfos = new ArrayList<>();
        for (CityInfo ci : cityInfos) {
            if (ci.getName().toLowerCase().contains(name.toLowerCase())) {
                final_cityInfos.add(ci);
            }
        }
        return final_cityInfos;
    }

    public void add(CityInfo cityInfo) {
        //cityInfos.add(cityInfo);
        cityInfoRepository.save(cityInfo);
    }
}
