import { Text, View, StyleSheet } from 'react-native'
import CameraApp from '../components/CameraApp';

const PlayGameScreen = () => {

    return (
      <View style={styles.container}>
        <Text style={styles.text}>Game Screen</Text>
        <CameraApp />
      </View>
    )
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  text: {
    fontSize: 30
  }
});
export default PlayGameScreen