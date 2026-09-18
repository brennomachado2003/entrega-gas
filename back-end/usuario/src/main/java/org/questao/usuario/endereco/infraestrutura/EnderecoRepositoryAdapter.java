package org.questao.usuario.endereco.infraestrutura;

import org.questao.usuario.endereco.dominio.Endereco;
import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.infraestrutura.UsuarioEntity;
import org.questao.usuario.usuario.infraestrutura.UsuarioMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnderecoRepositoryAdapter implements EnderecoRepository {

    private final EnderecoJPARepository enderecoJPARepository;

    public EnderecoRepositoryAdapter(EnderecoJPARepository enderecoJPARepository) {
        this.enderecoJPARepository = enderecoJPARepository;
    }

    @Override
    public List<Endereco> listar() {
        List<EnderecoEntity> enderecos = enderecoJPARepository.findAll();
        return enderecos.stream().map(EnderecoMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Endereco buscar(Long id) {
        EnderecoEntity endereco = enderecoJPARepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Endereco não encontrado: " + id));
        return EnderecoMapper.toDomain(endereco);
    }

    @Override
    public Endereco salvar(Endereco endereco) {
        EnderecoEntity enderecoEntity = EnderecoMapper.toEntity(endereco);
        return EnderecoMapper.toDomain(enderecoJPARepository.save(enderecoEntity));
    }

    @Override
    public Endereco atualizar(Long id, Endereco dados) {
        Endereco endereco = buscar(id);
        endereco.atualizar(dados);
        return salvar(endereco);
    }

    @Override
    public List<Endereco> listaEndereco(Usuario usuario){
        UsuarioEntity usuarioEntity = UsuarioMapper.toEntity(usuario);
        List<EnderecoEntity> enderecos = enderecoJPARepository.findByUsuario(usuarioEntity);
        return enderecos.stream().map(EnderecoMapper::toDomain).collect(Collectors.toList());
    }
}
