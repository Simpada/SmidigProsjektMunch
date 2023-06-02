import { createStackNavigator } from '@react-navigation/stack';
import EventsScreen from '../screens/EventsScreen';
import EventDetailsScreen from '../screens/EventDetailsScreen';
import { NavigationContainer } from '@react-navigation/native';

const Stack = createStackNavigator();

const EventsNavigator = () => {
  return (
    <NavigationContainer 
        independent={true}>

      <Stack.Navigator>
        <Stack.Screen name='Events' component={EventsScreen} options={{headerShown: false}}/>
        <Stack.Screen name='Event Details' component={EventDetailsScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default EventsNavigator;
