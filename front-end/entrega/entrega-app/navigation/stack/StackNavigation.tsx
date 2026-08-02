import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {CadastroEntregador, CadastroUsuario, Login, Home, Pedido, SolicitarEntregador} from '../../pages';

const Stack = createNativeStackNavigator();


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
