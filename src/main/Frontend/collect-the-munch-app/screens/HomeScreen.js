
import { useState } from 'react';
import { Text, View, StyleSheet, Touchable, TouchableOpacity } from 'react-native'
import Review from '../components/Review';
const HomeScreen = () => {

    return (
      <View style={styles.container}>
        <View style={styles.munchContainer}>
          <Text style={styles.munchtext}>MUNCH</Text>
        </View>
        <View>
          <Text style={styles.text}>Home Screen</Text>
        </View>
        <Review />
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
  },
  stars: {
    flexDirection: "row"
  }
});
export default HomeScreen