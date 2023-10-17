package com.otaviobraga.cursomc.mapper;

import com.otaviobraga.cursomc.domain.Cliente;
import com.otaviobraga.cursomc.dto.ClienteDTO;

public class ClienteMapper {
	
	
	
	 public static ClienteDTO toDto(Cliente cliente) {
	        ClienteDTO clienteDTO = new ClienteDTO(cliente);
	        clienteDTO.setId(cliente.getId());
	        clienteDTO.setNome(cliente.getNome());
	        clienteDTO.setEmail(cliente.getEmail());
	        return clienteDTO;
	    }

	    public static Cliente toEntity(ClienteDTO clienteDTO) {
	        Cliente cliente = new Cliente();
	        cliente.setId(clienteDTO.getId());
	        cliente.setNome(clienteDTO.getNome());
	        cliente.setEmail(clienteDTO.getEmail());
	        return cliente;
	    }
}
