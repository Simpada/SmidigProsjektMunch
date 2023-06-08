import React from 'react';
import { View, TouchableOpacity, Text } from 'react-native';
import { useNavigation } from '@react-navigation/native';

const NavigationComponent = () => {
  const navigation = useNavigation();

  const navigateToCreateTeam = () => {
    navigation.navigate('CreateTeam');
  };

  return (
    <View>
      <TouchableOpacity onPress={navigateToCreateTeam}>
        <Text>Create a Party</Text>
      </TouchableOpacity>
    </View>
  );
};

export default NavigationComponent;
