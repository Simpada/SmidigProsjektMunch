import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import PlayGameScreen from '../screens/PlayGameScreen';
import GameScreen from '../screens/GameScreen';


const Stack = createStackNavigator();

const PlayNavigator = () => {
  return (
    <NavigationContainer
    independent={true}>
      <Stack.Navigator>
      <Stack.Screen name="PlayGameScreen" component={PlayGameScreen} />
      <Stack.Screen name="Play Game" component={GameScreen} />
</Stack.Navigator>
    </NavigationContainer>
  );
};

export default PlayNavigator;