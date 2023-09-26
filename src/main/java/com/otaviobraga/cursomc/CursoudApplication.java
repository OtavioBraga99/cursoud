package com.otaviobraga.cursomc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.otaviobraga.cursomc.domain.Categoria;
import com.otaviobraga.cursomc.domain.Cidade;
import com.otaviobraga.cursomc.domain.Cliente;
import com.otaviobraga.cursomc.domain.Endereco;
import com.otaviobraga.cursomc.domain.Estado;
import com.otaviobraga.cursomc.domain.Produto;
import com.otaviobraga.cursomc.domain.enums.TipoCliente;
import com.otaviobraga.cursomc.repositories.CategoriaRepository;
import com.otaviobraga.cursomc.repositories.CidadeRepository;
import com.otaviobraga.cursomc.repositories.ClienteRepository;
import com.otaviobraga.cursomc.repositories.EnderecoRepository;
import com.otaviobraga.cursomc.repositories.EstadoRepository;
import com.otaviobraga.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoudApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired 
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computaros", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(List.of(p1, p2, p3));
		cat2.getProdutos().addAll(List.of(p2));

		p1.getCategorias().addAll(List.of(cat1));
		p2.getCategorias().addAll(List.of(cat1, cat2));
		p3.getCategorias().addAll(List.of(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(
			/* id */ null,
				/* nome */"Maria Silva",
					/* email */"maria@gmail.com",
							/* CPFouCNPJ */"36378912377",
								/* tipo */TipoCliente.PESSOA_FISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838345"));

		Endereco e1 = new Endereco(
			/* id */ null,
				/* nome */ "Rua das Flores",
					/* número */ "300",
						/* complemento */ "apto 203",
							/* bairro */ "Jardim",
								/* cep */ "38220834",
									/* cliente */ cli1,
										/* cidade */ c1);
		
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
}
