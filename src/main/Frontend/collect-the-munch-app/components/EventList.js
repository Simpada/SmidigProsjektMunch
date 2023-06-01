import React from 'react';
import { FlatList, View, StyleSheet } from 'react-native';
import EventItem from './EventItem';

const EventList = ({ events }) => {
  return (
    <View style={styles.container}>
      <FlatList
        data={events}
        alwaysBounceVertical={false}
        renderItem={({ item }) => (
          <EventItem
            id={item.id}
            description={item.description}
            title={item.title}
            category={item.category}
          />
        )}
        keyExtractor={(itemData) => itemData.id.toString()}
      />
    </View>
  );
};

export default EventList;

const styles = StyleSheet.create({
    container: {
        flex:1,
        width: "100%",
        padding: 20
    }
})
