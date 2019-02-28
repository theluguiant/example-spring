package com.springboot.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.apirest.models.entity.Client;
import com.springboot.apirest.models.services.IClientService;

@CrossOrigin(origins = {"http://localhost","http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {
   
	@Autowired
	private IClientService clientService;
	
	@GetMapping("/clients")
	public List<Client> index() {
		return clientService.findAll();
	}
	
	@GetMapping("/clients/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Client show(@PathVariable Long id) {
		return clientService.findById(id);
	}
	
	@PostMapping("/clients")
	@ResponseStatus(HttpStatus.CREATED)
	public Client create(@RequestBody Client cliente) {
		return clientService.save(cliente);
	}
	
	@PostMapping("/clients/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Client update(@RequestBody Client cliente, @PathVariable Long id) {
		Client clienteActual = clientService.findById(id);
		
		clienteActual.setName(cliente.getName());
		clienteActual.setLast(cliente.getLast());
		clienteActual.setEmail(cliente.getEmail());
		
		return clientService.save(clienteActual);
	}
	
	@PostMapping("/clients/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clientService.delete(id);
	}
}
