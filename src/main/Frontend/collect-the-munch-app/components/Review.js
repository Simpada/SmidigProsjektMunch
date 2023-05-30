import React, {useState} from 'react';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import {Rating, AirbnbRating } from 'react-native-elements';

const Review = () => {
    const [rating, setRating] = useState(0);
    const handleReview = () => {
        alert.alert(rating)
    }
    const handleRatingChange = (newRating) => {
      setRating(newRating);
    };

    return (
      <View>
        <Text>Please rate ur experience! </Text>
        <Rating
          showRating
          onFinishRating={handleRatingChange}
          style={{ paddingVertical: 10 }}
        />
        <TouchableOpacity onPress={handleReview}>
            <Text style={styles.submitBtn} >
                Submit
            </Text>
        </TouchableOpacity>

      </View>
    );
  };

  export default Review;


  const styles = StyleSheet.create({
    submitBtn: {
        backgroundColor: "#FE390F",
        color: "white",
        paddingVertical: 10,
        fontSize: 20,
        textAlign: "center",
        borderRadius:40,
    },

  })