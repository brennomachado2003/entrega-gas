export const apiService = {
  buscarEnderecoPorCEP: async (cep) => {
    try {
      const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
      const data = await response.json();
      if (data.erro) {
        return {
          erro: true,
          mensagem: "CEP não encontrado.",
        };
      }
      return {
        erro: false,
        logradouro: data.logradouro,
        bairro: data.bairro,
        localidade: data.localidade,
        uf: data.uf,
        enderecoCompleto: `${data.logradouro}, ${data.bairro} - ${data.localidade}/${data.uf}`,
      };
    } catch (error) {
      return {
        erro: true,
        mensagem: "Erro ao conectar com o serviço de CEP.",
      };
    }
  },
};