package org.questao.empresa.empresa.infraestrutura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaJPARepository extends JpaRepository<EmpresaEntity, Long> {
    /*
    save(empresa)
    findById(id)
    findAll()
    deleteById(id)
    delete(empresa)
    existsById(id)
    count()
    findAllById(ids)
    */
}
