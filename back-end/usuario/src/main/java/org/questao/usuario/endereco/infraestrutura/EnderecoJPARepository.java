package org.questao.usuario.endereco.infraestrutura;

import org.questao.usuario.usuario.infraestrutura.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoJPARepository extends JpaRepository<EnderecoEntity, Long> {
    /*
    save(endereco)
    findById(id)
    findAll()
    deleteById(id)
    delete(endereco)
    existsById(id)
    count()
    findAllById(ids)
    */
    List<EnderecoEntity> findByUsuarioIdUsuario(Long idUsuario);
    List<EnderecoEntity> findByUsuario(UsuarioEntity usuario);
}
