package com.otaviobraga.cursomc.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.otaviobraga.cursomc.domain.Cidade;
import com.otaviobraga.cursomc.domain.Cliente;
import com.otaviobraga.cursomc.domain.Endereco;
import com.otaviobraga.cursomc.domain.enums.TipoCliente;
import com.otaviobraga.cursomc.dto.ClienteDTO;
import com.otaviobraga.cursomc.dto.ClienteNewDTO;
import com.otaviobraga.cursomc.mapper.ClienteMapper;
import com.otaviobraga.cursomc.repositories.CidadeRepository;
import com.otaviobraga.cursomc.repositories.ClienteRepository;
import com.otaviobraga.cursomc.repositories.EnderecoRepository;
import com.otaviobraga.cursomc.services.exceptions.CidadeNaoEncontradaException;
import com.otaviobraga.cursomc.services.exceptions.DataIntegrityException;
import com.otaviobraga.cursomc.services.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> optionalCliente = repo.findById(id);

		return optionalCliente.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	@Transactional
	public ClienteDTO update(Integer id, Cliente obj) {
		Cliente entity = find(id);
		entity.setNome(obj.getNome());
		entity = repo.save(entity);
		return ClienteMapper.toDto(entity);
	}

	@Transactional
	public void delete(Integer id) {
		Cliente entity = find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir o cliente, pois há pedidods relacionados.");
		}
	}

	public List<ClienteDTO> findAll() {
		List<Cliente> entities = repo.findAll();
		return entities.stream().map(ClienteMapper::toDto).collect(Collectors.toList());
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,
				Sort.by(Sort.Direction.fromString(direction), orderBy));
		return repo.findAll(pageRequest);
	}

	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	public Cliente fromDto(ClienteNewDTO objDto) {
		Optional<Cidade> optionalCidade = cidadeRepository.findById(objDto.getCidadeId());

		if (optionalCidade.isPresent()) {
			Cidade cidade = optionalCidade.get();

			Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
					TipoCliente.toEnum(objDto.getTipo()));
			Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
					objDto.getBairro(), objDto.getCep(), cli, cidade);

			cli.getEnderecos().add(end);
			cli.getTelefones().add(objDto.getTelefone1());
			if (objDto.getTelefone2() != null) {
				cli.getTelefones().add(objDto.getTelefone2());
			}
			if (objDto.getTelefone3()!= null) {
				cli.getTelefones().add(objDto.getTelefone3());
			}
			
			return cli;
		} else {
			throw new CidadeNaoEncontradaException("Cidade não encontrada para o ID: " + objDto.getCidadeId());
		}
	}
}
