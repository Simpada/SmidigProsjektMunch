import React, { useCallback, useRef, useEffect } from 'react';
import { View, Text, Modal, TouchableOpacity, StyleSheet, Animated } from 'react-native';
import { colors } from '../Styles/theme';
import { useNavigation } from '@react-navigation/native';

const MenuModal = ({ visible, closeMenu }) => {
    const navigation = useNavigation();
    const isUserLoggedIn = true;
    const animValue = useRef(new Animated.Value(500)).current;

    const handleMenuItemPress = useCallback((value) => {
        navigation.navigate(value);
        closeMenu();
    }, [navigation, closeMenu]);

    const handleClose = () => {
        Animated.timing(animValue, {
            toValue: 500,
            duration: 500,
            useNativeDriver: false,
        }).start(closeMenu);
    };

    useEffect(() => {
        if (visible) {
            animValue.setValue(500);
            Animated.timing(animValue, {
                toValue: 0,
                duration: 500,
                useNativeDriver: false,
            }).start();
        } else {
            handleClose();
        }
    }, [visible]);

    return (
        <Modal visible={visible} animationType="none" transparent>
            <View style={styles.modalContainer}>
                <Animated.View style={[styles.modalContent, { transform: [{ translateX: animValue }] }]}>
                    <TouchableOpacity onPress={handleClose} style={styles.closeButton}>
                        <Text style={styles.closeButtonText}>X</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.menuItem} onPress={() => handleMenuItemPress('Leaderboard')}>
                        <Text style={styles.menuItemText}>Leaderboard</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.menuItem} onPress={() => handleMenuItemPress('Profile')}>
                        <Text style={styles.menuItemText}>Profile</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.menuItem} onPress={() => handleMenuItemPress("Play Game")}>
                        <Text style={styles.menuItemText}>Game</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.menuItem} onPress={() => handleMenuItemPress("Events")}>
                        <Text style={styles.menuItemText}>Events</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.menuItem} onPress={() => handleMenuItemPress(!isUserLoggedIn ? "Login" : "Logout")}>
                        <Text style={styles.menuItemText}>
                            {!isUserLoggedIn ? "Login" : "Log out"}
                        </Text>
                    </TouchableOpacity>
                </Animated.View>
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
        backgroundColor: 'rgba(15, 35, 53, 0.5)',
        padding: 20,
        marginTop: 100,
        marginRight: 10,
        width: '80%',
        height: "60%",
        alignItems: 'center',
    },
    closeButton: {
        backgroundColor: colors.red,
        borderRadius: 5,
        alignSelf: 'flex-end',
        marginBottom: 10,
    },
    closeButtonText: {
        textAlign: "center",
        paddingTop: 3,
        width: 35,
        height: 35,
        fontSize: 25,
        fontWeight: 'bold',
        color: colors.white,    },
        menuItem: {
            marginVertical: 10,
            width: "90%",
            padding: 10,
            borderWidth: 1,
            borderColor: "#fff",
            borderRadius: 5
        },
        menuItemText: {
            fontSize: 30,
            textAlign: "center",
            color: colors.white,
        }
    });
    
    export default MenuModal;
    
