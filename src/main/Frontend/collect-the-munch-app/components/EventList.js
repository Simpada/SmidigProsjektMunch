// EventList.js

import React from 'react';
import { FlatList, View, StyleSheet, TouchableOpacity } from 'react-native';
import EventItem from '../assets/Images/PlaceholderImage.jpg';

const EventList = ({ events, handleEventPress }) => {
  return (
    <View style={styles.container}>
      <FlatList
        data={events}
        alwaysBounceVertical={false}
        renderItem={({ item }) => (
          <TouchableOpacity onPress={() => handleEventPress(item)}>
            <EventItem
              id={item.id}
              description={item.description}
              title={item.title}
              category={item.category}
            />
          </TouchableOpacity>
        )}
        keyExtractor={(itemData) => itemData.id.toString()}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    width: '100%',
    padding: 20,
  },
});

export default EventList;

