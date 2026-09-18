import { post } from "../http";

import {UsuarioCadastroDTO, UsuarioResponseDTO} from "../../pages/cadastro/usuario/types";

import {LoginRequestDTO,LoginResponseDTO} from "../../pages/login/types";

export function cadastrarUsuario(usuario: UsuarioCadastroDTO): Promise<UsuarioResponseDTO> {
  return post("/usuario/cadastro", usuario);
}

export function loginUsuario(request: LoginRequestDTO): Promise<LoginResponseDTO> {
  return post("/usuario/login", request);
}

