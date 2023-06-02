import React, { useState, useEffect } from 'react';
import { View, TextInput, Button, StyleSheet, Alert, TouchableOpacity, Text } from 'react-native';
import axios from 'axios';
import AsyncStorage from '@react-native-async-storage/async-storage';
import jwtDecode from 'jwt-decode';
import * as Font from 'expo-font';

const LoginScreen = () => {
  const [email, setEmail] = useState('');
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
    // Check if a token exists in storage
    const token = await AsyncStorage.getItem('jwt_token');
    if (token) {
      // Verify the token's validity
      try {
        const decodedToken = jwtDecode(token);
        const currentTime = Date.now() / 1000; // Convert to seconds

        // Check if the token has expired
        if (decodedToken.exp < currentTime) {
          // Token has expired, clear it from storage
          await AsyncStorage.removeItem('jwt_token');
          setIsLoggedIn(false);
        } else {
          // Token is valid, set the login state
          setIsLoggedIn(true);
        }
      } catch (error) {
        // Error occurred while decoding or verifying the token
        console.log('Token verification error:', error);
      }
    }
  };

  const handleLogin = async () => {
    try {
      const response = await axios.post('Ur API endpoint to POST here', {
        email,
        password,
      });

      // Extract the JWT token from the response
      const token = response.data.token;

      // Store the token in AsyncStorage
      await AsyncStorage.setItem('jwt_token', token);

      // Decode the token to get user information
      const user = jwtDecode(token);

      // Perform any necessary actions with the user data
      console.log('User:', user);

      // Update the login state
      setIsLoggedIn(true);
    } catch (error) {
      // Handle login error
      Alert.alert('Login Failed', error.message);
    }
  };

  const handleLogout = async () => {
    // Clear the token from storage and update the login state
    await AsyncStorage.removeItem('jwt_token');
    setIsLoggedIn(false);
  };

  const renderContent = () => {
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
            placeholder="Email"
            value={email}
            onChangeText={setEmail}
            autoCapitalize="none"
          />
          <TextInput
            style={styles.input}
            placeholder="Password"
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

  if (!fontLoaded) {
    return null; // Render nothing until the custom font is loaded
  }

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

