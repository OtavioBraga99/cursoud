package com.otaviobraga.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

	public CategoriaDTO find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);

		if (!obj.isPresent()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName(), obj);
		}

		return mapper.toDto(obj.get());
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
		CategoriaDTO entity = find(id);
		entity.setNome(categoriaDto.getNome());
		entity = repo.save(entity);
		return mapper.toDto(entity);
	}

	@Transactional
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
		}
	}
}