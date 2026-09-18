import { useState } from "react";
import { View, Text, Pressable } from "react-native";
import { styles } from "./style";
import {AnimatedCadastro, Botao, Input} from "../../components";
import { useLogin } from "../../hooks/login/useLogin";
import { LoginRequestDTO } from "./types";
import { useNavigation } from "@react-navigation/native";

export default function Login() {
  const navigation = useNavigation();
  const { login, loading, error } = useLogin();
  const [form, setForm] = useState<LoginRequestDTO>({login: "", senha: ""});
  
  async function entrar() {
    await login(form);
  }

  return (
    <AnimatedCadastro>
      <View style={styles.card}>
        <Text style={styles.title}>Entrar</Text>
        <Text style={styles.subtitle}>Faça login para continuar</Text>
        <View style={styles.form}>
          <Input
            placeholder="Email ou CPF"
            value={form.login}
            onChangeText={(text) => setForm({...form, login: text})
            }
          />
          <Input
            placeholder="Senha"
            secureTextEntry
            value={form.senha}
            onChangeText={(text) => setForm({...form, senha: text})
            }
          />
          {!!error && (<Text style={styles.errorText}>{error}</Text>)}
          <Botao title="Entrar" loading={loading} onPress={entrar}/>
          <View style={styles.linksContainer}>
            <Pressable onPress={() => navigation.navigate("CadastroUsuario" as never)}>
              {(state: any) => (<Text style={[styles.link, state.hovered && styles.linkHover]}>Criar conta de usuário</Text>)}
            </Pressable>
            <Pressable onPress={() => navigation.navigate("CadastroEntregador" as never)}>
              {(state: any) => (<Text style={[styles.link, state.hovered && styles.linkHover]}>Criar conta de entregador</Text>)}
            </Pressable>
          </View>
        </View>
      </View>
    </AnimatedCadastro>

  );

}