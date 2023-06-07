import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { colors } from '../Styles/theme';
import Review from '../components/Review';
const EventDetailsScreen = ({ route }) => {
  const { event } = route.params;


  return (

    <View style={styles.container}>
      <View style={styles.eventContent}>
        {!event.name ? <Text>No event title</Text> : <Text style={styles.eventTitle}>{event.name}</Text>}
        <View style={styles.categoriesContainer}>
        {event.categories.map((category, index) => (
            <React.Fragment key={index}>
              <Text style={styles.eventCat}>{category}</Text>
              {index !== event.categories.length -1 && <Text style={styles.comma}>, </Text>}
            </React.Fragment>
          ))}
        </View>
      </View>
      <Review />
    </View>
  );
};

export default EventDetailsScreen;

const styles = StyleSheet.create({
  // Page styles
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
  // Event styles
  eventContent: {
    flex:1,
    padding: 20,
  },
  eventCat: {
    color: "#FE390F",
    textTransform:"capitalize",
    fontWeight:"700", 
    fontSize: 20
  },
  comma:{
    color: colors.red,
    fontWeight:"700", 
    fontSize: 20
  },
  categoriesContainer:{
    flexDirection:"row",
    marginTop: 20,
    justifyContent: "flex-end"
   
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
