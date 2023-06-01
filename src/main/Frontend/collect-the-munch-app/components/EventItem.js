import React from 'react';
import { Text, View, StyleSheet, Image, Touchable, TouchableOpacity } from 'react-native';
import PlaceholderImg from '../assets/Images/PlaceholderImage.jpg';
import { NavigationContainer } from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack'

const Stack = createStackNavigator();

const EventItem = (props) => {

  return (
    <View style={styles.card}>
        <Image style={styles.image} source={PlaceholderImg} />
        <View style={styles.contentContainer}>
          <Text style={styles.eventTitle}>{props.title}</Text>
          <Text style={styles.eventDesc}>{props.description}</Text>
          <Text style={styles.eventCat}>{props.category}</Text>
        </View>
    </View>
  );
};

export default EventItem;

const styles = StyleSheet.create({
    card: {
      width: "100%",
      backgroundColor: '#0F2335',
      borderRadius: 10,
      height: 250,
      overflow: "hidden",
      marginVertical: 5,
      zIndex: 1
    },
    contentContainer: {
        flex: 1,
        padding: 10,
        justifyContent:"space-between"
    },
    image: {
        width: "100%", 
        height: 100,  
        resizeMode: "cover"
    },
    eventTitle: {
        fontSize: 23, 
        color: "white",
        fontWeight: "bold"
    },
    eventDesc: {
        fontSize: 18,
        color: "white"
    },
    eventCat: {
        color: "#FE390F",
        textTransform:"capitalize",
        textAlign:"right",
        fontWeight:"700"
    }

  });
  
