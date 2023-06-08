import * as React from 'react';
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';
import { View } from 'react-native';
import { colors } from '../Styles/theme';
import WeeklyScreen from '../components/LeaderboardNavComponents/WeeklyScreen';
import MonthlyScreen from '../components/LeaderboardNavComponents/MonthlyScreen';
import AllTimeScreen from '../components/LeaderboardNavComponents/AllTimeScreen';

const Tab = createMaterialTopTabNavigator();

function LeaderboardScreen() {
  return (
    <View style={{ flex: 1 }}>
      <Tab.Navigator
        tabBarOptions={{
          activeTintColor: colors.red,
          inactiveTintColor: colors.grey,
          style: { backgroundColor: colors.white },
          indicatorStyle: { backgroundColor: colors.red },
        }}
      >
        <Tab.Screen name="Latest Score" component={WeeklyScreen} />
        <Tab.Screen name="Monthly" component={MonthlyScreen} />
        <Tab.Screen name="All Time" component={AllTimeScreen} />
      </Tab.Navigator>
    </View>
  );
}

export default LeaderboardScreen;