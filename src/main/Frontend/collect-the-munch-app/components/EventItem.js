import React from 'react';
import { Text, View, StyleSheet, Image, Touchable, TouchableOpacity } from 'react-native';
import PlaceholderImg from '../assets/Images/PlaceholderImage.jpg';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import Review from './Review';
import { colors } from '../Styles/theme';

const Stack = createStackNavigator();

// Remove the duplicate import React from 'react';

const EventItem = (props) => {
  const { name, description, categories } = props;

  return (
    <View style={styles.card}>
      <Image style={styles.image} source={PlaceholderImg} />
      <View style={styles.contentContainer}>
        <Text style={styles.eventTitle}>{name}</Text>
        <Text style={styles.eventDesc}>{description}</Text>
        <View style={styles.categoriesContainer}>
          {categories.map((category, index) => (
            <React.Fragment key={index}>
              <Text style={styles.eventCat}>{category}</Text>
              {index !== categories.length -1 && <Text style={styles.comma}>, </Text>}
            </React.Fragment>
          ))}
        </View>
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
    categoriesContainer:{
      flexDirection: "row"
    },
    comma: {
      color: colors.red
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
  
