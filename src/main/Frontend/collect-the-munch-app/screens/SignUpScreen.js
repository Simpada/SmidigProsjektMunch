import React, { useState, useEffect } from 'react';
import { View, TextInput, Button, StyleSheet, Alert, TouchableOpacity, Text } from 'react-native';
import axios from 'axios';
import * as Font from 'expo-font';
import { imageToByteArray } from '../components/ImageToByteArray';


const SignupScreen = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [dateOfBirth, setDateOfBirth] = useState('');
  const [email, setEmail] = useState('');
  const [fontLoaded, setFontLoaded] = useState(false);

  useEffect(() => {
    loadCustomFont();
  }, []);

  const loadCustomFont = async () => {
    await Font.loadAsync({
      'GirottMunch-BoldBackslant': require('../assets/fonts/GirottMunch-BoldBackslant.otf'),
    });
    setFontLoaded(true);
  };

  const handleSignup = async () => {
    // Validate input values
    if (!validateEmail(email)) {
      Alert.alert('Invalid Email', 'Please enter a valid email address');
      return;
    }

    if (!validateBirthDate(dateOfBirth)) {
      Alert.alert('Invalid Birth Date', 'Please enter a valid birth date');
      return;
    }

    if (!validateUsername(username)) {
      Alert.alert('Invalid Username', 'Username should not exceed 20 characters');
      return;
    }

    if (!validatePassword(password)) {
      Alert.alert('Invalid Password', 'Password should not exceed 30 characters');
      return;
    }
  

    try {
      const byteArray = [1, 2, 3, 4, 5]
      const response = await axios.post('https://findthemunchgame.azurewebsites.net/api/user/register', {
        username,
        password,
        dateOfBirth,
        email,
        profilePicture: byteArray,
      });

      // Handle successful signup
      console.log(response.data);

      // Clear the input fields
      setUsername('');
      setPassword('');
      setDateOfBirth('');
      setEmail('');

      // Show a success message or navigate to a new screen
      Alert.alert('Signup Successful', 'You have successfully signed up!');
    } catch (error) {
      // Handle signup error
      console.log('Signup Error:', error);
      Alert.alert('Signup Failed', 'An error occurred during signup');
    }
  };

  const validateEmail = (email) => {
    // Simple email validation using regex
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const validateBirthDate = (birthDate) => {
    // Birth date validation: should only contain numbers
    const birthDateRegex = /^[0-9]+$/;
    return birthDateRegex.test(birthDate);
  };

  const validateUsername = (username) => {
    // Username validation: should not exceed 20 characters
    return username.length <= 20;
  };

  const validatePassword = (password) => {
    // Password validation: should not exceed 30 characters
    return password.length <= 30;
  };

  if (!fontLoaded) {
    return null; // Render nothing until the custom font is loaded
  }

  return (
    <View style={styles.container}>
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
      <TextInput
        style={[styles.input, { color: 'white' }]}
        placeholder="Date of Birth"
        placeholderTextColor={'white'}
        value={dateOfBirth}
        onChangeText={setDateOfBirth}
      />
      <TextInput
        style={[styles.input, { color: 'white' }]}
        placeholder="Email"
        placeholderTextColor={'white'}
        value={email}
        onChangeText={setEmail}
        autoCapitalize="none"
      />
      <TouchableOpacity style={styles.button} onPress={handleSignup}>
        <Text style={styles.buttonText}> Signup</Text>
      </TouchableOpacity>
    </View>
  );
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
});

export default SignupScreen;
