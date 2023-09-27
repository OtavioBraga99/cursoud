package com.otaviobraga.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pedido pendente"), 
	QUITADO(2, "Pedido quitado"), 
	CANCELADO(3, "Pedido Cancelado");

	private int cod;
	private String descricao;

	private EstadoPagamento(int cod, String descriString) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
