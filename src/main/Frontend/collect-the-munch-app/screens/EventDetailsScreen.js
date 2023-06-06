import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { colors } from '../Styles/theme';
const EventDetailsScreen = ({ route }) => {
  const { event } = route.params;

  return (

    <View style={styles.container}>
      {/*
      <View style={styles.headerContainer}>
        <Text style={styles.headerText}>MUNCH</Text>
      </View>
      */}
      <View style={styles.eventContent}>
        <Text>Event Name</Text>
        {!event.title ? <Text>No event title</Text> : <Text style={styles.eventTitle}>{event.title}</Text>}
        <Text style={styles.eventCategory}>{event.category}</Text>
        <Text style={styles.eventDescription}>{event.description}</Text>
      </View>
    </View>
  );
};

export default EventDetailsScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.navy,
  }, 
  headerContainer: {
    borderWidth:1,
    marginVertical: 50,
    width: "100%",
    height: 150,
    backgroundColor: colors.red,
    justifyContent:"center",
    alignItems:"center",
  },
  headerText: {
    fontSize: 90,
    borderWidth:1,
    color: colors.white,
    fontFamily: "GirottMunch-BoldBackslant"
  },
  eventContent: {
    padding: 20,
  },
  eventTitle: {
    textAlign:"center",
    fontSize: 35, 
    color: colors.white,
    fontFamily: "GirottMunch-BoldBackslant", 

  },
  eventCategory: {
    marginVertical: 10,
    textAlign: "right",
    fontWeight: 700,
    color: colors.red,
    fontSize: 16,
    textTransform: "uppercase"
  },
  eventDescription: {
    fontSize: 20, 
    color: colors.white,
    lineHeight: 30
  }
})
