package com.example.airqualilty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class RController {
    @Autowired
    private CityInfoService cityInfoService;
    @Autowired
    private CityInfoRepository cityInfoRepository;
    /*
        @GetMapping("/CityInfo")
        public List<CityInfo> getAllCityInfo() {
            return cityInfoRepository.findAll();
        }

    @GetMapping("/CityInfo/{id}")
    public String getCityInfoById(@PathVariable(value = "id") String id, Model model){
        //return cityInfoService.getCityInfoById(id);
        return "ResultPage";
    }
    */

    @PostMapping ("/CityInfo")
    public void addCityInfo(@RequestBody CityInfo cityInfo) {
        cityInfoService.add(cityInfo);
    }

    /*
    @GetMapping("/CityInfo/ByName/{name}")
    public List<CityInfo> getAllCityInfoByName(@PathVariable(value = "name") String name, Model model){
        return cityInfoService.getCityInfoByName(name);
    }
    */
}
