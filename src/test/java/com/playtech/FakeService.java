package com.playtech;

public class FakeService implements IService{

	@Override
	public String call(double n1, double n2) {
		
		return "{error:\"\", result:\"" + (n1 + n2) + "\"}";
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "add";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPort() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
