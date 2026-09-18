package org.questao.entregador.entregador.infraestrutura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntregadorJPARepository extends JpaRepository<EntregadorEntity, Long> {
    /*
    save(entregador)
    findById(id)
    findAll()
    deleteById(id)
    delete(entregador)
    existsById(id)
    count()
    findAllById(ids)
    */
    EntregadorEntity findByCpf(String cpf);
}
