package com.otaviobraga.cursomc.mapper;

import org.mapstruct.Mapper;

import com.otaviobraga.cursomc.domain.Categoria;
import com.otaviobraga.cursomc.dto.CategoriaDTO;

import jakarta.persistence.Entity;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaDTO> {
	CategoriaDTO toDto(Categoria categoria);

	Categoria fromDto(CategoriaDTO categoriaDTO);
}
