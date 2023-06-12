import React, { useState } from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import LoginScreen from '../screens/LoginScreenJWT';
import Header from '../components/Header';

const ProfileScreen = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <>
    <Header />
    <View style={styles.container}>
      {isLoggedIn ? (
        <View>
          <Ionicons name="person-circle-outline" size={100} color="#FE390F" />
          <Text style={styles.title}>Logged in</Text>
          {/* Add your logged-in profile content here */}
        </View>
      ) : (
        <LoginScreen onLogin={() => setIsLoggedIn(true)} />
        )}
    </View>
    </>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    width:'100%',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginTop: 16,
  },
});

export default ProfileScreen;
