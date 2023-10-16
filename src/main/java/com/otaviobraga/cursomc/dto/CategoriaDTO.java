package com.otaviobraga.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.otaviobraga.cursomc.domain.Categoria;
public class CategoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public CategoriaDTO() {
	}
	public CategoriaDTO(Categoria obj){
		id = obj.getId();
		nome = obj.getNome();
	}
	
	private Integer id;
	
	@NotNull(message = "Preenchimento obrigatório")
	@Size(min = 5, max = 80, message = "O tamanho deve estar entre 5 e 80 caracteres")
	private String nome;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
}
