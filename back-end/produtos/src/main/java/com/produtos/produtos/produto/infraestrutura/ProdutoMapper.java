package com.produtos.produtos.produto.infraestrutura;

import com.produtos.produtos.produto.dominio.Produto;
import com.produtos.produtos.produto.infraestrutura.dto.ProdutoRequestDTO;
import com.produtos.produtos.produto.infraestrutura.dto.ProdutoResponseDTO;

public final class ProdutoMapper {

    private ProdutoMapper() {
    }

    public static ProdutoEntity toEntity(Produto produto) {

        return new ProdutoEntity(
                produto.getIdProduto(),
                produto.getNome(),
                produto.getNcm().ncm(),
                produto.getQuantidade(),
                produto.getValor().valor()
        );
    }

    public static Produto toDomain(ProdutoEntity entity) {

        return Produto.reconstituir(
                entity.getIdProduto(),
                entity.getNome(),
                entity.getNcm(),
                entity.getQuantidade(),
                entity.getValor()
        );
    }

    public static ProdutoResponseDTO produtoResponseDTO(Produto produto) {
        return new ProdutoResponseDTO(produto.getIdProduto(), produto.getNome(), produto.getValor().valor());
    }

    public static Produto requestToDominio(ProdutoRequestDTO produtoRequestDTO) {
        return Produto.reconstituir(
                null,
                produtoRequestDTO.getNome(),
                produtoRequestDTO.getNcm(),
                produtoRequestDTO.getQuantidade(),
                produtoRequestDTO.getValor()
        );
    }
}
