package com.requisitos.hellkaiser.rm.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GiphyService {
    String api = "//api.giphy.com/v1/gifs/search?api_key=dc6zaTOxFJmzC&limit=1&q=";

    public String get(String search){
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(this.api + search, String.class);
        return result;
    }
}
