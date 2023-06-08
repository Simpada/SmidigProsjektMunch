import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import CreateTeamScreen from './screens/CreateTeam';
import PlayGameScreen from './screens/PlayGameScreen';

const Stack = createStackNavigator();

const AppNavigator = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="PlayGameScreen" component={PlayGameScreen} />
        <Stack.Screen name="CreateTeam" component={CreateTeamScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default AppNavigator;
