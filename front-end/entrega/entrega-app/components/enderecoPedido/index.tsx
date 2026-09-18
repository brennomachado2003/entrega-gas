import { View, Text, ActivityIndicator } from "react-native";
import { useBuscarEnderecoId } from "../../hooks/endereco/useBuscarEnderecoId";
import { styles } from "./style";

type Props = {
  idEndereco: number;
};

export default function EnderecoPedido({ idEndereco }: Props) {
  const { endereco, loading, error } = useBuscarEnderecoId(idEndereco);

  if (loading) {
    return (
      <ActivityIndicator
        size="small"
        style={styles.loading}
      />
    );
  }

  if (error || !endereco) {
    return (
      <Text style={styles.error}>
        Erro ao carregar endereço
      </Text>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.address}>
        {endereco.rua}, {endereco.numero}
      </Text>

      <Text style={styles.address}>
        {endereco.bairro} - {endereco.cidade}
      </Text>
    </View>
  );
}