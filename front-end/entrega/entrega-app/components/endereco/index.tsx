import React, { useState } from "react";
import { View, Text, Pressable, TextInput } from "react-native";

import { EnderecoResponseDTO, EnderecoCadastroDTO, EnderecoProps } from "./types";
import { styles } from "./style";
import { useCadastrarEndereco } from "../../hooks/endereco/useCadastrarEndereco";
import { useBuscarCep } from "../../hooks/cepApi/useBuscarCep";

const formInicial: EnderecoCadastroDTO = {
  usuarioId: 0,
  rua: "",
  numero: "",
  complemento: "",
  bairro: "",
  cidade: "",
  estado: "",
  cep: "",
};

export default function Endereco({
  enderecos,
  enderecoSelecionado,
  onSelecionarEndereco,
  onEnderecoCadastrado,
}: EnderecoProps) {

  const [mostrarFormulario, setMostrarFormulario] = useState(false);
  const [form, setForm] = useState<EnderecoCadastroDTO>(formInicial);
  const { buscarCep, buscandoCep } = useBuscarCep();
  const { cadastrar, loading, errors } = useCadastrarEndereco();

    function alterarCampo(campo: keyof EnderecoCadastroDTO, valor: string) {
      setForm((prev) => ({
        ...prev,
        [campo]: valor,
      }));
    }
    async function alterarCep(valor: string) {
      alterarCampo("cep", valor);
      const resultado = await buscarCep(valor);
      if (!resultado || resultado.erro) return;
      setForm((prev) => ({
        ...prev,
        cep: valor,
        rua: resultado.logradouro,
        bairro: resultado.bairro,
        cidade: resultado.localidade,
        estado: resultado.uf,
      }));
  }
  async function salvarEndereco() {
    const sucesso = await cadastrar(form);

    if (sucesso) {
      setForm(formInicial);
      setMostrarFormulario(false);
      onEnderecoCadastrado?.();
    }
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Endereço de entrega</Text>

      <Pressable
        style={styles.newAddressCard}
        onPress={() => setMostrarFormulario((prev) => !prev)}
      >
        <Text style={styles.newAddressText}>
          {mostrarFormulario ? "- Fechar cadastro" : "+ Novo endereço"}
        </Text>
      </Pressable>

      {mostrarFormulario && (
        <View style={styles.form}>
          <TextInput
            style={[styles.input, errors.cep && styles.inputError]}
            placeholder="CEP"
            value={form.cep}
            keyboardType="numeric"
            returnKeyType="next"
            onChangeText={(valor) => alterarCampo("cep", valor)}
            onBlur={() => alterarCep(form.cep)}
            onSubmitEditing={() => alterarCep(form.cep)}
          />
          
          <TextInput
            style={[styles.input, errors.rua && styles.inputError]}
            placeholder="Rua"
            value={form.rua}
            onChangeText={(valor) => alterarCampo("rua", valor)}
          />

          <TextInput
            style={[styles.input, errors.numero && styles.inputError]}
            placeholder="Número"
            value={form.numero}
            onChangeText={(valor) => alterarCampo("numero", valor)}
          />

          <TextInput
            style={styles.input}
            placeholder="Complemento"
            value={form.complemento}
            onChangeText={(valor) => alterarCampo("complemento", valor)}
          />

          <TextInput
            style={[styles.input, errors.bairro && styles.inputError]}
            placeholder="Bairro"
            value={form.bairro}
            onChangeText={(valor) => alterarCampo("bairro", valor)}
          />

          <TextInput
            style={[styles.input, errors.cidade && styles.inputError]}
            placeholder="Cidade"
            value={form.cidade}
            onChangeText={(valor) => alterarCampo("cidade", valor)}
          />

          <TextInput
            style={[styles.input, errors.estado && styles.inputError]}
            placeholder="Estado"
            value={form.estado}
            onChangeText={(valor) => alterarCampo("estado", valor)}
          />

          <Pressable
            style={styles.saveButton}
            onPress={salvarEndereco}
            disabled={loading}
          >
            <Text style={styles.saveButtonText}>
              {loading ? "SALVANDO..." : "SALVAR ENDEREÇO"}
            </Text>
          </Pressable>
        </View>
      )}

      {enderecos.length === 0 ? (
        <Text style={styles.emptyText}>
          Nenhum endereço cadastrado
        </Text>
      ) : (
        enderecos.map((endereco) => {
          const selecionado =
            enderecoSelecionado?.idEndereco === endereco.idEndereco;

          return (
            <Pressable
              key={endereco.idEndereco}
              style={[
                styles.addressCard,
                selecionado && styles.addressCardSelected,
              ]}
              onPress={() => onSelecionarEndereco(endereco)}
            >
              <View style={styles.addressRow}>
                <View style={styles.addressIdBox}>
                  <Text style={styles.addressIdText}>
                    {endereco.idEndereco}
                  </Text>
                </View>

                <View style={styles.addressInfo}>
                  <Text style={styles.addressTitle}>
                    {endereco.rua}, {endereco.numero}
                  </Text>

                  <Text style={styles.addressText}>
                    {endereco.bairro}
                  </Text>

                  <Text style={styles.addressText}>
                    {endereco.cidade} - {endereco.estado}
                  </Text>
                </View>
              </View>
            </Pressable>
          );
        })
      )}
    </View>
  );
}