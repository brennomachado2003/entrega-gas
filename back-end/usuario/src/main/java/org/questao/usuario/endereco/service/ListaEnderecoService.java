package org.questao.usuario.endereco.service;

import org.questao.usuario.endereco.dominio.Endereco;
import org.questao.usuario.endereco.infraestrutura.EnderecoRepository;
import org.questao.usuario.usuario.dominio.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaEnderecoService {

    private final EnderecoRepository enderecoRepository;

    public ListaEnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> listEndereco(Usuario usuario) {
        return enderecoRepository.listaEndereco(usuario);
    }
}
