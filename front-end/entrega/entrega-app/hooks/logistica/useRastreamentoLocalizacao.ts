import { useEffect, useRef, useState } from "react";
import * as Location from "expo-location";

import { atualizarLocalizacao } from "../../service/logistica/rastrear/apiRastreamento";
import { obterUsuario } from "../../service/authStorage";
import { AtualizarLocalizacaoDTO } from "../../service/logistica/rastrear/types/type";


export function useRastreamentoLocalizacao(idEntregador: number, ativo: boolean) {

  const [carregando, setCarregando] = useState(false);
  const [erro, setErro] = useState<string | null>(null);

  const ultimaLocalizacao = useRef<{
    latitude: number;
    longitude: number;
  } | null>(null);

  async function atualizarPosicao(location: Location.LocationObject) {

    const novaLocalizacao: AtualizarLocalizacaoDTO = {
      latitude: location.coords.latitude,
      longitude: location.coords.longitude,
    };
    const anterior = ultimaLocalizacao.current;
    if (anterior && anterior.latitude === novaLocalizacao.latitude && anterior.longitude === novaLocalizacao.longitude) return;

    try {
      setCarregando(true);
      setErro(null);
      await atualizarLocalizacao(idEntregador, novaLocalizacao);
      ultimaLocalizacao.current = novaLocalizacao;
    } catch (error) {
      setErro(error instanceof Error ? error.message : "Erro ao atualizar localização");
    } finally {
      setCarregando(false);
    }
  }

  useEffect(() => {
    if (!ativo) return;
    let inscricao: Location.LocationSubscription | null = null;
    async function iniciarRastreamento() {
      try {
        const { status } = await Location.requestForegroundPermissionsAsync();
        if (status !== "granted") throw new Error("Permissão de localização negada");
        inscricao = await Location.watchPositionAsync(
          {
            accuracy: Location.Accuracy.High,
            distanceInterval: 1,
          },
          atualizarPosicao
        );
      } catch (error) {
        setErro(error instanceof Error ? error.message : "Erro ao iniciar rastreamento");
      }
    }
    iniciarRastreamento();
    return () => {inscricao?.remove();};
  }, [idEntregador]);

  return {
    carregando,
    erro,
  };
}