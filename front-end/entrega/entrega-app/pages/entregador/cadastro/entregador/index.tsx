import { useState } from "react";
import { View, Text, Pressable} from "react-native";
import { styles } from "./style";
import {Botao, Input, AnimatedCadastro} from "../../../../components";
import { EntregadorCadastroDTO } from "./types";
import { useCadastroEntregador } from "../../../../hooks/entregador/useCadastroEntregador";
import { useNavigation } from "@react-navigation/native";

export default function CadastroEntregador() {
  const navigation = useNavigation();
  const { cadastrar, loading, errors } = useCadastroEntregador();
  const [form, setForm] = useState<EntregadorCadastroDTO>({nome: "", cpf: "", senha: "", telefone: ""});

  return (
    <AnimatedCadastro>
      <View style={styles.card}>
        <Text style={styles.title}>Criar Conta Entregador</Text>
        <Text style={styles.subtitle}>
          Preencha os dados abaixo
        </Text>
        
        <View style={styles.form}>
          <Input
            placeholder="Nome completo"
            value={form.nome}
            error={errors.nome}
            onChangeText={(text) =>
              setForm({ ...form, nome: text })
            }
          />
          <Input
            placeholder="CPF"
            type="cpf"
            value={form.cpf}
            error={errors.cpf}
            errorMessage="CPF deve conter 11 dígitos"
            onChangeText={(text) =>
              setForm({ ...form, cpf: text })
            }
          />

          <Input
            placeholder="Telefone"
            type="phone"
            value={form.telefone}
            error={errors.telefone}
            errorMessage="Telefone inválido"
            onChangeText={(text) =>
              setForm({ ...form, telefone: text })
            }
          />

          <Input
            placeholder="Senha"
            secureTextEntry
            value={form.senha}
            error={errors.senha}
            errorMessage="Senha deve conter pelo menos 6 caracteres"
            onChangeText={(text) =>
              setForm({ ...form, senha: text })
            }
          />
          <Botao title="Cadastrar" loading={loading} onPress={() => cadastrar(form)}/>
          <View style={styles.linksContainer}>
            <Pressable onPress={() => navigation.navigate("Login" as never)}>
              {(state: any) => (<Text style={[styles.link, state.hovered && styles.linkHover]}>Login</Text>)}
            </Pressable>
          </View>
      </View>
      </View>
    </AnimatedCadastro>
  );
}