package com.otaviobraga.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otaviobraga.cursomc.domain.Categoria;
import com.otaviobraga.cursomc.dto.CategoriaDTO;
import com.otaviobraga.cursomc.mapper.CategoriaMapper;
import com.otaviobraga.cursomc.repositories.CategoriaRepository;
import com.otaviobraga.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	@Autowired
	private CategoriaMapper mapper;

	public Optional<Categoria> find(Integer id) {
	    Optional<Categoria> obj = repo.findById(id);
	    
	    if (!obj.isPresent()) {
	        throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName(), obj);
	    }
	    
	    return obj;
	}

	public CategoriaDTO insert(CategoriaDTO dto) {
		
		Categoria entity = mapper.toEntity(dto);
		entity.setId(null);
		Categoria categoriaSalva = repo.save(entity);
		return mapper.toDto(categoriaSalva);
	}
	
	public CategoriaDTO update(CategoriaDTO categoriaDto) {
		find(obj.getId());
		Categoria entity = mapper.toEntity(categoriaDto);
		return null;
	}
}
