package com.springboot.apirest.model;

import com.springboot.apirest.models.entity.Client;
import java.util.List;

public class ResponseModelClient {

	public Client client;
	public List<Client> clients;
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}
