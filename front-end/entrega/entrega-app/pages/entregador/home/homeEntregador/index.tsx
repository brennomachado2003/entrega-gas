import React from "react";
import {
  ScrollView,
  View,
  Text,
  Pressable,
  Platform,
  Alert,
} from "react-native";

import { salvarUsuario } from "../../../../service/authStorage";

import { styles } from "./style";

import {
  ListPedidosEntrega,
  Saudacao
} from "../../../../components";

import { useTrocaAtivoEntregador } from "../../../../hooks/entregador/useTrocarAtivoEntregador";
import { useAuthContext } from "../../../../hooks/auth/useAuthContext";


export default function HomeEntregador() {

  const { user, setUser } = useAuthContext();
  const { trocarStatus, carregando } = useTrocaAtivoEntregador();

  async function handleTrocarStatus() {
    try {
      const entregadorAtualizado = await trocarStatus();
      setUser({...user!, ativo: entregadorAtualizado.ativo});

      const usuarioAtualizado = {...user!, ativo: entregadorAtualizado.ativo};
      setUser(usuarioAtualizado);
      await salvarUsuario(usuarioAtualizado);
    } catch (error) {
      Alert.alert("Erro", "Não foi possível alterar seu status.");
    }

  }

  return (
    <>
      <ScrollView
        style={styles.screen}
        contentContainerStyle={styles.container}
        showsVerticalScrollIndicator={false}
      >

        {Platform.OS !== "web" && (
          <Saudacao />
        )}


        <View
          style={[
            styles.heroCard,
            user?.ativo && styles.heroCardOnline,
          ]}
        >

          <Text style={styles.heroIcon}>
            {user?.ativo
              ? "🚗"
              : "💤"}
          </Text>


          <Text style={styles.heroTitle}>
            {user?.ativo
              ? "Você está Online"
              : "Você está Offline"}
          </Text>


          <Text style={styles.heroDescription}>
            {user?.ativo
              ? "Aguarde novas solicitações de gás na sua região. Mantenha o app aberto."
              : "Fique online para começar a receber pedidos e realizar entregas hoje."}
          </Text>


          <Pressable
            style={[
              styles.heroButton,
              user?.ativo && styles.heroButtonOffline,
            ]}
            onPress={handleTrocarStatus}
            disabled={carregando}
          >

            <Text
              style={[
                styles.heroButtonText,
                user?.ativo && styles.heroButtonTextOffline,
              ]}
            >

              {carregando
                ? "ALTERANDO..."
                : user?.ativo
                ? "FICAR OFFLINE"
                : "FICAR ONLINE AGORA"}

            </Text>

          </Pressable>

        </View>


        <Text style={styles.sectionTitle}>
          Seu Desempenho (Hoje)
        </Text>


        <View style={styles.features}>

          <View style={styles.featureCard}>

            <Text style={styles.featureIcon}>
              💰
            </Text>

            <Text style={styles.featureText}>
              R$ 0,00
            </Text>

            <Text style={styles.featureSubText}>
              Ganhos
            </Text>

          </View>


          <View style={styles.featureCard}>

            <Text style={styles.featureIcon}>
              📦
            </Text>

            <Text style={styles.featureText}>
              0
            </Text>

            <Text style={styles.featureSubText}>
              Entregas
            </Text>

          </View>


          <View style={styles.featureCard}>

            <Text style={styles.featureIcon}>
              ⭐
            </Text>

            <Text style={styles.featureText}>
              5.0
            </Text>

            <Text style={styles.featureSubText}>
              Avaliação
            </Text>

          </View>

        </View>


        <Text style={styles.sectionTitle}>
          Próximas Entregas / Histórico
        </Text>


        <ListPedidosEntrega />

      </ScrollView>
    </>
  );
}