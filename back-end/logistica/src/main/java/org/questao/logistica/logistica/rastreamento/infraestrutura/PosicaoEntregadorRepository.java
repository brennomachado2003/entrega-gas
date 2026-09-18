package org.questao.logistica.logistica.rastreamento.infraestrutura;

import org.locationtech.jts.geom.Point;
import org.questao.logistica.logistica.rastreamento.dominio.PosicaoEntregador;

import java.util.List;

public interface PosicaoEntregadorRepository {

     List<PosicaoEntregador> listar();
     PosicaoEntregador buscar(Long id);
     PosicaoEntregador salvar(PosicaoEntregador posicaoEntregador);
     PosicaoEntregador atualizar(Long id, Point localizacao);
     List<PosicaoEntregador> buscarEntregadoresProximos(Point localizacao, double raio);
     PosicaoEntregador buscarEntregadorPorId(Long id);
}
