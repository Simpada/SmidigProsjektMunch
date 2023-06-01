import React, { useEffect } from 'react';
import { Text, View, StyleSheet, Image } from 'react-native';
import * as Font from 'expo-font';
import { colors } from '../Styles/theme';
import HeaderImg from '../assets/Images/munch-museet.avif';

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
      <View style={styles.imageContainer}>
        <Image style={styles.image} source={HeaderImg} resizeMode="cover" />
      </View>
      <Text style={styles.text}>MUNCH</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.navy,
    alignItems: 'center',
  },
  imageContainer: {
    width: "100%",
    height: 400,
  },
  image: {
    flex: 1,
    width: undefined,
    height: undefined,
  },
  text: {
    position: 'absolute',
    top: 20,
    color: colors.red,
    fontSize: 100,
    fontFamily: 'GirottMunch-BoldBackslant',
    zIndex: 1,
  },
});

export default HomeScreen;