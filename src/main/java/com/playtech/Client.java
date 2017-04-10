package com.playtech;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Client {
	private static final String SERVICE_NOT_AVAILABLE = "Service not available.";
	private Collection<IService> services;

	public String processServiceResponse(String response) {
		ServiceResponse sr = null;
		try {
			sr = new ObjectMapper().readValue(response, ServiceResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
			return "Invalid response format from Service!";
		}
		if (!sr.error.isEmpty()) {
			return sr.error;
		}
		return sr.result;
	}

	public String getTextFromService(IService s, int arg1, int arg2) {
		String result;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.getForEntity("http://" + s.getAddr() + ":"
					+ s.getPort(), String.class);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return SERVICE_NOT_AVAILABLE;
		}

		if (response.getStatusCode() != HttpStatus.OK) {
			System.out.println("Connection failed. HTTP Status: "
					+ response.getStatusCodeValue());
			return SERVICE_NOT_AVAILABLE;
		}

		result = processServiceResponse(response.getBody());
		return result;
	}

	public Map<String, String> getResponseFromRegistry() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.getForEntity("http://", String.class);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new HashMap<String, String>();
		}

		if (response.getStatusCode() != HttpStatus.OK) {
			System.out.println("Connection failed. HTTP Status: "
					+ response.getStatusCodeValue());
			return new HashMap<String, String>();
		}

		return processRegistryResponse(response.getBody());
	}

	private Map<String, String> processRegistryResponse(String body) {
		Map<String, String> res = new HashMap<>();
		try {
			services = new ObjectMapper().readValue(body,
					new TypeReference<Collection<IService>>() {});
		} catch (IOException e) {
			e.printStackTrace();
			return res;
		}
		for (IService s : services) {
			res.put(s.getId(), "http://" + s.getAddr() + ":" + s.getPort());
		}
		return res;
	}
}
