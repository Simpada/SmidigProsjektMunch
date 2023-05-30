import React, {useState} from 'react';
import { StyleSheet, Text, View } from 'react-native';
import {Rating, AirbnbRating } from 'react-native-elements';

const Review = () => {
    const [rating, setRating] = useState(0);

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
        <Text>Selected rating: {rating}</Text>

      </View>
    );
  };

  export default Review;