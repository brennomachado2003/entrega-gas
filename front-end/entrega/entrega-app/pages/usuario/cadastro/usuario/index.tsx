import { useState } from "react";
import { View, Text, Pressable, ScrollView, KeyboardAvoidingView, Platform } from "react-native";
import { styles } from "./style";
import { Botao, Input, AnimatedCadastro } from "../../../../components";
import { UsuarioCadastroDTO } from "./types";
import { useCadastroUsuario } from "../../../../hooks/usuario/useCadastroUsuario";
import { useNavigation } from "@react-navigation/native";

export default function CadastroUsuario() {
  const navigation = useNavigation();
  const { cadastrar, loading, errors } = useCadastroUsuario();
  const [form, setForm] = useState<UsuarioCadastroDTO>({ nome: "", cpf: "", dataNascimento: "", telefone: "", email: "", senha: "" });
  return (
    <KeyboardAvoidingView style={{ flex: 1 }} behavior={Platform.OS === "ios" ? "padding" : "height"}>
      <ScrollView
        contentContainerStyle={styles.scroll}
        keyboardShouldPersistTaps="handled"
        showsVerticalScrollIndicator={false}
      >
        <AnimatedCadastro>
          <View style={styles.card}>
            <Text style={styles.title}>Criar Conta Usuario</Text>
            <Text style={styles.subtitle}>Preencha os dados abaixo</Text>
            <View style={styles.form}>
              <Input
                placeholder="Nome completo"
                value={form.nome}
                error={errors.nome}
                onChangeText={(text) => setForm({ ...form, nome: text })}
              />
              <Input
                placeholder="CPF"
                type="cpf"
                value={form.cpf}
                error={errors.cpf}
                errorMessage="CPF deve conter 11 dígitos"
                onChangeText={(text) => setForm({ ...form, cpf: text })}
              />
              <Input
                placeholder="Data de nascimento"
                type="date"
                value={form.dataNascimento}
                error={errors.dataNascimento}
                errorMessage="Data de nascimento inválida"
                onChangeText={(text) => setForm({ ...form, dataNascimento: text })}
              />
              <Input
                placeholder="Telefone"
                type="phone"
                value={form.telefone}
                error={errors.telefone}
                errorMessage="Telefone inválido"
                onChangeText={(text) => setForm({ ...form, telefone: text })}
              />
              <Input
                placeholder="Email"
                type="email"
                value={form.email}
                error={errors.email}
                errorMessage="Email inválido"
                onChangeText={(text) => setForm({ ...form, email: text })}
              />
              <Input
                placeholder="Senha"
                secureTextEntry
                value={form.senha}
                error={errors.senha}
                errorMessage="Senha deve conter pelo menos 6 caracteres"
                onChangeText={(text) => setForm({ ...form, senha: text })}
              />
              <Botao title="Cadastrar" loading={loading} onPress={() => cadastrar(form)} />
              <View style={styles.linksContainer}>
                <Pressable onPress={() => navigation.navigate("Login" as never)}>
                  {(state: any) => (<Text style={[styles.link, state.hovered && styles.linkHover]}>Login</Text>)}
                </Pressable>
              </View>
            </View>
          </View>
        </AnimatedCadastro>
      </ScrollView>
    </KeyboardAvoidingView>
  );
}

