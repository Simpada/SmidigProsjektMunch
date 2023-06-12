import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Ionicons } from '@expo/vector-icons';

// Import your screens
import HomeScreen from '../screens/HomeScreen';
import PlayGameScreen from '../screens/PlayGameScreen';
import LeaderboardScreen from '../screens/LeaderboardScreen';
import GameScreen from '../screens/GameScreen';
import EventsNavigator from './EventsNavigator';
import CreateTeamNavigator from './CreateTeamNavigator';
import ProfileScreen from '../screens/ProfileScreen';
import { colors } from '../Styles/theme';

const Tab = createBottomTabNavigator();

const Navigation = () => {
  return (
    <NavigationContainer>
      <Tab.Navigator
        initialRouteName="Home"
        screenOptions={({ route }) => ({
          tabBarActiveTintColor: colors.red,
          tabBarInactiveTintColor: colors.grey,
          tabBarIcon: ({ focused, color, size }) => {
            let iconName;

            if (route.name === 'Home') {
              iconName = focused ? 'home' : 'home-outline';
            } else if (route.name === 'Game Info') {
              iconName = focused ? 'information-circle' : 'information-circle-outline';
            } else if (route.name === 'Play Game') {
              iconName = focused ? 'camera' : 'camera-outline';
            } else if (route.name === 'Events') {
              iconName = focused ? 'calendar' : 'calendar-outline';
            } else if (route.name === 'Leaderboard') {
              iconName = focused ? 'trophy' : 'trophy-outline';
            } else if (route.name === 'Profile') {
              iconName = focused ? 'person-circle' : 'person-circle-outline';
            }

            return <Ionicons name={iconName} size={size} color={color} />;
          },

        })}

      >
        <Tab.Screen name="Home" component={HomeScreen} options={{headerShown: false}}/>
        <Tab.Screen name="Game Info" component={CreateTeamNavigator} options={{headerShown: false}}/>
        <Tab.Screen name="Play Game" component={GameScreen} options={{headerShown: false}}/>
        <Tab.Screen name="Events" component={EventsNavigator} options={{headerShown: false}}/>
        <Tab.Screen name="Leaderboard" component={LeaderboardScreen} options={{headerShown: false}}/>
        <Tab.Screen name="Profile" component={ProfileScreen} options={{headerShown: false}}/>
      </Tab.Navigator>
    </NavigationContainer>
  );
};

export default Navigation;
