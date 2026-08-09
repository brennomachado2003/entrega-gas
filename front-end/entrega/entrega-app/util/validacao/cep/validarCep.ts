export function validarCep(cep: string): string | null {
  const cepLimpo = cep.replace(/\D/g, "");

  if (cepLimpo.length !== 8) {
    return null;
  }

  return cepLimpo;
}