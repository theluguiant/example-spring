package com.springboot.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.apirest.models.entity.Client;
import com.springboot.apirest.models.entity.Region;

public interface IClientService {

	public List<Client> findAll();
	
	public Page<Client> findAll(Pageable pageable);
	
	public Client findById(Long id);
	
	public Client save(Client cliente);
	
	public void delete(Long id);
	
	public List<Region> findAllRegiones();
	
}
