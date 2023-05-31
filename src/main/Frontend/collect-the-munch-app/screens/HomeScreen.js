
import { useState } from 'react';
import { Text, View, StyleSheet, Touchable, TouchableOpacity } from 'react-native'
import Review from '../components/Review';
import * as Font from 'expo-font';

const HomeScreen = () => {
  useEffect(() => {
    loadFonts();
  }, []);

  const loadFonts = async () => {
    await Font.loadAsync({
      'GirottMunch-BoldBackslant': require('../Fonts/GirottMunch-BoldBackslant.ttf'),
    });
  };
    return (
      <View style={styles.container}>
      <View style={styles.container}>
      <View style={styles.background} />
      <View style={styles.content}>
        <Text style={styles.headline}>'MUNCH</Text>
      </View>
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