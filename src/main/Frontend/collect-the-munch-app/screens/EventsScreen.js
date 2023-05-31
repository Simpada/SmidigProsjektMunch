import { Text, View, StyleSheet } from 'react-native';
import { useEffect, useState } from 'react';
import EventList from '../components/EventList';
import Header from '../components/Header';
import Filter from '../components/Filter';
import { colors } from '../Styles/theme';

const EventsScreen = () => {
  const [events, setEvents] = useState([]);
  const [filteredEvents, setFilteredEvents] = useState([]);

  useEffect(() => {
    const initialEvents = [
      {id: 1, title: "Unveiling History", category: "historical", description: "Join us on a captivating journey through time as we unveil the mysteries of ancient civilizations."},
      {id: 2, title: "Brushstrokes and Beyond", category: "thematic", description: "Ignite your creativity and delve into the world of art with our Brushstrokes and Beyond workshop."},
      {id: 3, title: "From Curators to Connoisseurs", category: "historical", description: "Embark on a fascinating journey of scientific discovery at our interactive Science Lab Explorations."},
      {id: 4, title: "Tales and Treasures", category: "photography", description: " Gather the whole family for a day of enchantment and exploration at our Tales and Treasures event."},
    ];

    setEvents(initialEvents);
    setFilteredEvents(initialEvents);
  }, []);

  const handleSearch = (text) => {
    const filtered = events.filter((event) =>
      event.title.toLowerCase().includes(text.toLowerCase())
    );
    setFilteredEvents(filtered);
  };

  return (
    <View style={styles.container}>
      <Header style={styles.header} />
      <Filter style={styles.filter} onSearch={handleSearch} />
      <View style={styles.eventTitleContainer}>
        <Text style={styles.eventTitle}>New Events</Text>
      </View>
      <EventList style={styles.list} events={filteredEvents} />
    </View>
  );
};

export default EventsScreen;



const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  eventTitleContainer: {
    width: "100%",
    paddingHorizontal: 20,
  },
  eventTitle: {
    fontSize: 25, 
    fontWeight: 800,
    paddingLeft:10,
    color: colors.red, 
    fontFamily: "GirottMunch-BoldBackslant"
  }
 
});