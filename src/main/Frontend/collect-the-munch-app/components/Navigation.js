import { NavigationContainer, Screen } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { createStackNavigator } from '@react-navigation/stack';
import { Ionicons } from '@expo/vector-icons';

// Import your screens
import HomeScreen from '../screens/HomeScreen';
import PlayGameScreen from '../screens/PlayGameScreen';
import LeaderboardScreen from '../screens/LeaderboardScreen';
import EventsScreen from '../screens/EventsScreen';
import EventsNavigator from './EventsNavigator';
const Tab = createBottomTabNavigator();

const Navigation = () => {
  return (
    <NavigationContainer>
        <Tab.Navigator
          initialRouteName="Home"
          screenOptions={({ route }) => ({
            headerShown: false,
            tabBarIcon: ({ focused, color, size }) => {
              let iconName;
              if (route.name === 'Home') {
                iconName = focused ? 'home' : 'home-outline';
              } else if (route.name === 'Play Game') {
                iconName = focused ? 'game-controller' : 'game-controller-outline';
              } else if (route.name === 'Events') {
                iconName = focused ? 'calendar' : 'calendar-outline';
              } else if (route.name === 'Leaderboard') {
                iconName = focused ? 'trophy' : 'trophy-outline';
              }

              return <Ionicons name={iconName} size={size} color={color} />;
            },
          })}
          tabBarOptions={{
            activeTintColor: '#FE390F',
            inactiveTintColor: 'gray',
          }}
        >
          <Tab.Screen name="Home" component={HomeScreen} />
          <Tab.Screen name="Play Game" component={PlayGameScreen} />
          <Tab.Screen name="Events" component={EventsNavigator} />
          <Tab.Screen name="Leaderboard" component={LeaderboardScreen} />
        </Tab.Navigator>
    </NavigationContainer>
  );
};

export default Navigation;
