import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import PlayGameScreen from '../screens/PlayGameScreen';
import CreateTeamScreen from '../screens/CreateTeamScreen';
import GameScreen from '../screens/GameScreen';


const Stack = createStackNavigator();

const CreateTeamNavigator = () => {
  return (
    <NavigationContainer
    independent={true}>
      <Stack.Navigator>
      <Stack.Screen name="PlayGameScreen" component={PlayGameScreen} options={{headerShown: false}}/>
      <Stack.Screen name="CreateTeamScreen" component={CreateTeamScreen} />
      <Stack.Screen name="Play Game" component={GameScreen} />
</Stack.Navigator>
    </NavigationContainer>
  );
};

export default CreateTeamNavigator;



