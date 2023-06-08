import React, { useEffect } from 'react';
import { View, Text, StyleSheet, TextInput, TouchableOpacity, Image } from 'react-native';

import * as Font from 'expo-font';

const CreateTeam = () => {
  useEffect(() => {
    loadFonts();
  }, []);

  const loadFonts = async () => {
    await Font.loadAsync({
      'GirottMunch-BoldBackslant': require('../assets/fonts/GirottMunch-BoldBackslant.ttf'),
      'GirottMunch-BoldSlant': require('../assets/fonts/GirottMunch-BoldSlant.ttf'),
      'GirottMunch-Bold': require('../assets/fonts/GirottMunch-Bold.ttf'),
    }); 
  };

  return (
    <View style={styles.container}>
        <View style={styles.redBackground}>
            <Text style={styles.munchHeadline}>
                MUNCH
            </Text>
        </View>
      <View style={styles.contentContainer}>
        <View style={styles.searchBarContainer}>
          <TextInput style={styles.searchBar} placeholder="Search" />
          <TouchableOpacity style={styles.inviteButton}>
            <Text style={styles.inviteButtonText}>Invite</Text>
          </TouchableOpacity>
        </View>
        <Text style={styles.headerText}>Current Team</Text>
        <View style={styles.whiteBox}>
          <View style={styles.profileContainer}>
            <Image
              source={require('../assets/Images/kaws.png')}
              style={styles.profileImage}
            />
            <Text style={styles.username}>noobdecent123</Text>
          </View>
          <View style={styles.separator} />
          <View style={styles.profileContainer}>
            <Image
              source={require('../assets/Images/worm.png')}
              style={styles.profileImage}
            />
            <Text style={styles.username}>shockdoggofan1</Text>
          </View>
          <View style={styles.separator} />
          <View style={styles.profileContainer}>
            <Image
              source={require('../assets/Images/samuel.png')}
              style={styles.profileImage}
            />
            <Text style={styles.username}>samuelleandro126</Text>
          </View>
          <View style={styles.separator} />
          <View style={styles.profileContainer}>
            <Image
              source={require('../assets/Images/addfriend.png')}
              style={styles.addFriendIcon}
            />
          </View>
        </View>
        <TouchableOpacity style={styles.playButton}>
          <Text style={styles.playButtonText}>Play</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
    munchHeadline: {
        fontFamily: 'GirottMunch-BoldBackslant',
        fontSize: 70,
        color: 'black',
        alignSelf: 'center',
    },
    redBackground: {
        backgroundColor: 'red',
        width: '100%',
    },
  container: {
    flex: 1,
    backgroundColor: '#0f2335',
    alignItems: 'center',
    justifyContent: 'center',
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
    fontFamily: 'GirottMunch-Bold',
  },
  headerText: {
    color: 'white',
    fontSize: 24,
    fontFamily: 'GirottMunch-Bold',
    marginTop: 20,
    marginBottom: 10,
  },
  whiteBox: {
    backgroundColor: '#FFFFFF',
    height: 300,
    borderRadius: 5,
    padding: 10,
  },
  profileContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 10,
  },
  profileImage: {
    width: 50,
    height: 50,
    borderRadius: 25,
    marginRight: 10,
  },
  username: {
    fontSize: 16,
    fontWeight: 'bold',
  },
  separator: {
    height: 1,
    backgroundColor: '#000000',
    marginBottom: 10,
  },
  addFriendIcon: {
    width: 70,
    height: 70,
    resizeMode: 'contain',
  },
  playButton: {
    backgroundColor: 'red',
    paddingHorizontal: 30,
    paddingVertical: 10,
    borderRadius: 5,
    alignSelf: 'center',
    marginTop: 20,
  },
  playButtonText: {
    color: 'white',
    fontSize: 20,
    fontFamily: 'GirottMunch-BoldSlant',
  },
});

export default CreateTeam;
