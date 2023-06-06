import React, { useEffect } from 'react';
import { View, Text, StyleSheet, TextInput, TouchableOpacity } from 'react-native';
import * as Font from 'expo-font';

const CreateTeam = () => {
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
      <View style={styles.munchHeaderContainer}>
        <View style={styles.munchHeaderTextContainer}>
          <Text style={styles.headlineText}>MUNCH</Text>
        </View>
      </View>
      <View style={styles.contentContainer}>
        <View style={styles.searchBarContainer}>
          <TextInput style={styles.searchBar} placeholder="Search" />
          <TouchableOpacity style={styles.inviteButton}>
            <Text style={styles.inviteButtonText}>Invite</Text>
          </TouchableOpacity>
        </View>
        <View style={styles.whiteBox} />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#0f2335',
    alignItems: 'center',
    justifyContent: 'center',
  },
  munchHeaderContainer: {
    alignItems: 'center',
  },
  munchHeaderTextContainer: {
    backgroundColor: 'red',
    paddingHorizontal: 100,
    paddingVertical: 5,
    borderRadius: 5,
    alignSelf: 'flex-start',
    width: '100%',
  },
  headlineText: {
    color: 'black',
    fontSize: 100,
    fontFamily: 'GirottMunch-BoldBackslant',
    textAlign: 'center',
  },
  contentContainer: {
    marginTop: '10%',
    paddingHorizontal: 20,
    width: '100%',
    maxWidth: 500,
  },
  searchBarContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 10,
  },
  searchBar: {
    flex: 1,
    height: 50,
    backgroundColor: '#FFFFFF',
    borderRadius: 5,
    paddingHorizontal: 10,
  },
  inviteButton: {
    marginLeft: 10,
    backgroundColor: 'red',
    paddingHorizontal: 15,
    paddingVertical: 10,
    borderRadius: 5,
  },
  inviteButtonText: {
    color: 'white',
    fontSize: 16,
    fontWeight: 'bold',
  },
  whiteBox: {
    backgroundColor: '#FFFFFF',
    height: 200,
    borderRadius: 5,
  },
});

export default CreateTeam;
