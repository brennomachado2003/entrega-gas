package org.questao.usuario.endereco.service;

import org.questao.usuario.Erros.endereco.ErroAoListarEnderecoPeloUsuario;
import org.questao.usuario.endereco.dominio.Endereco;
import org.questao.usuario.endereco.infraestrutura.EnderecoRepository;
import org.questao.usuario.usuario.dominio.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaEnderecoService {

    private static final Logger log = LoggerFactory.getLogger(ListaEnderecoService.class);
    private final EnderecoRepository enderecoRepository;

    public ListaEnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> listEndereco(Usuario usuario) {
        try {
            return enderecoRepository.listaEndereco(usuario);
        }
        catch (Exception e) {
            log.error("Erro ao listar endereco por usuario", e);
            throw new ErroAoListarEnderecoPeloUsuario(e);
        }
    }
}
