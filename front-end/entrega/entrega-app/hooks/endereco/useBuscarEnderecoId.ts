import { useEffect, useState } from "react";
import { buscarEnderecosPorID } from "../../service/usuario/enderecoService";
import { EnderecoResponseDTO } from "../../components/endereco/types";

export function useBuscarEnderecoId(idEndereco: number) {
  const [endereco, setEndereco] = useState<EnderecoResponseDTO | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    async function buscar() {
      try {
        setLoading(true);
        setError(null);

        const response = await buscarEnderecosPorID(idEndereco);
        setEndereco(response);
      } catch (error) {
        console.error("Erro ao buscar endereço:", error);
        setError("Erro ao buscar endereço.");
      } finally {
        setLoading(false);
      }
    }

    if (idEndereco) {
      buscar();
    }
  }, [idEndereco]);

  return {
    endereco,
    loading,
    error,
  };
}