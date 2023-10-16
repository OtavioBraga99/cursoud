package com.otaviobraga.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import com.otaviobraga.cursomc.domain.Categoria;
import com.otaviobraga.cursomc.dto.CategoriaDTO;
import com.otaviobraga.cursomc.mapper.CategoriaMapper;
import com.otaviobraga.cursomc.repositories.CategoriaRepository;
import com.otaviobraga.cursomc.services.exceptions.DataIntegrityException;

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
	public CategoriaDTO insert(CategoriaDTO dto) {
		Categoria entity = mapper.toEntity(dto);
		entity.setId(null);
		Categoria categoriaSalva = repo.save(entity);
		return mapper.toDto(categoriaSalva);
	}

	@Transactional
	public CategoriaDTO update(Integer id, CategoriaDTO categoriaDto) {
		CategoriaDTO entity = findAll(id);
		entity.setNome(categoriaDto.getNome());
		entity = repo.save(entity);
		return mapper.toDto(entity);
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
}