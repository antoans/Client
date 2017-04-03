package com.playtech;

import java.util.Collection;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Client {
	private static final String REGISTRY_ADDRESS = "http://192.12392131.2312:8080/list";
	private Collection<Service> services;

	public Client() {
		services = Collections.<Service> emptyList();
	}

	public static void main(String[] args) {
		Client c = new Client();
		c.getRegistry();

	}

	private void getRegistry() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Collection> response = restTemplate.getForEntity(
				REGISTRY_ADDRESS, Collection.class ); 
		HttpStatus status = response.getStatusCode();
		response.getBody();
		assert status.equals(HttpStatus.OK);
		// fill in services
	}

	private void callService(Service s) {
		callService(s, "2", "1");
	}

	private void callService(Service s, String param1, String param2) {
		RestTemplate restTemplate = new RestTemplate();
		String address = "http://" + s.getAddr() + ":" + s.getPort() + "/"
				+ s.getId() + "/" + param1 + "/" + param2;
		ResponseEntity<String> response = restTemplate.getForEntity(address,
				String.class);
		HttpStatus status = response.getStatusCode();
		assert status.equals(HttpStatus.OK);
		// response contains the result
		String result = "";
		System.out.println(s.getId() + " " + param1 + " " + param2 + " "
				+ result);
	}
}
