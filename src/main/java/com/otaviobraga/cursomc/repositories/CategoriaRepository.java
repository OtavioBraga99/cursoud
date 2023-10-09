package com.otaviobraga.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otaviobraga.cursomc.domain.Categoria;
import com.otaviobraga.cursomc.dto.CategoriaDTO;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	CategoriaDTO save(CategoriaDTO entity);

}
