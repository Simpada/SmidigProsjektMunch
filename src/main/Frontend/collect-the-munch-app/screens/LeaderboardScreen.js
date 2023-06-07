import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';
import WeeklyScreen from '../components/LeaderboardNavComponents/WeeklyScreen';
import MonthlyScreen from '../components/LeaderboardNavComponents/MonthlyScreen';
import AllTimeScreen from '../components/LeaderboardNavComponents/AllTimeScreen';

const Tab = createMaterialTopTabNavigator();

function LeaderboardScreen() {
  return (
    <Tab.Navigator>
      <Tab.Screen name="Weekly" component={WeeklyScreen} />
      <Tab.Screen name="Monthly" component={MonthlyScreen} />
      <Tab.Screen name="All Time" component={AllTimeScreen} />
    </Tab.Navigator>
  );
}

export default LeaderboardScreen;