import React from 'react'
import {Text, StyleSheet, SafeAreaView } from 'react-native'
const Header = () => {
  return (
    <SafeAreaView style={styles.headerContainer}>
        <Text style={styles.headerText}>Collect the MUNCH</Text>
    </SafeAreaView>
  )
}

export default Header

const styles = StyleSheet.create({
    headerContainer: {
        height: 100,
        width: "100%", 
        borderWidth: 1,
        justifyContent: "center",
        alignItems:"center",
        backgroundColor: '#0F2335', // Set your desired background color
        paddingTop: 20,
    },
    headerText: {
        fontSize: 18, // Adjust the font size as needed
        fontWeight: 'bold', // Adjust the font weight as needed
        textAlign: 'center',
        color: "white"
    }
})