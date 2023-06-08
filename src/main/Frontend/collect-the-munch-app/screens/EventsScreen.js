import React, { useEffect, useState } from 'react';
import { Text, View, StyleSheet } from 'react-native';
import EventList from '../components/EventList';
import Header from '../components/Header';
import Filter from '../components/Filter';
import { colors } from '../Styles/theme';
import { useNavigation } from '@react-navigation/native';
import Review from '../components/Review'
import axios from 'axios';

const EventsScreen = () => {
  const navigation = useNavigation();
  const [events, setEvents] = useState([]);
  const [filteredEvents, setFilteredEvents] = useState([]);

  const handleEventPress = (event) => {
    navigation.navigate('Event Details', { event });
  };

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const response = await axios.get('https://findthemunchgame.azurewebsites.net/api/events');
        const data = response.data;
        console.log(data)
        setEvents(data);
        
        setFilteredEvents(data);
      } catch (error) {
        console.error('Error fetching events:', error);
      }
    };
    fetchEvents();
    
  }, []);

  const handleSearch = (text) => {
    const filtered = events.filter((event) =>
      event.name.toLowerCase().includes(text.toLowerCase())
    );
    setFilteredEvents(filtered);
  };

  return (
    <View style={styles.container}>
      <Filter style={styles.filter} onSearch={handleSearch} />
      <View style={styles.eventsContainer}>
        <View style={styles.eventTitleContainer}>
          <Text style={styles.eventTitle}>New Events</Text>
        </View>

        <EventList 
          style={styles.list}
          events={filteredEvents}
          handleEventPress={handleEventPress}
        />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  eventTitleContainer: {
    width: '100%',
    paddingHorizontal: 20,
  },
  eventTitle: {
    fontSize: 25,
    fontWeight: '800',
    paddingLeft: 10,
    color: colors.red,
    fontFamily: 'GirottMunch-BoldBackslant',
  },
  filter: {
    flex: 1,
  },
  eventsContainer: {
    flex: 4,
  },
});

export default EventsScreen;

    
    /*
        const postEvents = async () => {
          try {
            const eventsToPost = [
              {
                description:
                  'Discover the mysteries of ancient civilizations through a captivating exhibition showcasing rare artifacts and their historical significance.',
                eventPoster: [],
                id: 6,
                name: 'Ancient Artifacts Exhibition',
              },
              {
                description:
                  'Embark on a hands-on journey of scientific discovery at our interactive science lab, where you can conduct experiments and learn about the wonders of the natural world.',
                eventPoster: [],
                id: 7,
                name: 'Interactive Science Lab',
              },
              {
                description:
                  'Unleash your creativity and learn the art of painting landscapes with our skilled artists. Join us for an immersive workshop that will bring out the artist in you.',
                eventPoster: [],
                id: 8,
                name: 'Art Workshop: Painting Landscapes',
              },
            ];
            const postRequests = eventsToPost.map((event) =>
            axios.post('https://findthemunchgame.azurewebsites.net/api/events', event)
            );
            
            await Promise.all(postRequests);
            
            console.log('Events posted successfully');
          } catch (error) {
            console.error('Error posting events:', error);
          }
        };
        
    */
    
    //postEvents();