package org.questao.empresa.empresa.infraestrutura;

import org.questao.empresa.empresa.dominio.Empresa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmpresaRepositoryAdapter implements EmpresaRepository {

    private final EmpresaJPARepository empresaJPARepository;

    public EmpresaRepositoryAdapter(EmpresaJPARepository empresaJPARepository) {
        this.empresaJPARepository = empresaJPARepository;
    }

    @Override
    public List<Empresa> listar() {
        List<EmpresaEntity> empresas = empresaJPARepository.findAll();
        return empresas.stream().map(EmpresaMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Empresa buscar(Long id) {
        EmpresaEntity empresa = empresaJPARepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Empresa não encontrado: " + id));
        return EmpresaMapper.toDomain(empresa);
    }

    @Override
    public Empresa salvar(Empresa empresa) {
        EmpresaEntity empresaEntity = EmpresaMapper.toEntity(empresa);
        return EmpresaMapper.toDomain(empresaJPARepository.save(empresaEntity));
    }

    @Override
    public Empresa atualizar(Long id, Empresa dados) {
        Empresa empresa = buscar(id);
        empresa.atualizar(dados);
        return salvar(empresa);
    }

    @Override
    public void excluir(Long id) {
        Empresa entregador = buscar(id);
        empresaJPARepository.delete(EmpresaMapper.toEntity(entregador));
    }
}
