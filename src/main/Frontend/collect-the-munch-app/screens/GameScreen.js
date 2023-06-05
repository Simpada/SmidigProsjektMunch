import React from 'react'
import { View, Text, StyleSheet } from 'react-native'
import SubmitCamera from './SubmitCamera'


const GameScreen = () => {
  return (
    <View style={styles.container}>
      <SubmitCamera/>
    </View>
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