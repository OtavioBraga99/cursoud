package com.otaviobraga.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otaviobraga.cursomc.domain.Pedido;
import com.otaviobraga.cursomc.repositories.PedidoRepository;
import com.otaviobraga.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Optional<Pedido> buscar(Integer id) {
	    Optional<Pedido> obj = repo.findById(id);
	    
	    if (!obj.isPresent()) {
	        throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName(), obj);
	    }
	    
	    return obj;
	}

}
