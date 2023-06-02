import React from 'react'
import {Text, StyleSheet, SafeAreaView, TouchableOpacity, Alert } from 'react-native'
import { colors } from '../Styles/theme'
const Header = () => {

  const handleMenuClick = () => {
    Alert.alert("Menu functionality will come soon!")
  }


  return (
    <SafeAreaView style={styles.headerContainer}>
        <Text style={styles.headerText}>Collect the MUNCH</Text>
        <TouchableOpacity style={styles.menuBtn} onPress={handleMenuClick}>
          <Text style={styles.menBtnText}>
            Menu
          </Text>
        </TouchableOpacity>
    </SafeAreaView>
  )
}

export default Header

const styles = StyleSheet.create({
    headerContainer: {
      flexDirection: "row",
      height: 100,
      width: "100%", 
      borderWidth: 1,
      justifyContent: "center",
      alignItems:"center",
      backgroundColor: '#0F2335', // Set your desired background color
      paddingTop: 20,
    },
    headerText: {
        fontSize: 20, // Adjust the font size as needed
        fontWeight: 'bold', // Adjust the font weight as needed
        textAlign: 'center',
        color: "white",
        paddingHorizontal:10,
        fontFamily: "GirottMunch-BoldBackslant"
    },
    menuBtn: {
      position:"absolute",
      bottom: "35%",
      right: 15, 
    }, 
    menBtnText: {
      color: colors.white,
      fontSize: 20
    }
})