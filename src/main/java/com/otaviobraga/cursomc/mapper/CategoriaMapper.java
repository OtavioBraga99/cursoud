package com.otaviobraga.cursomc.mapper;

import org.mapstruct.Mapper;

import com.otaviobraga.cursomc.domain.Categoria;
import com.otaviobraga.cursomc.dto.CategoriaDTO;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaDTO>{

	CategoriaDTO toDto(CategoriaDTO entity);


}
