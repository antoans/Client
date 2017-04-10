package com.playtech;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests {

	@Test
	public void testErrorResponse() {
		Client c = new Client();
		Assert.assertEquals(
				c.processServiceResponse("{\"error\":\"Error!\", \"result\":\"22.5\"}"),
				"Error!");
	}

	@Test
	public void testValidResponse() {
		Client c = new Client();
		Assert.assertEquals(
				c.processServiceResponse("{\"error\":\"\", \"result\":\"22.5\"}"),
				"22.5");
	}

	@Test
	public void testServiceUnavailable() {
		String expected = "Service not available.";
		Client c = new Client();
		IService s = new Service("dsa", "sadas", "413413432", "-4444");
		String actual = c.getTextFromService(s, 1, 1);
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void testHttpStatusNotOk() {
		String expected = "Service not available.";
		Client c = new Client();
		IService s = new Service("dsa", "sadas", "192.168.1.10", "8080");
		String actual = c.getTextFromService(s, 1, 1);
		Assert.assertEquals(actual, expected);
	}
	
	//"Registry not available."
	@Test
	public void testRegistryUnavailable() {
		String expected = "Registry not available.";
		Client c = new Client();
		c.getResponseFromRegistry();
	}
}
