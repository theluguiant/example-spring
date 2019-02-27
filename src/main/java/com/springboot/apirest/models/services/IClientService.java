package com.springboot.apirest.models.services;

import java.util.List;
import com.springboot.apirest.models.entity.Client;

public interface IClientService {

	public List<Client> findAll();
	
}
