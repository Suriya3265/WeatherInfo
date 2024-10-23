package com.example.demo.Controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Weatherinfo;
import com.example.demo.Service.weatherService;

@RestController
public class WeatherController {
	
	@Autowired
	private weatherService weatherService;

	@GetMapping("/weather")
	public Weatherinfo getWeather(@RequestParam String pincode,@RequestParam String date) {
		
		LocalDate localDate=LocalDate.parse(date);
		
		return weatherService.getWeatherinfo(pincode, localDate);
	}
}
