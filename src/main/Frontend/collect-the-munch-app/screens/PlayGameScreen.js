import React, { useEffect, useState } from 'react';
import { Text, View, StyleSheet, Image, ScrollView, TouchableOpacity } from 'react-native';
import * as Font from 'expo-font';
import NavigationComponent from '../components/NavigationComponent';
import popImage from '../assets/Images/pop.jpg';
import leaderboardImage from '../assets/Images/leaderboard.png';

const PlayGameScreen = ({ navigation }) => {
  const [fontsLoaded, setFontsLoaded] = useState(false);

  useEffect(() => {
    loadFonts();
  }, []);

  const loadFonts = async () => {
    await Font.loadAsync({
      'GirottMunch-BoldBackslant': require('../assets/fonts/GirottMunch-BoldBackslant.ttf'),
      'GirottMunch-Bold': require('../assets/fonts/GirottMunch-Bold.ttf'),
      'GirottMunch-BoldSlant': require('../assets/fonts/GirottMunch-BoldSlant.ttf'),
    });

    setFontsLoaded(true);
  };

  const navigateToCreateTeam = () => {
    navigation.navigate('CreateTeam');
  };

  if (!fontsLoaded) {
    return null;
  }

  return (
    <ScrollView style={styles.container}>
      <View style={styles.imageContainer}>
        <Image source={require('../assets/Images/Dalle.png')} style={styles.image} />
      </View>
      <View style={styles.content}>
        <View style={styles.textContainer}>
          <Text style={[styles.headline, { color: 'red' }]}>Play Collect the MUNCH</Text>
          <Text style={styles.subText}>Welcome to Collect the MUNCH. Find the paintings in the museum and scan the QR codes to add exclusive paintings to your collection. Compete with or against your friends for great rewards</Text>
        </View>
        <View style={styles.photoContainer}>
          <View style={styles.overlayContainer}>
            <Image source={require('../assets/Images/QR.jpg')} style={styles.photo} />
            <View style={styles.overlayTextContainer}>
              <Text style={styles.overlayText}>1 - Scan a painting</Text>
            </View>
          </View>
        </View>
        <View style={styles.additionalTextContainer}>
          <Text style={styles.additionalText}>2 - Swipe to collect the card</Text>
          <View style={styles.photoContainer}>
            <View style={styles.overlayContainer}>
              <Image source={popImage} style={styles.photo} />
              <View style={styles.overlayTextContainer}>
                <Text style={styles.overlayText}></Text>
              </View>
            </View>
          </View>
          <View style={styles.additionalTextSpacer} />
          <Text style={styles.additionalText}>3 - Compete with your friends and others!</Text>
          <View style={styles.photoContainer}>
            <View style={styles.overlayContainer}>
              <Image source={leaderboardImage} style={styles.photo} />
              <View style={styles.overlayTextContainer}>
                <Text style={styles.overlayText}></Text>
              </View>
            </View>
          </View>
        </View>

        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.button} onPress={navigateToCreateTeam}>
            <Text style={styles.buttonText}>Create a Party</Text>
          </TouchableOpacity>
          <NavigationComponent navigation={navigation} />
        </View>
      </View>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#0f2335',
  },
  imageContainer: {
    alignItems: 'center',
    justifyContent: 'center',
  },
  image: {
    width: '100%',
    height: 200,
    resizeMode: 'cover',
  },
  content: {
    paddingTop: 50,
    paddingHorizontal: 20,
  },
  textContainer: {
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: 20,
  },
  headline: {
    fontSize: 30,
    fontFamily: 'GirottMunch-BoldBackslant',
    color: 'red',
    marginBottom: 10,
  },
  subText: {
    fontSize: 15,
    color: 'white',
    fontFamily: 'GirottMunch-Bold',
    textAlign: 'center',
    paddingHorizontal: 20,
  },
  photoContainer: {
    width: 220,
    borderWidth: 4,
    borderColor: 'red',
    padding: 5,
    marginTop: 10,
    alignItems: 'center',
    alignSelf: 'center',
  },
  overlayContainer: {
    position: 'relative',
  },
  photo: {
    width: 200,
    height: 400,
    resizeMode: 'cover',
  },
  overlayTextContainer: {
    position: 'absolute',
    top: -20,
    left: 0,
    width: '100%',
    height: '0%',
    alignItems: 'center',
    justifyContent: 'center',
  },
  overlayText: {
    fontSize: 18,
    color: 'red',
    fontFamily: 'GirottMunch-Bold',
    textAlign: 'center',
  },
  additionalTextContainer: {
    marginTop: 10,
    alignItems: 'center',
  },
  additionalText: {
    fontSize: 18,
    color: 'red',
    fontFamily: 'GirottMunch-Bold',
    textAlign: 'center',
    paddingHorizontal: 20,
  },
  additionalTextSpacer: {
    height: 10,
  },
  buttonContainer: {
    alignItems: 'center',
    marginTop: 20,
  },
  button: {
    backgroundColor: 'red',
    width: 150,
    height: 50,
    borderRadius: 25,
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 10,
  },
  buttonText: {
    color: 'white',
    fontSize: 18,
    fontFamily: 'GirottMunch-Bold',
  },
});

export default PlayGameScreen;
