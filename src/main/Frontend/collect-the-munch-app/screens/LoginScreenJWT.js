import React, { useState, useEffect } from 'react';
import { View, TextInput, Button, StyleSheet, Alert, TouchableOpacity, Text } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import * as Font from 'expo-font';

const LoginScreen = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [fontLoaded, setFontLoaded] = useState(false);

  useEffect(() => {
    loadCustomFont();
    checkLoginStatus();
  }, []);

  const loadCustomFont = async () => {
    await Font.loadAsync({
      'GirottMunch-BoldBackslant': require('../assets/fonts/GirottMunch-BoldBackslant.otf'),
    });
    setFontLoaded(true);
  };

  const checkLoginStatus = async () => {
    // Check if the user is already logged in
    // You can implement your own logic here, such as checking AsyncStorage or a server-side session

    // For demonstration purposes, we'll consider the user logged in if a username is stored
    const storedUsername = await AsyncStorage.getItem('username');
    if (storedUsername) {
      setIsLoggedIn(true);
    }
  };

  const handleLogin = async () => {
    // Perform your login logic here
    // You can replace this with your API call or authentication mechanism

    // For demonstration purposes, we'll simply check if the username and password match hardcoded values
    if (username === 'user1' && password === 'password1') {
      // Update the login state
      setIsLoggedIn(true);

      // Store the username in AsyncStorage to simulate a logged-in state
      await AsyncStorage.setItem('username', username);
    } else {
      // Handle login error
      Alert.alert('Login Failed', 'Invalid username or password');
    }
  };

  const handleLogout = async () => {
    // Clear the username from storage and update the login state
    await AsyncStorage.removeItem('username');
    setIsLoggedIn(false);
  };

  const renderContent = () => {
    if (!fontLoaded) {
      return null; // Render nothing until the custom font is loaded
    }

    if (isLoggedIn) {
      return (
        <View style={styles.loggedContainer}>
          <Text style={styles.loggedText}>You are logged in!</Text>
          <Button title="Logout" onPress={handleLogout} />
        </View>
      );
    } else {
      return (
        <View style={styles.container}>
          <View style={styles.headline}>
            <Text style={styles.headlineText}>MUNCH</Text>
          </View>
          <TextInput
            style={styles.input}
            placeholder="Username"
            placeholderTextColor={'white'}
            value={username}
            onChangeText={setUsername}
            autoCapitalize="none"
          />
          <TextInput
            style={styles.input}
            placeholder="Password"
            placeholderTextColor={'white'}
            value={password}
            onChangeText={setPassword}
            secureTextEntry
          />
          <TouchableOpacity style={styles.button} onPress={handleLogin}>
            <Text style={styles.buttonText}>Login</Text>
          </TouchableOpacity>
        </View>
      );
    }
  };

  return renderContent();
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    padding: 16,
    backgroundColor: '#0F2335',
  },
  headline: {
    backgroundColor: '#FE390F',
    alignSelf: 'stretch',
    paddingVertical: 8,
    marginBottom: 16,
    height: 100,
  },
  headlineText: {
    color: 'white',
    fontSize: 70,
    fontWeight: 'bold',
    textAlign: 'center',
    fontFamily: 'GirottMunch-BoldBackslant',
  },
  input: {
    width: '100%',
    height: 48,
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 4,
    marginBottom: 16,
    padding: 10,
    fontFamily: 'GirottMunch-BoldBackslant',
    textAlign: 'center',
    color: 'white',
  },
  button: {
    width: '100%',
    height: 48,
    backgroundColor: '#FE390F',
    borderRadius: 4,
    alignItems: 'center',
    justifyContent: 'center',
  },
  buttonText: {
    color: '#ffff',
    fontSize: 18,
    fontWeight: 'bold',
    fontFamily: 'GirottMunch-BoldBackslant',
  },
  loggedContainer: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    padding: 16,
  },
  loggedText: {
    fontSize: 20,
    marginBottom: 16,
    fontFamily: 'GirottMunch-BoldBackslant',
  },
});

export default LoginScreen;
