import React, { useEffect, useState } from 'react';
import { Text, View, StyleSheet, Image, TouchableOpacity } from 'react-native';
import * as Font from 'expo-font';
import { colors } from '../Styles/theme';
import HeaderImg from '../assets/Images/munch-museet.avif';
import { AntDesign, Entypo, Feather, FontAwesome5 } from '@expo/vector-icons';

const HomeScreen = () => {
  useEffect(() => {
    loadFonts();
  }, []);

  const loadFonts = async () => {
    await Font.loadAsync({
      'GirottMunch-BoldBackslant': require('../assets/fonts/GirottMunch-BoldBackslant.ttf'),
    });
  };

  const [isMenuOpen, setMenuOpen] = useState(false);
  const [selectedOption, setSelectedOption] = useState('Option 1');

  const handleOptionChange = (value) => {
    setSelectedOption(value);
    setMenuOpen(false);
  };

  const toggleMenu = () => {
    setMenuOpen(!isMenuOpen);
  };

  const renderIcon = (option) => {
    switch (option) {
      case 'Option 1':
        return <AntDesign name="user" size={16} color={colors.black} />;
      case 'Option 2':
        return <Feather name="archive" size={16} color={colors.black} />;
      case 'Option 3':
        return <FontAwesome5 name="box-open" size={16} color={colors.black} />;
      case 'Option 4':
        return <FontAwesome5 name="trophy" size={16} color={colors.black} />;
      case 'Option 5':
        return <AntDesign name="setting" size={16} color={colors.black} />;
      default:
        return null;
    }
  };

  return (
    <View style={styles.container}>
      <View style={styles.dropdownContainer}>
        <TouchableOpacity onPress={toggleMenu} style={styles.dropdownButton}>
          <AntDesign name={isMenuOpen ? 'caretup' : 'caretdown'} size={24} color={colors.black} />
        </TouchableOpacity>
        {isMenuOpen && (
          <View style={styles.menu}>
            <TouchableOpacity onPress={() => handleOptionChange('Option 1')} style={styles.menuItem}>
              <Text style={styles.menuItemText}>
                {renderIcon('Option 1')} Profile
              </Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => handleOptionChange('Option 2')} style={styles.menuItem}>
              <Text style={styles.menuItemText}>
                {renderIcon('Option 2')} The Collection
              </Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => handleOptionChange('Option 3')} style={styles.menuItem}>
              <Text style={styles.menuItemText}>
                {renderIcon('Option 3')} Inventory
              </Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => handleOptionChange('Option 4')} style={styles.menuItem}>
              <Text style={styles.menuItemText}>
                {renderIcon('Option 4')} Leaderboard
              </Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => handleOptionChange('Option 5')} style={styles.menuItem}>
              <Text style={styles.menuItemText}>
                {renderIcon('Option 5')} Settings
              </Text>
            </TouchableOpacity>
          </View>
        )}
      </View>
      <View style={styles.imageContainer}>
        <Image style={styles.image} source={HeaderImg} resizeMode="cover" />
      </View>
      <Text style={styles.text}>MUNCH</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.navy,
    alignItems: 'center',
  },
  dropdownContainer: {
    position: 'absolute',
    top: 40,
    left: 20,
    zIndex: 1,
  },
  dropdownButton: {
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: colors.white,
    width: 30,
    height: 30,
    borderRadius: 5,
    elevation: 3,
  },
  menu: {
    marginTop: 5,
    backgroundColor: colors.white,
    borderRadius: 5,
    paddingHorizontal: 10,
    paddingVertical: 5,
    elevation: 3,
  },
  menuItem: {
    paddingVertical: 8,
  },
  menuItemText: {
    fontSize: 16,
    color: colors.black,
    marginLeft: 5,
  },
  imageContainer: {
    width: '100%',
    height: 400,
  },
  image: {
    flex: 1,
    width: undefined,
    height: undefined,
  },
  text: {
    position: '',
    top: 20,
    color: colors.red,
    fontSize: 100,
    fontFamily: 'GirottMunch-BoldBackslant',
    zIndex: 1,
  },
});

export default HomeScreen;
