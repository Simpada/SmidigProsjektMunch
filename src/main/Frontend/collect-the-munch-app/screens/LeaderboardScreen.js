import { Text, View, StyleSheet } from 'react-native'

const LeaderboardScreen = () => {

    return (
      <View style={styles.container}>
        <Text style={styles.text}>Leaderboard Screen</Text>
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
export default LeaderboardScreen