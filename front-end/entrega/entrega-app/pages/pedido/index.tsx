import React, { useState } from "react";
import {
  Alert,
  ScrollView,
  View,
  Text,
  Pressable,
} from "react-native";

import { ListProdutos, Endereco } from "../../components";
import { EnderecoResponseDTO } from "../../components/endereco/types";
import { useCarrinho } from "../../hooks/carrinho/useCarrinho";
import { useBuscarEndereco } from "../../hooks/endereco/useBuscarEndereco";
import { useCadastrarPedido } from "../../hooks/pedido/useCadastrarPedido";
import { useNavigation } from "@react-navigation/native";
import { styles } from "./style";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { RootStackParamList } from "../../navigation/stack/StackNavigation";

export default function Pedido() {
  const navigation = useNavigation<NativeStackNavigationProp<RootStackParamList, "Pedido">>();
  const [enderecoSelecionado, setEnderecoSelecionado] =
    useState<EnderecoResponseDTO | null>(null);

  const { enderecos, buscar } = useBuscarEndereco();

  const {
    cadastrar: cadastrar,
    loading: loadingPedido,
  } = useCadastrarPedido();

  const {
    carrinho,
    adicionarProduto,
    removerProduto,
    total,
    limparCarrinho,
  } = useCarrinho();

  async function confirmarPedido() {
    if (carrinho.length === 0) {
      Alert.alert("Atenção", "Adicione produtos ao carrinho.");
      return;
    }

    if (!enderecoSelecionado) {
      Alert.alert("Atenção", "Selecione um endereço de entrega.");
      return;
    }

    const pedido = await cadastrar({
      empresaId: 1,
      enderecoId: enderecoSelecionado.idEndereco,
      valorCompra: total,
      produtos: carrinho.map((item) => ({
        produtoId: item.produto.idProduto,
        quantidade: item.quantidade,
        desconto: 0,
      })),
    });

    if (!pedido) {
      return null;
    }

    limparCarrinho();
    setEnderecoSelecionado(null);

    navigation.navigate("SolicitarEntregador", {
      pedidoId: pedido.pedidoId,
    });
  }

  return (
    <ScrollView
      style={styles.screen}
      contentContainerStyle={styles.container}
      showsVerticalScrollIndicator={false}
    >
      {/* HEADER */}
      <Text style={styles.title}>Fazer Pedido</Text>

      <Text style={styles.subtitle}>
        Escolha os produtos e finalize seu pedido.
      </Text>

      {/* LISTA DE PRODUTOS (controle aqui dentro) */}
      <ListProdutos onAdd={adicionarProduto} />

      {/* ENDEREÇO */}
      <Endereco
        enderecos={enderecos}
        enderecoSelecionado={enderecoSelecionado}
        onSelecionarEndereco={setEnderecoSelecionado}
        onEnderecoCadastrado={buscar}
      />

      {/* CARRINHO */}
      <View style={styles.section}>
        <Text style={styles.sectionTitle}>
          Seu Carrinho
        </Text>

        {carrinho.length === 0 ? (
          <Text style={styles.emptyText}>
            Nenhum produto selecionado
          </Text>
        ) : (
          carrinho.map((item) => (
            <View
              key={item.produto.idProduto}
              style={styles.cartCard}
            >
              <View>
                <Text style={styles.cartTitle}>
                  {item.produto.nome}
                </Text>

                <Text style={styles.cartPrice}>
                  R$ {item.produto.valor.toFixed(2)}
                </Text>
              </View>

              <View style={styles.cartActions}>
                <Pressable
                  style={styles.qtyButton}
                  onPress={() =>
                    removerProduto(item.produto.idProduto)
                  }
                >
                  <Text style={styles.qtyText}>-</Text>
                </Pressable>

                <Text style={styles.qtyValue}>
                  {item.quantidade}
                </Text>

                <Pressable
                  style={styles.qtyButton}
                  onPress={() =>
                    adicionarProduto(item.produto)
                  }
                >
                  <Text style={styles.qtyText}>+</Text>
                </Pressable>
              </View>
            </View>
          ))
        )}
      </View>

      {/* RESUMO */}
      <View style={styles.resumeCard}>
        <Text style={styles.resumeTitle}>
          Resumo do Pedido
        </Text>

        <View style={styles.resumeRow}>
          <Text style={styles.resumeLabel}>
            Total de itens
          </Text>

          <Text style={styles.resumeValue}>
            {carrinho.reduce(
              (acc, item) => acc + item.quantidade,
              0
            )}
          </Text>
        </View>

        <View style={styles.divider} />

        <View style={styles.resumeRow}>
          <Text style={styles.totalLabel}>
            Total
          </Text>

          <Text style={styles.totalValue}>
            R$ {total.toFixed(2)}
          </Text>
        </View>
      </View>

      {/* AÇÕES */}
      <View style={styles.actions}>
        <Pressable
          style={styles.confirmButton}
          onPress={confirmarPedido}
          disabled={loadingPedido}
        >
          <Text style={styles.confirmButtonText}>
            {loadingPedido ? "CONFIRMANDO..." : "CONFIRMAR PEDIDO"}
          </Text>
        </Pressable>

        <Pressable
          style={styles.clearButton}
          onPress={limparCarrinho}
        >
          <Text style={styles.clearButtonText}>
            LIMPAR
          </Text>
        </Pressable>
      </View>
    </ScrollView>
  );
}