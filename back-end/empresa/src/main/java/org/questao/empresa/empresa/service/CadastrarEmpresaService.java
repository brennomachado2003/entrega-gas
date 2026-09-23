package org.questao.empresa.empresa.service;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.questao.empresa.Erros.ErroAoCadastrarEmpresaService;
import org.questao.empresa.empresa.controller.EmpresaController;
import org.questao.empresa.empresa.dominio.Empresa;
import org.questao.empresa.empresa.dto.EmpresaCadastroDTO;
import org.questao.empresa.empresa.implement.BuscarLocalizacaoClient;
import org.questao.empresa.empresa.infraestrutura.EmpresaMapper;
import org.questao.empresa.empresa.infraestrutura.EmpresaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CadastrarEmpresaService {

    private final EmpresaRepository empresaRepository;
    private final BuscarLocalizacaoClient buscarLocalizacaoClient;
    private final GeometryFactory geometryFactory;
    private static final Logger log = LoggerFactory.getLogger(CadastrarEmpresaService.class);


    public  CadastrarEmpresaService(EmpresaRepository empresaRepository,
                                    BuscarLocalizacaoClient buscarLocalizacaoClient,
                                    GeometryFactory geometryFactory) {
        this.empresaRepository = empresaRepository;
        this.buscarLocalizacaoClient = buscarLocalizacaoClient;
        this.geometryFactory = geometryFactory;
    }

    public Empresa cadastrar(EmpresaCadastroDTO dto){
        try {
            dto = buscarLocalizacaoClient.buscarLocalizacao(dto);
            Point localizacao = geometryFactory.createPoint(new Coordinate(dto.longitude(), dto.latitude()));
            Empresa empresa = EmpresaMapper.requestToDominio(dto, localizacao);
            return empresaRepository.salvar(empresa);
        }
        catch (Exception e) {
            log.error("Erro cadastrar empresa: ", e.getMessage());
            throw new ErroAoCadastrarEmpresaService();
        }
    }
}
