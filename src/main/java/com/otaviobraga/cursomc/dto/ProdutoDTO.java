package com.otaviobraga.cursomc.dto;

import java.io.Serializable;

import com.otaviobraga.cursomc.domain.Produto;

public class ProdutoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private double preco;
	
	public ProdutoDTO() {
	}
	
	public ProdutoDTO(Produto obj) {
		id = obj.getid();
		nome = obj.getNome();
		preco = obj.getPreco();
	}

	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
}
