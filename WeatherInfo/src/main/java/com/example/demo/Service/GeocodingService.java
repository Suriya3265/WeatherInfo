package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Model.GeocodingResponse;

@Service
public class GeocodingService {

    @Value("${geocoding.api.key}")
    private String apiKey;

    private final String geocodingUrl = "https://api.openweathermap.org/geo/1.0/zip?zip={pincode},{country}&appid={apiKey}";

    private RestTemplate restTemplate = new RestTemplate();

    public double[] getLatLonFromPincode(String pincode) {
        String url = geocodingUrl.replace("{pincode}", pincode)
                                  .replace("{country}", "IN") // Assuming India
                                  .replace("{apiKey}", apiKey);
        GeocodingResponse response = restTemplate.getForObject(url, GeocodingResponse.class);
        return new double[]{response.getLat(), response.getLon()};
    }
}
