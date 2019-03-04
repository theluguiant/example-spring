package com.springboot.apirest.models.dao;

import com.springboot.apirest.models.entity.Client;
import com.springboot.apirest.models.entity.Region;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IClientDao extends JpaRepository<Client, Long> {
	
	@Query("from Region")
	public List<Region> findAllRegiones();
	
}
