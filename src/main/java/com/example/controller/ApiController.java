package com.example.controller;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.logging.Logger;

@RestController
public class ApiController {

	RestTemplate rest = new RestTemplate();
	String baseUrl = "http://services.groupkt.com/state/get/USA/";

	@RequestMapping(value = "/USA/stateabbreviation/{stateAbb}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getStateByAbb(@PathVariable String stateAbb) {

		String url = baseUrl + stateAbb;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return rest.exchange(url, HttpMethod.GET, entity, String.class).getBody();
	}

}
