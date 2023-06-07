import React, { useState } from 'react';
import { StyleSheet, Text, View, Button, TextInput, Pressable } from 'react-native';
import { Rating } from 'react-native-elements';
import {colors} from '../Styles/theme';
const Review = () => {
  const [rating, setRating] = useState(0);
  const [comment, setComment] = useState('');

  const handleRatingChange = (newRating) => {
    setRating(newRating);
  };

  const handleCommentChange = (text) => {
    setComment(text);
  };

    //POST functionality
  const handleSubmit = () => {
    const endpoint = 'Paste the API endpoint u want to POST to.';
    const payload = { rating, comment };

    fetch(endpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(payload),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log('Review submitted:', data);
      })
      .catch((error) => {
        console.error('Error submitting review:', error);
      });
  };

  return (
    <View style={styles.container}>
      <Text style={styles.reviewText}>Leave your thoughts on the event!</Text>
      <Rating
        showRating
        onFinishRating={handleRatingChange}
        style={{ paddingVertical: 10 }}
      />
      <TextInput
        style={styles.input}
        multiline
        value={comment}
        onChangeText={handleCommentChange}
        placeholder="Write a comment..."
      />
      <Pressable onPress={handleSubmit} style={styles.button}> 
        <Text style={styles.btnText}>Submit</Text>
      </Pressable>
    </View>
  );
};
//Styles
const styles = StyleSheet.create({
  container: {
    padding: 20,
    width: "100%",
    gap: 10,
    height: 300,
    backgroundColor: colors.white,
    alignItems: "center",
  },
  reviewText: {
    fontSize: 20,
    color: "white"
  },

  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
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
    borderRadius: 20
  },
  button: {
    width: "100%",
    height: 40,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: colors.red,
    borderRadius: 20,
  },
  btnText: {
    color: colors.white,
    fontSize: 20,
  }
});

export default Review;