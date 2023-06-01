import React, { useState } from 'react';
import { View, TextInput, Button } from 'react-native';
import { setCookie } from 'react-native-cookies';
import { login } from './api'; // Imports the login API function

const LoginScreen = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = () => {
    const userData = {
      email,
      password,
    };

    login(userData)
      .then((response) => {
        // Handle successful login
        console.log(response.data);

        // Set the cookie
        const cookieData = {
          name: 'sessionToken',
          value: '{userId}',
          domain: 'mockbin.com',
          path: '/',
          version: '1',
          secure: true,
          httpOnly: true, s
        };

        setCookie(cookieData)
          .then(() => {
            console.log('Cookie set successfully');
            // Redirect or navigate to the next screen
          })
          .catch((error) => {
            console.log('Error setting cookie:', error);
          });
      })
      .catch((error) => {
        // Handle login error
        console.log(error.response.data);
      });
  };

  return (
    <View>
      <TextInput
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
      />
      <TextInput
        placeholder="Password"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
      />
      <Button title="Login" onPress={handleLogin} />
    </View>
  );
};

export default LoginScreen;
``
