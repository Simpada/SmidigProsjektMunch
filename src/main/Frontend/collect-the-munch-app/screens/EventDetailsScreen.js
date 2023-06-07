import React from 'react';
import { View, Text, StyleSheet, ScrollView, Image } from 'react-native';
import { colors } from '../Styles/theme';
import Review from '../components/Review';
import placeHolderImg from '../assets/Images/samuel.png' 
const EventDetailsScreen = ({ route }) => {
  const { event } = route.params;


  return (

    <ScrollView>
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
          <View>
            <Image source={placeHolderImg} style={styles.eventImage}/>
          </View>
          <View style={styles.descriptionContainer}>
            <Text>About this event</Text>
            <Text style={styles.eventDesc}>
              {event.description}
            </Text>
          </View>
        </View>
        <Review />
      </View>
    </ScrollView>
  );
};

export default EventDetailsScreen;

const styles = StyleSheet.create({
  // Page styles
  container: {
    backgroundColor: colors.navy,
    marginBottom: 200
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
  eventDesc: {
    fontSize: 18,
    color: "white"
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
  eventImage:{
    marginTop: 30,
    width: "100%", 
    height: 300,  
    resizeMode: "cover"
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
  descriptionContainer: {
    marginVertical:30
  },
  eventDescription: {
    fontSize: 20, 
    color: colors.white,
    lineHeight: 30
  }
})
