import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, Alert } from 'react-native';

const LoginScreen = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = () => {
    // Perform login authentication logic here
    if (username === 'example' && password === 'password') {
      // Successful login
      Alert.alert('Login', 'Login successful!');
    } else {
      // Failed login
      Alert.alert('Login', 'Invalid username or password');
    }
  };

  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <Text style={{ fontSize: 24, marginBottom: 20 }}>Login Page</Text>
        <TextInput
            style={{ width: '80%', height: 40, borderColor: 'gray', borderWidth: 1, marginBottom: 10, paddingHorizontal: 10 }}
            placeholder="Username"
            onChangeText={text => setUsername(text)}
            value={username}
            />
        <TextInput
            style={{ width: '80%', height: 40, borderColor: 'gray', borderWidth: 1, marginBottom: 20, paddingHorizontal: 10 }}
            placeholder="Password"
            secureTextEntry
            onChangeText={text => setPassword(text)}
            value={password}
            />
        <TouchableOpacity
            style={{ backgroundColor: 'blue', padding: 10 }}
            onPress={handleLogin}
            >
            <Text style={{ color: 'white', fontSize: 18 }}>Login</Text>
        </TouchableOpacity>
        
    </View> 

  );
};

export default LoginScreen;
