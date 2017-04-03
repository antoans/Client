package com.playtech;

public class Service {
	private String id;
	private String description;
	private String addr;
	private String port;
	
	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getAddr() {
		return addr;
	}

	public String getPort() {
		return port;
	}

	public Service(String id, String description, String addr, String port) {
		this.id = id;
		this.description = description;
		this.addr = addr;
		this.port = port;
	}

}


//{ // IN 
//	Id:{“add”|”subtract”|”multiply”...},
//	description:”short description of the functionality for humans”
//	addr:”192.168.1.10”,
//	port:1024,
//}

