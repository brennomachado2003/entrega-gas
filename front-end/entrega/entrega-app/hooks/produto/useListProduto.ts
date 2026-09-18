import { useEffect, useState } from "react";
import { listarProdutos } from "../../service/produto/produtoService";
import { ProdutoPedidoDTO } from "../../components/listProdutos/types";

export function useListProdutos() {
  const [produtos, setProdutos] = useState<ProdutoPedidoDTO[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    carregarProdutos();
  }, []);

  async function carregarProdutos() {
    try {
      setLoading(true);
      setError("");

      const response = await listarProdutos();

      if (!response || response.length === 0) {
        setProdutos([]);
        setError("Nenhum produto cadastrado.");
        return;
      }

      setProdutos(response);
    } catch (err) {
      console.error("Erro ao carregar produtos:", err);
      setError("Não foi possível carregar os produtos.");
    } finally {
      setLoading(false);
    }
  }

  return {
    produtos,
    loading,
    error,
    semProdutos: produtos.length === 0,
    carregarProdutos,
  };
}