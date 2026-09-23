package com.produtos.produtos.produto.config;

import com.produtos.produtos.produto.dominio.Produto;
import com.produtos.produtos.produto.infraestrutura.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class DataInitializer implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    public DataInitializer(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) {

        Produto produto = new Produto(
                null,
                "Botijão de Gás",
                "27111910",
                10,
                new BigDecimal("100.00")
        );
        produtoRepository.salvar(produto);
    }
}
