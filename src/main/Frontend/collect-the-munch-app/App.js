import { StyleSheet, View } from 'react-native';

import Navigation from './components/Navigation';



export default function App() {


  return (
      <Navigation />
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
