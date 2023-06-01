import React, { useEffect, useState } from 'react';
import { Text, View, StyleSheet, Image, TouchableOpacity } from 'react-native';
import * as Font from 'expo-font';
import { colors } from '../Styles/theme';
import HeaderImg from '../assets/Images/munch-museet.avif';
import { Entypo, Feather, FontAwesome5, AntDesign } from '@expo/vector-icons';

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
      case 'User':
        return <AntDesign name="user" size={16} color={colors.white} />;
      case 'The Collection':
        return <Feather name="archive" size={16} color={colors.white} />;
      case 'Inventory':
        return <FontAwesome5 name="box-open" size={16} color={colors.white} />;
      case 'Leaderboards':
        return <FontAwesome5 name="trophy" size={16} color={colors.white} />;
      case 'Settings':
        return <AntDesign name="setting" size={16} color={colors.white} />;
      default:
        return null;
    }
  };

  const menuItems = ['User', 'The Collection', 'Inventory', 'Leaderboards', 'Settings'];

  return (
    <View style={styles.container}>
      <View style={styles.dropdownContainer}>
        <TouchableOpacity onPress={toggleMenu} style={styles.dropdownButton}>
          <Entypo name="menu" size={24} color={colors.white} />
        </TouchableOpacity>
        {isMenuOpen && (
          <View style={styles.menu}>
            {menuItems.map((item, index) => (
              <React.Fragment key={item}>
                <TouchableOpacity
                  onPress={() => handleOptionChange(item)}
                  style={[styles.menuItem, index !== 0 && styles.menuItemWithBorder]}
                >
                  <Text style={[styles.menuItemText, selectedOption === item && styles.selectedMenuItemText]}>
                    {renderIcon(item)} {item}
                  </Text>
                </TouchableOpacity>
                {index !== menuItems.length - 1 && <View style={styles.menuItemSeparator} />}
              </React.Fragment>
            ))}
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
    backgroundColor: colors.navy,
    width: 30,
    height: 30,
    borderRadius: 5,
    elevation: 3,
  },
  menu: {
    marginTop: 5,
    backgroundColor: colors.navy,
    borderRadius: 5,
    paddingHorizontal: 10,
    paddingVertical: 5,
    elevation: 3,
  },
  menuItem: {
    paddingVertical: 8,
  },
  menuItemWithBorder: {
    borderTopColor: colors.white,
    borderTopWidth: 1,
  },
  menuItemSeparator: {
    height: 1,
    backgroundColor: colors.white,
  },
  menuItemText: {
    fontSize: 16,
    color: colors.white,
    marginLeft: 5,
    fontFamily: 'GirottMunch-Bold',
  },
  selectedMenuItemText: {
    fontWeight: 'bold',
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
