import { Text, View, StyleSheet } from 'react-native'

const HomeScreen = () => {


    return (
      <View style={styles.container}>
        <View style={styles.munchContainer}>
          <Text style={styles.munchtext}>MUNCH</Text>
        </View>
        <View>
          <Text style={styles.text}>Home Screen</Text>
        </View>
      </View>
    )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'space-evenly',
  },
  text: {
    fontSize: 30
  },
  munchtext: {
    fontSize: 100,
    textAlign:"center",
    color: "black",
  },
  munchContainer: {
    backgroundColor: "#FE390F",
    width: "100%",
  }
});
export default HomeScreen