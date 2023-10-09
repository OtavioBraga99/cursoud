package com.otaviobraga.cursomc.mapper;

import com.otaviobraga.cursomc.domain.Categoria;
import com.otaviobraga.cursomc.dto.CategoriaDTO;

public interface BaseMapper<E, D> {

	E toEntity(D dto);
	
	D toDto(Categoria categoriaSalva);
}
