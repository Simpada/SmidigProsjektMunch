import React from 'react';
import { View, Text, StyleSheet, Button } from 'react-native';

const CustomHeader = ({ title, navigation }) => {
    return (
        <View style={styles.header}>
            {navigation.canGoBack() && <Button title="Back" onPress={() => navigation.goBack()} />}
            <Text style={styles.title}>{title}</Text>
        </View>
    );
}

const styles = StyleSheet.create({
    header: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        paddingHorizontal: 10,
        backgroundColor: '#f8f8f8',
        height: 60,
    },
    title: {
        fontSize: 20,
        textAlign: 'center',
    },
});

export default CustomHeader;
