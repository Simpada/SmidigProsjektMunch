import React, { useState, useEffect } from 'react';
import { View, TextInput, Button, StyleSheet, Alert, TouchableOpacity, Text } from 'react-native';
import axios from 'axios';
import * as Font from 'expo-font';
import {loadImageAndConvertToByteArray} from "../components/ImageToByteArray";

const SignupScreen = () => {
  const [username, setUsername] = useState('2e1sd');
  const [password, setPassword] = useState('sdsfdsf');
  const [dateOfBirth, setDateOfBirth] = useState('11111111');
  const [email, setEmail] = useState('fdsff@gofgo.com');
  const [fontLoaded, setFontLoaded] = useState(false);
  const [profilePicture, setProfilePicture] = useState([]);

  const handleSignup = async () => {
    try {
      const byteArray = await loadImageAndConvertToByteArray('https://i.imgur.com/ZzVx1hk.png');
      setProfilePicture(byteArray);
      const response = await axios.post('https://findthemunchgame.azurewebsites.net/api/user/register', {
        username,
        password,
        dateOfBirth,
        email,
        profilePicture,
      });

      // Handle successful signup
      console.log(response.data);

      // Clear the input fields
      setUsername('efefefefsdf');
      setPassword('d12d1d');
      setDateOfBirth('11111111');
      setEmail('23fscv@gjf.com');

      // Show a success message or navigate to a new screen
      Alert.alert('Signup Successful', 'You have successfully signed up!');
    } catch (error) {
      // Handle signup error
      console.log('Signup Error:', error);
      Alert.alert('Signup Failed', 'An error occurred during signup');
    }
  };


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
