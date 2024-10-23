package com.example.demo.Service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Model.Weatherinfo;
import com.example.demo.Repository.WeatherDataRepository;

@Service
public class weatherService {
	
	@Value("${openWeather.api.key}")
	private String apikey;
	

    private final String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}";
	@Autowired
	private RestTemplate restTemplate;

	
	@Autowired
	private GeocodingService geocodingService;
	
	@Autowired
	private WeatherDataRepository weatherDataRepository;
	
	public Weatherinfo getWeatherinfo(String pincode, LocalDate date) {
		Optional<Weatherinfo> optionalWeatherData=weatherDataRepository.findByPincodeAndDate(pincode, date);
		
		if(optionalWeatherData.isPresent()) {
			return optionalWeatherData.get();
		}
		else {
			double[] latLon = geocodingService.getLatLonFromPincode(pincode);
			
			double lat = latLon[0];
			double lon = latLon[1];
			
			String weatherdata = fetchWeatherInfo(lat, lon);
			Weatherinfo weatherinfo=new Weatherinfo(pincode,lat,lon,date,weatherdata);
			weatherDataRepository.save(weatherinfo);
			
			return weatherinfo;
		}
     
	}

private String fetchWeatherInfo(double lat, double lon) {
    String url = weatherUrl.replace("{lat}", String.valueOf(lat))
                           .replace("{lon}", String.valueOf(lon))
                           .replace("{apiKey}", apikey);
    return restTemplate.getForObject(url, String.class);


}
}
