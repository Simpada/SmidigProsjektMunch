import React from 'react'
import { View, Text, StyleSheet } from 'react-native'
import * as Font from 'expo-font';
import SubmitCamera from '../screens/SubmitCamera'
import Header from '../components/Header';
const GameScreen = () => {
  return (
    <>
    <Header />
    <View style={styles.container}>
      <SubmitCamera />
    </View>
    </>
  )
}

const styles = StyleSheet.create({
    container: {
        flex:1,
        justifyContent: "center",
        alignItems: "center"
    }
})

export default GameScreen