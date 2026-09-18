import { createNativeStackNavigator } from '@react-navigation/native-stack';

import {
  HomeUsuario,
  FazerPedido,
  SolicitarEntregador,
  Pedido
} from '../../pages';

export type UsuarioStackParamList = {
  HomeUsuario: undefined;

  FazerPedido: undefined;

  SolicitarEntregador: {
    pedidoId: number;
  };

  Pedido: {
    pedidoId: number;
  };
};

const Stack = createNativeStackNavigator<UsuarioStackParamList>();

export default function UsuarioStack() {
  return (
    <Stack.Navigator>
      <Stack.Screen
        name="HomeUsuario"
        component={HomeUsuario}
      />

      <Stack.Screen
        name="FazerPedido"
        component={FazerPedido}
      />

      <Stack.Screen
        name="SolicitarEntregador"
        component={SolicitarEntregador}
      />

      <Stack.Screen
      name="Pedido"
      component={Pedido}
    />
    </Stack.Navigator>
  );
}