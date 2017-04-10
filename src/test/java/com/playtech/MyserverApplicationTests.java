package com.playtech;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyserverApplicationTests {

//	@Test
	public void getInfoFromRegistry() throws IOException{
		ResponseEntity<String> response = mock(ResponseEntity.class);
		when(response.getBody()).thenReturn(
				readFile("registry.json", StandardCharsets.UTF_8));
		String body = response.getBody();
		List<Service> serviceEntries = new ObjectMapper().readValue(body, 
				new TypeReference<List<Service>>(){});
		
	}

	private static String readFile(String path, Charset encoding)
			throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	private String callServiceWithArguments(String str, double n1, double n2){
		List<IService> services = new ArrayList<>();
		boolean isServiceValid = false;
		String result = "";
		services.add(new FakeService());
		for (IService i : services) {
			if(i.getId().equals(str)) {
				result = i.call(n1, n2);
				isServiceValid = true;
			}
		}
		
		if(isServiceValid){
			return result;
		}else{
			return "An error occured on the service, there is no service: " + str;
		}
		
	}

	@Test
	public void testService() {
		Assert.assertEquals(callServiceWithArguments("add", 11, 11), "{error:\"\", result:\"22\"}");
	}

}