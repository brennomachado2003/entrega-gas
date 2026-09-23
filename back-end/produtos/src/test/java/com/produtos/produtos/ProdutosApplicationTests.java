package com.produtos.produtos;

import com.produtos.produtos.produto.dominio.Produto;
import com.produtos.produtos.produto.infraestrutura.ProdutoRepository;
import com.produtos.produtos.produto.service.AtualizarProdutoService;
import com.produtos.produtos.produto.service.BuscarProdutoService;
import com.produtos.produtos.produto.service.CadastrarProdutoService;
import com.produtos.produtos.produto.service.ListaProdutosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

	@Mock
	private ProdutoRepository produtoRepository;

	@InjectMocks
	private AtualizarProdutoService atualizarProdutoService;

	@InjectMocks
	private BuscarProdutoService buscarProdutoService;

	@InjectMocks
	private CadastrarProdutoService cadastrarProdutoService;

	@InjectMocks
	private ListaProdutosService listaProdutosService;


	@Test
	void deveAtualizarProduto() {

		Long id = 1L;

		Produto produtoExistente = mock(Produto.class);
		Produto dados = mock(Produto.class);

		when(produtoRepository.buscar(id))
				.thenReturn(produtoExistente);

		when(produtoRepository.salvar(produtoExistente))
				.thenReturn(produtoExistente);

		Produto resultado =
				atualizarProdutoService.atualizar(id, dados);

		assertNotNull(resultado);
		assertEquals(produtoExistente, resultado);

		verify(produtoRepository)
				.buscar(id);

		verify(produtoExistente)
				.atualizar(dados);

		verify(produtoRepository)
				.salvar(produtoExistente);
	}

	@Test
	void deveBuscarProdutoPorId() {

		Long id = 1L;

		Produto produto = mock(Produto.class);

		when(produtoRepository.buscar(id))
				.thenReturn(produto);

		Produto resultado =
				buscarProdutoService.buscarProdutoPorId(id);

		assertNotNull(resultado);
		assertEquals(produto, resultado);

		verify(produtoRepository)
				.buscar(id);
	}

	@Test
	void deveCadastrarProduto() {

		Produto produto = mock(Produto.class);

		when(produtoRepository.salvar(produto))
				.thenReturn(produto);

		Produto resultado =
				cadastrarProdutoService.cadastroProduto(produto);

		assertNotNull(resultado);
		assertEquals(produto, resultado);

		verify(produtoRepository)
				.salvar(produto);
	}


	@Test
	void deveListarProdutos() {

		Produto produto1 = mock(Produto.class);
		Produto produto2 = mock(Produto.class);

		List<Produto> produtos =
				List.of(produto1, produto2);

		when(produtoRepository.listar())
				.thenReturn(produtos);

		List<Produto> resultado =
				listaProdutosService.listaProdutos();

		assertNotNull(resultado);
		assertEquals(2, resultado.size());
		assertEquals(produtos, resultado);

		verify(produtoRepository)
				.listar();
	}
}