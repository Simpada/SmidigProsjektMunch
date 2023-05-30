import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

const Tab = createBottomTabNavigator();


const Navigation = () => {
  return (
    <NavigationContainer>
          <Tab.Navigator
            initialRouteName='Home'
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
            <Tab.Screen name="Events" component={EventsScreen} />
            <Tab.Screen name="Leaderboard" component={LeaderboardScreen} />
          </Tab.Navigator>
        </NavigationContainer>
  )
}

export default Navigation