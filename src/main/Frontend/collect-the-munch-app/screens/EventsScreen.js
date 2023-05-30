import { Text, View, StyleSheet } from 'react-native'
import { useState } from 'react';
import EventList from '../components/EventList';
import Header from '../components/Header';
import Filter from '../components/Filter';
const EventsScreen = () => {

  const [events, setEvents] = useState([
    { id: 1, title: "Unveiling History", description: "Join us on a captivating journey through time as we unveil the mysteries of ancient civilizations. ", category: "historical "},
    { id: 2, title: "Brushstrokes and Beyond", description: "Ignite your creativity and delve into the world of art with our Brushstrokes and Beyond workshop.", category: "historical "},
    { id: 3, title: "From Curators to Connoisseurs", description: "Explore the intricacies of art appreciation, from understanding symbolism and artistic intent to analyzing diverse art movements and styles", category: "photography "},
    { id: 4, title: "Science Lab Explorations", description: "Embark on a fascinating journey of scientific discovery at our interactive Science Lab Explorations.", category: "historical "},
    { id: 5, title: "Tales and Treasures", description: "Gather the whole family for a day of enchantment and exploration at our Tales and Treasures event.", category: "photography "},
])

    return (
      <View style={styles.container}>
        <Header style={styles.header} />
        <Filter style={styles.filter} />
        <EventList style={styles.list} events={events} />
      </View>
    )
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
 
});
export default EventsScreen