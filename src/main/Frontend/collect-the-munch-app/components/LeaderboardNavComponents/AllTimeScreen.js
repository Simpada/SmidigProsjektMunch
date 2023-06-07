import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet, FlatList, Button, ActivityIndicator } from 'react-native';
import axios from 'axios';

function AllTimeScreen() {
  // Add more dummy data as needed to fill the page
  const dummyData = [
    { id: '1', username: 'User 1', points: 100 },
    { id: '2', username: 'User 2', points: 90 },
    { id: '3', username: 'User 3', points: 100 },
    { id: '4', username: 'User 4', points: 90 },
    { id: '5', username: 'User 5', points: 100 },
    { id: '6', username: 'User 6', points: 90 },
    { id: '7', username: 'User 7', points: 100 },
    { id: '8', username: 'User 8', points: 90 },
    { id: '9', username: 'User 9', points: 100 },
    { id: '10', username: 'User 10', points: 90 },
    { id: '11', username: 'User 11', points: 100 },
    { id: '12', username: 'User 12', points: 90 },
    { id: '50', username: 'User 50', points: 1 },
  ];

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 10;
  const [isLoading, setIsLoading] = useState(false);

  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = dummyData.slice(indexOfFirstItem, indexOfLastItem);

  useEffect(() => {
    setIsLoading(true);
    // Uncomment this block when your API is ready
    /*
    axios.get(`https://your-api-url/All-time?page=${currentPage}`)
      .then(response => {
        setData(response.data);
        setIsLoading(false);
      })
      .catch(error => {
        console.error(error);
        setIsLoading(false);
      });
    */
    setIsLoading(false);
  }, [currentPage]);

  return (
    <View style={styles.container}>
      <Text>Latest Event</Text>
      {isLoading ? (
        <ActivityIndicator size="large" color="#0000ff" />
      ) : (
        <FlatList
          data={currentItems}
          keyExtractor={item => item.id.toString()}
          renderItem={({ item, index }) => (
            <View style={styles.item}>
              <Text>{index + 1}</Text>
              <Text>{item.username}</Text>
              <Text>{item.points} points</Text>
            </View>
          )}
        />
      )}
      <View style={styles.pagination}>
        <Button
          title="Previous"
          onPress={() => setCurrentPage(Math.max(1, currentPage - 1))}
          disabled={currentPage === 1}
        />
        <Button
          title="Next"
          onPress={() => setCurrentPage(currentPage + 1)}
          disabled={currentPage * itemsPerPage >= dummyData.length}
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20,
  },
  item: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: 10,
    borderBottomColor: '#ccc',
    borderBottomWidth: 1,
  },
  pagination: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    padding: 10,
  },
});

export default AllTimeScreen;

