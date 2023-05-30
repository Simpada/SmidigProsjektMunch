import React from 'react'
import { FlatList, View, StyleSheet } from 'react-native'
import EventItem from './EventItem'

const EventList = ({events}) => {
  return (
    <View style={styles.container}>
        <FlatList 
            data={events}
            alwaysBounceVertical={false}
            renderItem={itemData => (
                <EventItem 
                    id={itemData.item.id} 
                    description={itemData.item.description}
                    title={itemData.item.title}
                    category={itemData.item.category}
                />
            )}/>
    </View>
  )
}

export default EventList

const styles = StyleSheet.create({
    container: {
        flex:1,
        width: "100%",
        padding: 20
    }
})
