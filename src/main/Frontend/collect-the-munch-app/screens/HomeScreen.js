
import { useState, useEffect } from 'react';
import { Text, View, StyleSheet, Touchable, TouchableOpacity, Image } from 'react-native'
import Review from '../components/Review';
import * as Font from 'expo-font';
import { fonts } from 'react-native-elements/dist/config';
import { colors } from '../Styles/theme';
import HeaderImg from '../assets/Images/munch-museet.avif'
const HomeScreen = () => {
  useEffect(() => {
    loadFonts();
  }, []);

  const loadFonts = async () => {
    await Font.loadAsync({
      'GirottMunch-BoldBackslant': require('../assets/fonts/GirottMunch-BoldBackslant.ttf'),
    });
  };
    return (
      <View style={styles.container}>
        <View>
          <View>
            <Image style={styles.image} source={HeaderImg} resizeMode="cover"/>
          </View>
            <Text>MUNCH</Text>
        </View>
      </View>
    )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.navy,
    alignItems: 'center',
    justifyContent: "center"
  },
  text: {
    color: colors.red,
    fontSize: 100,
    fontFamily: "GirottMunch-BoldBackslant"
  },
  munchtext: {
    fontSize: 100,
    textAlign:"center",
    color: "black",
  },
  image: {
    width: 100, 
    height: 100
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