import { ScrollView, View, Text, Pressable } from "react-native";
import { styles } from "./style";
import { ListPedidos, Saudacao } from "../../../../components";
import { useNavigation } from "@react-navigation/native";
import { Platform } from 'react-native';

export default function HomeUsuario() {
  const navigation = useNavigation();
  return (
    <ScrollView
      style={styles.screen}
      contentContainerStyle={styles.container}
      showsVerticalScrollIndicator={false}
    >
      {Platform.OS !== 'web' && <Saudacao />}

      <Pressable style={styles.heroCard}>
        <Text style={styles.heroIcon}>
          🛢️
        </Text>

        <Text style={styles.heroTitle}>
          Pedir Gás
        </Text>

        <Text style={styles.heroDescription}>
          Solicite um entregador próximo e acompanhe sua entrega em tempo real.
        </Text>

        <Pressable
          style={styles.heroButton}
          onPress={() => navigation.navigate("FazerPedido" as never)}
        >
          <Text style={styles.heroButtonText}>
            PEDIR AGORA
          </Text>
        </Pressable>
      </Pressable>

      <Text style={styles.sectionTitle}>
        Recursos
      </Text>

      <View style={styles.features}>
        <View style={styles.featureCard}>
          <Text style={styles.featureIcon}>⚡</Text>
          <Text style={styles.featureText}>Entrega rápida</Text>
        </View>

        <View style={styles.featureCard}>
          <Text style={styles.featureIcon}>📍</Text>
          <Text style={styles.featureText}>Rastreamento</Text>
        </View>

        <View style={styles.featureCard}>
          <Text style={styles.featureIcon}>🛡️</Text>
          <Text style={styles.featureText}>Segurança</Text>
        </View>
      </View>

      <Text style={styles.sectionTitle}>
        Último pedido
      </Text>

      <ListPedidos />
    </ScrollView>
  );
}