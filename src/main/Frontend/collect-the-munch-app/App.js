import { StyleSheet, View } from 'react-native';

import Navigation from './components/Navigation';
import SignupScreen from './screens/SignUpScreen';
import LoginScreen from './screens/LoginScreenJWT';



export default function App() {


  return (
      <SignupScreen/>
      );
}

const styles = StyleSheet.create({
  container: {
    flex: 0,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
