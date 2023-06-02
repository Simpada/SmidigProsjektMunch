import React,{ useCallback } from 'react';
import { View, Text, Modal, TouchableOpacity, StyleSheet, Alert } from 'react-native';
import { colors } from '../Styles/theme';
import { useNavigation } from '@react-navigation/native';

const MenuModal = ({ visible, closeMenu }) => {
    const navigation = useNavigation();

    const handleMenuItemPress = useCallback((value) => {
        navigation.navigate(value);
        closeMenu();
      }, [navigation, closeMenu]);

  return (
    <Modal visible={visible} animationType="fade" transparent>
      <View style={styles.modalContainer}>
        <View style={styles.modalContent}>

          <TouchableOpacity onPress={closeMenu} style={styles.closeButton}>
            <Text style={styles.closeButtonText}>Close Menu</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={() => handleMenuItemPress('Leaderboard')}>
            <Text style={styles.menuItemText}>Leaderboard</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={handleMenuItemPress}>
            <Text style={styles.menuItemText}>Profile</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={() => handleMenuItemPress("Play Game")}>
            <Text style={styles.menuItemText}>Game</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={() => handleMenuItemPress("Events")}>
            <Text style={styles.menuItemText}>Events</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={handleMenuItemPress}>
            <Text style={styles.menuItemText}>Login / Signup</Text>
          </TouchableOpacity>
     
        </View>
      </View>
    </Modal>
  );
};

const styles = StyleSheet.create({
  modalContainer: {
    flex: 1,
    justifyContent: 'flex-start',
    alignItems: 'flex-end',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
},
modalContent: {
    backgroundColor: colors.navy,
    padding: 20,
    marginTop: 100,
    width: '80%',
    height: "60%",
    alignItems: 'center',
  },
  closeButton: {
    alignSelf: 'flex-end',
    marginBottom: 10,
  },
  closeButtonText: {
    fontSize: 16,
    fontWeight: 900,
    color: colors.red,
  },
  menuItem: {
      marginVertical: 10,
      width: "90%",
      padding: 10,
      borderWidth:1,
      borderColor: "#fff",
      borderRadius: 5
    },
menuItemText: {
    fontSize: 30,
    textAlign:"center",
    color: colors.white,
  }
});

export default MenuModal;
