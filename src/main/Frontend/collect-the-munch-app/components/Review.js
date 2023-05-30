import React, {useState} from 'react';
import { StyleSheet, Text, TouchableOpacity, View, TextInput, Button, Alert} from 'react-native';
import {Rating, AirbnbRating } from 'react-native-elements';

const Review = () => {
    
    // States
    const [message, setMessage] = useState('');
    const [rating, setRating] = useState(0);


  
    // Functions
    const handleRatingChange = (newRating) => {
      setRating(newRating);
      console.log(rating)
      handleSendMessage()
    };
    
    const handleSendMessage = () => {
      setMessage('');
      console.log(message)
    };

    return (
      <View style={styles.Container}>
        <Rating
          showRating
          onFinishRating={handleRatingChange}
          style={styles.Rating}
        />

        <View>
          <TextInput
          onSubmitEditing={handleRatingChange}
            maxLength={250}
            style={styles.TextInput}
            multiline
            numberOfLines={4}
            placeholder="Enter your message"
            value={message}
            onChangeText={setMessage}
            placeholderTextColor="white"
          />
        </View>

        <TouchableOpacity title="Send" onPress={handleRatingChange}>
            <Text style={styles.submitBtn} >
                Submit
            </Text>
        </TouchableOpacity>
      </View>
    );
  };

  export default Review;


  const styles = StyleSheet.create({
    Container: {
      width: "100%",
      padding: 20,
      gap: 10,
      backgroundColor:"#0F2335",
    },
    Rating: {
      paddingVertical: 10,
      backgroundColor: "white"

    },
    submitBtn: {
        backgroundColor: "#FE390F",
        color: "white",
        paddingVertical: 10,
        fontSize: 20,
        textAlign: "center",
        borderRadius:40,
        marginBottom: 250,
    },
    TextInput: {
      borderWidth: 1,
      borderColor: "#eeee",
      padding:10,
      width: "100%", 
      minHeight: 100,
      maxHeight: 100,
      color: "white",
      fontSize:20
    }

  })