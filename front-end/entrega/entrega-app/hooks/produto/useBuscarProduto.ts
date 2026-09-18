import { useEffect, useState } from "react";

import { buscarProdutos } from "../../service/produto/produtoService";
import { ProdutoPedidoDTO } from "../../components/listProdutos/types";

export function useBuscarProduto(id: number) {
  const [produto, setProduto] = useState<ProdutoPedidoDTO | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    async function fetchProduto() {
      try {
        setLoading(true);
        setError(null);

        const response = await buscarProdutos(id);

        setProduto(response);
      } catch (err) {
        console.error("Erro ao buscar produto:", err);
        setError("Erro ao buscar o produto.");
      } finally {
        setLoading(false);
      }
    }

    fetchProduto();
  }, [id]);

  return {
    produto,
    loading,
    error,
  };
}