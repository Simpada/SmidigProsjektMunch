// EventDetailsScreen.js

import React from 'react';
import { View, Text } from 'react-native';

const EventDetailsScreen = ({ route }) => {
  const { event } = route.params;

  return (
    <View>
      <Text>{event.title}</Text>
      <Text>{event.category}</Text>
      <Text>{event.description}</Text>
    </View>
  );
};

export default EventDetailsScreen;
