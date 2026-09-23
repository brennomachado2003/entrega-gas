package org.questao.empresa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.questao.empresa.Erros.ErroAoBuscarEmpresaService;
import org.questao.empresa.Erros.ErroAoCadastrarEmpresaService;
import org.questao.empresa.Erros.ErroAoListaEmpresaService;
import org.questao.empresa.empresa.dominio.Empresa;
import org.questao.empresa.empresa.dto.EmpresaCadastroDTO;
import org.questao.empresa.empresa.implement.BuscarLocalizacaoClient;
import org.questao.empresa.empresa.infraestrutura.EmpresaRepository;
import org.questao.empresa.empresa.service.BuscarEmpresaService;
import org.questao.empresa.empresa.service.CadastrarEmpresaService;
import org.questao.empresa.empresa.service.ListaEmpresaService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @Mock
    private BuscarLocalizacaoClient buscarLocalizacaoClient;

    @Mock
    private GeometryFactory geometryFactory;

    @Mock
    private Point point;


    @InjectMocks
    private ListaEmpresaService listaEmpresaService;

    @InjectMocks
    private CadastrarEmpresaService cadastrarEmpresaService;

    @InjectMocks
    private BuscarEmpresaService buscarEmpresaService;


    @Test
    void deveListarEmpresas() {

        List<Empresa> empresas = List.of(
                mock(Empresa.class),
                mock(Empresa.class)
        );

        when(empresaRepository.listar())
                .thenReturn(empresas);

        List<Empresa> resultado =
                listaEmpresaService.listaEmpresas();

        assertNotNull(resultado);
        assertEquals(empresas, resultado);

        verify(empresaRepository).listar();
    }


    @Test
    void deveLancarErroAoListarEmpresas() {

        when(empresaRepository.listar())
                .thenThrow(new RuntimeException());

        assertThrows(
                ErroAoListaEmpresaService.class,
                () -> listaEmpresaService.listaEmpresas()
        );

        verify(empresaRepository).listar();
    }


    @Test
    void deveBuscarEmpresa() {

        Long id = 1L;

        Empresa empresa = mock(Empresa.class);

        when(empresaRepository.buscar(id))
                .thenReturn(empresa);

        Empresa resultado =
                buscarEmpresaService.buscar(id);

        assertNotNull(resultado);
        assertEquals(empresa, resultado);

        verify(empresaRepository).buscar(id);
    }


    @Test
    void deveLancarErroAoBuscarEmpresa() {

        Long id = 1L;

        when(empresaRepository.buscar(id))
                .thenThrow(new RuntimeException());

        assertThrows(
                ErroAoBuscarEmpresaService.class,
                () -> buscarEmpresaService.buscar(id)
        );

        verify(empresaRepository).buscar(id);
    }


    @Test
    void deveLancarErroAoCadastrarEmpresa() {

        EmpresaCadastroDTO dto = mock(EmpresaCadastroDTO.class);

        when(buscarLocalizacaoClient.buscarLocalizacao(dto))
                .thenThrow(new RuntimeException());

        assertThrows(
                ErroAoCadastrarEmpresaService.class,
                () -> cadastrarEmpresaService.cadastrar(dto)
        );

        verify(buscarLocalizacaoClient)
                .buscarLocalizacao(dto);
    }
}
