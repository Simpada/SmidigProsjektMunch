import React, { useState } from 'react';
import { StyleSheet, Text, View, Button, TextInput, Pressable, Keyboard, Alert } from 'react-native';
import { Rating } from 'react-native-elements';
import { colors } from '../Styles/theme';
import axios from 'axios';

const Review = ({ reviewType, eventId }) => {
  const userId = 20;
  let endpoint = '';

  if (reviewType === 'event') {
    endpoint = `https://findthemunchgame.azurewebsites.net/api/review/event/${eventId}/${userId}`;
  } else if (reviewType === 'app') {
    endpoint = `https://findthemunchgame.azurewebsites.net/api/review/app/${userId}`;
  }

  const [rating, setRating] = useState(0);
  const [comment, setComment] = useState('');

  const handleRatingChange = (newRating) => {
    setRating(newRating);
  };

  const handleCommentChange = (text) => {
    setComment(text);
  };

  const handleSubmit = () => {
    const payload = {
      reviewText: comment,
      numOfStars: rating
    };

    axios.post(endpoint, payload, {
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then((response) => {
        console.log('Review submitted:', response.data);
        Alert.alert(`Thank you for reviewing the ${reviewType}`)
      })
      .catch((error) => {
        console.error('Error submitting review:', error);
      });
  };

  const handleKeyPress = ({ nativeEvent }) => {
    if (nativeEvent.key === 'Enter') {
      Keyboard.dismiss();
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.reviewText}>Leave your thoughts{reviewType === 'event' ? ' on the event!' : ' on the app!'}</Text>
      <Rating
        showRating
        onFinishRating={handleRatingChange}
        style={{ paddingVertical: 10 }}
      />
      <TextInput
        style={styles.input}
        value={comment}
        maxLength={200}
        onChangeText={handleCommentChange}
        placeholder="Write a comment..."
        onKeyPress={handleKeyPress}
      />
      <Pressable onPress={handleSubmit} style={styles.button}>
        <Text style={styles.btnText}>Submit</Text>
      </Pressable>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 20,
    width: '100%',
    gap: 10,
    height: "100%",
    backgroundColor: colors.white,
    alignItems: 'center',
  },
  reviewText: {
    fontSize: 20,
    color: 'white',
  },
  input: {
    height: 40,
    width: '100%',
    borderColor: colors.red,
    borderWidth: 2,
    minHeight: 100,
    maxHeight: 100,
    marginBottom: 10,
    paddingTop: 20,
    paddingHorizontal: 20,
    borderRadius: 20,
  },
  button: {
    width: '100%',
    height: 40,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: colors.red,
    borderRadius: 20,
  },
  btnText: {
    color: colors.white,
    fontSize: 20,
  },
});

export default Review;
