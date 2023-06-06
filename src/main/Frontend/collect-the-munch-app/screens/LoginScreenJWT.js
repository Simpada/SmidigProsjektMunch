import React, { useState, useEffect } from 'react';
import { View, TextInput, Button, StyleSheet, Alert, TouchableOpacity, Text } from 'react-native';
import axios from 'axios';
import * as Font from 'expo-font';

const LoginScreen = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [fontLoaded, setFontLoaded] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    loadCustomFont();
  }, []);

  const loadCustomFont = async () => {
    await Font.loadAsync({
      'GirottMunch-BoldBackslant': require('../assets/fonts/GirottMunch-BoldBackslant.otf'),
    });
    setFontLoaded(true);
  };

  const handleLogin = async () => {
    try {
      const response = await axios.get(`https://findthemunchgame.azurewebsites.net/api/user/login/${username}/${password}`);

      // Handle the response without JWT-related code
      const user = response.data;

      // Perform any necessary actions with the user data
      console.log('User:', user);

      // Update the login state
      setIsLoggedIn(true);
    } catch (error) {
      // Handle login error
      Alert.alert('Login Failed', error.message);
    }
  };

  const renderContent = () => {
    if (isLoggedIn) {
      return (
        <View style={styles.container}>
          {/* Content for logged-in user */}
          <Text style={styles.loggedInText}>You are logged in!</Text>
          <Button title="Logout" onPress={() => setIsLoggedIn(false)} />
        </View>
      );
    } else {
      return (
        <View style={styles.container}>
          {/* Content for login screen */}
          <View style={styles.headline}>
            <Text style={styles.headlineText}>MUNCH</Text>
          </View>
          <TextInput
            style={[styles.input, { color: 'white' }]}
            placeholder="Username"
            placeholderTextColor={'white'}
            value={username}
            onChangeText={setUsername}
            autoCapitalize="none"
          />
          <TextInput
            style={[styles.input, { color: 'white' }]}
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
  loggedInText: {
    color: 'white',
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 16,
  },
});

export default LoginScreen;
