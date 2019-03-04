package com.springboot.apirest.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.apirest.models.entity.Client;
import com.springboot.apirest.models.entity.Region;
import com.springboot.apirest.models.services.IClientService;

@CrossOrigin(origins = {"http://localhost","http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {
   
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp = LocalDateTime.now();
	
	@Autowired
	private IClientService clientService;
	
	@GetMapping("/clients")
	public ResponseEntity<?> index() {
		
		List<Client> clientList = null;
		
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", timestamp);
		
		try {
			clientList = clientService.findAll();
		} catch(DataAccessException e) {
			response.put("message", "Error");
			response.put("errorStatus", true);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("errorStatus", false);
		response.put("response",clientList);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/clients/pages/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page) {
		
		Page<Client> clientList = null;
		
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", timestamp);
		
		try {
			clientList = clientService.findAll(PageRequest.of(page, 4));
		} catch(DataAccessException e) {
			response.put("message", "Error");
			response.put("errorStatus", true);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("errorStatus", false);
		response.put("response",clientList);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/clients/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Client client = null;
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", timestamp);
		
		try {
			client = clientService.findById(id);
		} catch(DataAccessException e) {
			response.put("message", "Error");
			response.put("errorStatus", true);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (client == null) {
			response.put("message", "Cliente no encontrado");
			response.put("errorStatus", true);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		
		response.put("errorStatus", false);
		response.put("response", client);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/clients")
	public ResponseEntity<?> create(@Valid  @RequestBody Client cliente, BindingResult result) {
		Client clientNew = null;
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", timestamp);
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
						.stream()
						.map( err -> "Error '"+ err.getField() + "': "+ err.getDefaultMessage())
						.collect(Collectors.toList());
			
			response.put("errorStatus", true);
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			clientNew = clientService.save(cliente);
		} catch(DataAccessException e) { 
			response.put("message", "Error");
			response.put("errorStatus", true);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("errorStatus", false);
		response.put("response", clientNew);
		return new ResponseEntity<Client>(clientNew, HttpStatus.CREATED);
	}
	
	@PostMapping("/clients/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Client cliente, BindingResult result, @PathVariable Long id) {
		
		Client clienteActual = clientService.findById(id);
		Client clienteUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", timestamp);
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
						.stream()
						.map( err -> {
							return "Error '"+ err.getField() + "': "+ err.getDefaultMessage();
						})
						.collect(Collectors.toList());
			response.put("errorStatus", true);
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if (clienteActual == null) {
			response.put("message", "Cliente no encontrado");
			response.put("errorStatus", true);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		
		try {
			clienteActual.setName(cliente.getName());
			clienteActual.setLast(cliente.getLast());
			clienteActual.setEmail(cliente.getEmail());
			 clienteUpdate = clientService.save(clienteActual);
			
		} catch(DataAccessException e) { 
			response.put("message", "Error");
			response.put("errorStatus", true);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	    response.put("errorStatus", false);
		response.put("response", clienteUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/clients/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", timestamp);
		try {
			clientService.delete(id);
		} catch(DataAccessException e) { 
			response.put("message", "Error");
			response.put("errorStatus", true);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("errorStatus", false);
		response.put("message", "delete success" );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/clients/regions")
	public ResponseEntity<?> listRegions() {
		
		List<Region> regions = null;
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", timestamp);
		
		try {
			
			regions = clientService.findAllRegiones();
			
		} catch(DataAccessException e) {  
			
			response.put("message", "Error");
			response.put("errorStatus", true);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		if ( regions == null ) {
			response.put("message", "Error");
			response.put("errorStatus", true);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("errorStatus", false);
		response.put("response", regions);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
}
