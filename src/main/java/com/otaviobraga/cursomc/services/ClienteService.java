package com.otaviobraga.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otaviobraga.cursomc.domain.Cliente;
import com.otaviobraga.cursomc.repositories.ClienteRepository;
import com.otaviobraga.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Optional<Cliente> find(Integer id) {
	    Optional<Cliente> obj = repo.findById(id);
	    
	    if (!obj.isPresent()) {
	        throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName(), obj);
	    }
	    
	    return obj;
	}

}
