package org.questao.estoque.estoqueMovelTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.questao.estoque.estoque.estoqueMovel.dominio.EstoqueMovel;
import org.questao.estoque.estoque.estoqueMovel.infraestrutura.EstoqueMovelJPARepository;
import org.questao.estoque.estoque.estoqueMovel.infraestrutura.EstoqueMovelRepository;
import org.questao.estoque.estoque.estoqueMovel.service.CadastrarEstoqueMovelService;
import org.questao.estoque.estoque.estoqueMovel.service.EstoqueMovelService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EstoqueMovelServiceTest {

    @Mock
    private EstoqueMovelRepository estoqueMovelRepository;

    @Mock
    private EstoqueMovelJPARepository estoqueMovelJPARepository;

    @InjectMocks
    private CadastrarEstoqueMovelService cadastrarEstoqueMovelService;

    @InjectMocks
    private EstoqueMovelService estoqueMovelService;


    @Test
    void deveCadastrarEstoqueMovel() {

        EstoqueMovel estoqueMovel = org.mockito.Mockito.mock(EstoqueMovel.class);

        when(estoqueMovelRepository.salvar(estoqueMovel))
                .thenReturn(estoqueMovel);

        EstoqueMovel resultado =
                cadastrarEstoqueMovelService.cadastrar(estoqueMovel);

        assertNotNull(resultado);
        assertEquals(estoqueMovel, resultado);

        verify(estoqueMovelRepository).salvar(estoqueMovel);
    }
}
