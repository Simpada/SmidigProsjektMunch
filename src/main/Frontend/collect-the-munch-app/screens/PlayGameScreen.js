import { Text, View, StyleSheet } from 'react-native'

const PlayGameScreen = () => {

    return (
      <View style={styles.container}>
        <Text style={styles.text}>Game Screen</Text>
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