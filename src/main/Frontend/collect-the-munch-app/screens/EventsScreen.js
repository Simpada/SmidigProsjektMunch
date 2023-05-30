import { Text, View, StyleSheet } from 'react-native'

const EventsScreen = () => {

    return (
      <View style={styles.container}>
        <Text style={styles.text}>Events Screen</Text>
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
export default EventsScreen