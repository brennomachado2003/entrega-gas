import { patch, post } from "../http";
import { EntregadorCadastroDTO, EntregadorResponseDTO } from "../../pages/entregador/cadastro/entregador/types";
import {LoginRequestDTO, LoginResponseDTO} from "../../pages/login/types";

export function cadastrarEntregador(entregador: EntregadorCadastroDTO): Promise<EntregadorResponseDTO> {
    return post("/entregadores/cadastro", entregador);
}

export function alternarStatusEntregador(idEntregador: number): Promise<EntregadorResponseDTO> {
  return patch(`/entregadores/${idEntregador}/status`);
}

export function loginEntregador(request: LoginRequestDTO): Promise<LoginResponseDTO> {
  return post("/entregadores/login/entregador", request);
}