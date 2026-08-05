import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {CadastroEntregador, CadastroUsuario, Login, Home, Pedido, SolicitarEntregador} from '../../pages';

export type RootStackParamList = {
  Login: undefined;
  Home: undefined;
  CadastroEntregador: undefined;
  CadastroUsuario: undefined;
  Pedido: undefined;
  SolicitarEntregador: {
    pedidoId: number;
  };
};

const Stack = createNativeStackNavigator<RootStackParamList>();


export default function StackNavigation(){
  return(
    <Stack.Navigator screenOptions={{headerShown: true}}>
      <Stack.Screen name='Login' component={Login} />
      <Stack.Screen name="Home" component={Home}/>
      <Stack.Screen name='CadastroEntregador' component={CadastroEntregador} />
      <Stack.Screen name='CadastroUsuario' component={CadastroUsuario} />
      <Stack.Screen name='Pedido' component={Pedido} />
      <Stack.Screen name='SolicitarEntregador' component={SolicitarEntregador} />
    </Stack.Navigator>
  );
}
