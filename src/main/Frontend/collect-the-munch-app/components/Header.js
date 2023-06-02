import React, { useState } from 'react'
import { Text, StyleSheet, SafeAreaView, TouchableOpacity } from 'react-native'
import { colors } from '../Styles/theme'
import MenuModal from './MenuModal'

const Header = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const handleMenuClick = () => {
    setIsMenuOpen(true);
  }

  const closeMenu = () => {
    setIsMenuOpen(false); 
  }

  return (
    <SafeAreaView style={styles.headerContainer}>
      <Text style={styles.headerText}>Collect the MUNCH</Text>
      <TouchableOpacity style={styles.menuBtn} onPress={handleMenuClick}>
        <Text style={styles.menuBtnText}>
          Menu
        </Text>   
      </TouchableOpacity>
      <MenuModal visible={isMenuOpen} closeMenu={closeMenu} />
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
    alignItems: "center",
    backgroundColor: '#0F2335',
    paddingTop: 20,
  },
  headerText: {
    fontSize: 20,
    fontWeight: 'bold',
    textAlign: 'center',
    color: "white",
    paddingHorizontal: 10,
    fontFamily: "GirottMunch-BoldBackslant"
  },
  menuBtn: {
    position: "absolute",
    bottom: "35%",
    right: 15,
  },
  menuBtnText: {
    color: colors.white,
    fontSize: 20
  }
})
