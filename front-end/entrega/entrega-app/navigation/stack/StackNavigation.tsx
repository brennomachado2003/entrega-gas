import { ActivityIndicator } from 'react-native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import {
  CadastroEntregador,
  CadastroUsuario,
  Login,
} from '../../pages';

import UsuarioStack from './UsuarioStack';
import EntregadorStack from './EntregadorStack';

import { useAuthContext } from '../../hooks/auth/useAuthContext';

export type RootStackParamList = {
  Login: undefined;
  CadastroEntregador: undefined;
  CadastroUsuario: undefined;
  UsuarioStack: undefined;
  EntregadorStack: undefined;
};

const Stack = createNativeStackNavigator<RootStackParamList>();

export default function StackNavigation() {
  const { user, loadingContext } = useAuthContext();

  if (loadingContext) {
    return <ActivityIndicator />;
  }

  return (
    <Stack.Navigator screenOptions={{ headerShown: true }}>

      {!user ? (
        <>
          <Stack.Screen
            name="Login"
            component={Login}
          />

          <Stack.Screen
            name="CadastroUsuario"
            component={CadastroUsuario}
          />

          <Stack.Screen
            name="CadastroEntregador"
            component={CadastroEntregador}
          />
        </>
      ) : user.tipo?.toUpperCase() === 'ENTREGADOR' ? (

        <Stack.Screen
          name="EntregadorStack"
          component={EntregadorStack}
          options={{ headerShown: false }}
        />

      ) : (

        <Stack.Screen
          name="UsuarioStack"
          component={UsuarioStack}
          options={{ headerShown: false }}
        />

      )}

    </Stack.Navigator>
  );
}