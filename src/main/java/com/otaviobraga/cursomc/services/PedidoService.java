package com.otaviobraga.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otaviobraga.cursomc.domain.ItemPedido;
import com.otaviobraga.cursomc.domain.PagamentoComBoleto;
import com.otaviobraga.cursomc.domain.Pedido;
import com.otaviobraga.cursomc.domain.enums.EstadoPagamento;
import com.otaviobraga.cursomc.repositories.ItemPedidoRepository;
import com.otaviobraga.cursomc.repositories.PagamentoRepository;
import com.otaviobraga.cursomc.repositories.PedidoRepository;
import com.otaviobraga.cursomc.repositories.ProdutoRepository;
import com.otaviobraga.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProdutoService produtoService;

	public Optional<Pedido> find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);

		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName(),
					obj);
		}

		return obj;
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, (Data) obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getid()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}
}
