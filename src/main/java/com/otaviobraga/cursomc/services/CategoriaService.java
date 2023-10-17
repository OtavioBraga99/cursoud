package com.otaviobraga.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.otaviobraga.cursomc.domain.Categoria;
import com.otaviobraga.cursomc.dto.CategoriaDTO;
import com.otaviobraga.cursomc.mapper.CategoriaMapper;
import com.otaviobraga.cursomc.repositories.CategoriaRepository;
import com.otaviobraga.cursomc.services.exceptions.DataIntegrityException;
import com.otaviobraga.cursomc.services.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	@Autowired
	private CategoriaMapper mapper;

	public CategoriaDTO findAll(Integer id) {
		return null;
	}

	@Transactional
	public CategoriaDTO insert(Object obj) {
		Categoria entity = mapper.toEntity(obj);
		entity.setId(null);
		Categoria categoriaSalva = repo.save(entity);
		return mapper.toDto(categoriaSalva);
	}

	@Transactional
	public CategoriaDTO update(Integer id, Categoria obj) {
	    CategoriaDTO existingCategoria = findAll(id);

	    if (existingCategoria != null) {
	        existingCategoria.setNome(obj.getNome());
	        Categoria entity = mapper.toEntity(existingCategoria);
	        entity = repo.save(entity);
	        return mapper.toDto(entity);
	    } else {
	        throw new ObjectNotFoundException("Categoria não encontrada! Id: " + id + ", Tipo: " + CategoriaDTO.class.getName());
	    }
	}

	@Transactional
	public void delete(Integer id) {
		findAll(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of (page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	public Categoria fromDto(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
}