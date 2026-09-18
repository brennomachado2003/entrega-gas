export interface LoginRequestDTO {
    login: string;
    senha: string;
}

export interface LoginResponseDTO {
    id: number;
    nome: string;
    contato: string;
    tipo: string;
    ativo: boolean;
}