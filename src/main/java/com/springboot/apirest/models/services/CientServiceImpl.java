package com.springboot.apirest.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.apirest.models.dao.IClientDao;
import com.springboot.apirest.models.entity.Client;
import com.springboot.apirest.models.entity.Region;
import com.springboot.apirest.models.services.IClientService;

@Service
public class CientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clientDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Client> findAll() {
		return (List<Client>) clientDao.findAll();
	}	

	@Override
	@Transactional(readOnly=true)
	public Page<Client> findAll(Pageable pageable) {
		return clientDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Client findById(Long id) {
		return clientDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Client save(Client cliente) {
		return clientDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clientDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Region> findAllRegiones() {
		return clientDao.findAllRegiones();
	}
}
