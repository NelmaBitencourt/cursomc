package com.bitencourt.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bitencourt.cursomc.domain.Categoria;
import com.bitencourt.cursomc.domain.Cidade;
import com.bitencourt.cursomc.domain.Cliente;
import com.bitencourt.cursomc.domain.Endereco;
import com.bitencourt.cursomc.domain.Estado;
import com.bitencourt.cursomc.domain.Produto;
import com.bitencourt.cursomc.domain.enums.TipoCliente;
import com.bitencourt.cursomc.repositories.CategoriaRepository;
import com.bitencourt.cursomc.repositories.CidadeRepository;
import com.bitencourt.cursomc.repositories.ClienteRepository;
import com.bitencourt.cursomc.repositories.EnderecoRepository;
import com.bitencourt.cursomc.repositories.EstadoRepository;
import com.bitencourt.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");

		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Imprensora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);

		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));

		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));

		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "23443223456", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("75987665789","78900899876"));
		
		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "12345567", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "centro", "38098989", cliente1, cidade2);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		
		clienteRepository.save(cliente1);
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		
		
		
		
		
		
		
		

	}

}
