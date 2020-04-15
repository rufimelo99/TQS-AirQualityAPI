package com.example.airqualilty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingController {

    @Autowired
    private CityInfoService cityInfoService;
    @Autowired
    private CityInfoRepository cityInfoRepository;

    /*
	@Autowired
	private CityInfoService cityInfoService;

	@Autowired
	private CityInfoRepository cityInfoRepository;

	@GetMapping("/listAllCityInfo")
	public String listAllCityInfo( Model model) {
		model.addAttribute("AllCitiesInfo", cityInfoRepository.findAll());
		return "ResultPage";
	}



	@GetMapping("/CityInfo")
	public String getAllCityInfo(Model model){
		CityInfo_Collection city_store = new CityInfo_Collection();
		city_store.addCityInfo(new CityInfo("1", "faro"));
		model.addAttribute("store", city_store);

		return "AllCitiesInformation";
	}

	@PostMapping("/CityInfo/save")
	public void addCityInfo(@ModelAttribute CityInfo_Collection store, Model model) {
		System.out.println("entra aqui");

		List<CityInfo> ci  = store.getAllCityInfos();
		for(CityInfo c : ci){
			cityInfoService.add(c);
			System.out.println(c.getName());
		}
	}
	@GetMapping("/CityInfo/{id}")
	public CityInfo getCityInfoById(@PathVariable String id, Model model){
		return cityInfoService.getCityInfoById(id);
	}
*/
	/*
	@RequestMapping("/CityInfo")
	public List<CityInfo> getAllCityInfo(Model model){
		CityInfo_Collection city_store = new CityInfo_Collection();
		city_store.addCityInfo(new CityInfo("1", "faro"));
		model.addAttribute("store", city_store);



		return cityInfoService.getAllCityInfos();
	}
	*/
    /*
    @RequestMapping(method = RequestMethod.POST, value = "/CityInfo")
    public void addCityInfo(@RequestBody CityInfo cityInfo, Model model){
        cityInfoService.add(cityInfo);

        model.addAttribute("CityInfo_to_add", cityInfo);
    }

    @GetMapping("/CityInfo")
    public String CityInfo(@RequestParam(value = "station_uuid") String station_uuid, Model model) {
        //return new City(name);
        //return ResponseEntity.ok().body(new City(name));
        model.addAttribute("station_uuid", station_uuid);
        return "ResultPage";
    }
    */


    @GetMapping("/CityInfo/{id}")
    public String CityInfo(@PathVariable(value="id") String id, Model model) {
        model.addAttribute("id", id);
        CityInfo ci  = cityInfoService.getCityInfoById(id);
        model.addAttribute("City", ci);
        model.addAttribute("last_id", id);
        return "ResultPage";
    }

    @GetMapping("/CityInfo")
    public String CityInfo(Model model) {
        model.addAttribute("cityInfoRepository", cityInfoRepository.findAll());
        return "AllCitiesInformation";
    }
    @GetMapping("/CityInfo/ByName/{name}")
    public String getAllCityInfoByName(@PathVariable(value = "name") String name, Model model){
        model.addAttribute("cityInfoRepositoryByName", cityInfoService.getCityInfoByName(name));
        return "AllCitiesInformationByName";
    }

}
