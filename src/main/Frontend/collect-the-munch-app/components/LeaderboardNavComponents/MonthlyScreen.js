import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet, FlatList, ActivityIndicator, Image, ScrollView } from 'react-native';
import axios from 'axios';
import { colors } from '../../Styles/theme';

function MonthlyScreen() {
  const initialLeaderboard = [
    { id: 1, fullName: 'Lily Pilly', userName: 'lily23', points: 2700, profileImage: require('../../assets/Images/Dalle.png') },
    { id: 2, fullName: 'Jackson Hubert', userName: 'Jackson_H', points: 2882, profileImage: require('../../assets/Images/Dalle.png') },
    { id: 3, fullName: 'Shockdoggo', userName: 'shockdoggo47', points: 3000, profileImage: require('../../assets/Images/Dalle.png') },
    { id: 4, fullName: 'User 4', userName: 'User 4', points: 385 },
    { id: 5, fullName: 'User 5', userName: 'User 5', points: 792 },
    { id: 6, fullName: 'User 6', userName: 'User 6', points: 492 },
    { id: 7, fullName: 'User 7', userName: 'User 7', points: 347 },
    { id: 8, fullName: 'User 8', userName: 'User 8', points: 190 },
    { id: 9, fullName: 'User 9', userName: 'User 9', points: 230 },
    { id: 10, fullName: 'User 10', userName: 'User 10', points: 1057 },
    { id: 11, fullName: 'User 11', userName: 'User 11', points: 670 },
    { id: 12, fullName: 'User 12', userName: 'User 12', points: 980 },
    { id: 13, fullName: 'User 50', userName: 'User 50', points: 1 },
    { id: 14, fullName: 'User 29', userName: 'User 29', points: 930 },
    { id: 15, fullName: 'User 57', userName: 'User 57', points: 200 },
    { id: 16, fullName: 'User 72', userName: 'User 72', points: 390 },
    { id: 17, fullName: 'User 62', userName: 'User 62', points: 360 },
    { id: 18, fullName: 'User 10', userName: 'User 10', points: 682 },
    { id: 19, fullName: 'User 11', userName: 'User 11', points: 600 },
    { id: 20, fullName: 'User 12', userName: 'User 12', points: 60 },
    { id: 21, fullName: 'Number one', userName: 'User 50', points: 1053 },
  ]
  .sort((a, b) => b.points - a.points)  
  .map((item, index) => ({
    ...item,
    profileImage: index < 3 ? item.profileImage : undefined,
  }));

  let topThree = initialLeaderboard.slice(0, 3);

  topThree = [
    topThree[1], // Second becomes first
    topThree[0], // First becomes second
    topThree[2], // Third stays the same
  ];

  const [currentPage] = useState(1);
  const [isLoading, setIsLoading] = useState(false);
  const [data, setData] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    // Uncomment this block when API is ready
    /*
    axios.get(`https://your-api-url/Monthly?page=${currentPage}`)
      .then(response => {
        setData(response.data);
        setIsLoading(false);
      })
      .catch(error => {
        console.error(error);
        setIsLoading(false);
      });
    */
    setIsLoading(false);
  }, [currentPage]);

  return (
    <View style={styles.container}>
      <View style={styles.topThreeContainer}>
        <ScrollView horizontal contentContainerStyle={{ justifyContent: 'space-around', flexDirection: 'row' }}>
          {topThree.map((item, index) => (
            <View style={[styles.winnerContainer, index === 1 && styles.goldMargin]} key={item.id}>
              <View
                style={[
                  styles.profileImageContainer,
                  index === 1 && styles.gold,
                  index === 0 && styles.silver,
                  index === 2 && styles.bronze,
                ]}
              >
                <Image source={item.profileImage} style={styles.profileImage} />
                <View style={styles.circleTopThree}>
                  <Text style={styles.circleTopThreeText}>{index === 1 ? '1' : (index === 0 ? '2' : '3')}</Text>
                </View>
              </View>
              <Text style={[styles.winnerName, index === 1 && styles.goldText, index === 0 && styles.silverText, index === 2 && styles.bronzeText]}>{item.fullName}</Text>
              <Text style={[styles.winnerPoints, index === 1 && styles.goldText, index === 0 && styles.silverText, index === 2 && styles.bronzeText]}>{item.points} points</Text>
            </View>
          ))}
        </ScrollView>
      </View>
      <View style={styles.listContainer}>
        {isLoading ? (
          <ActivityIndicator size="large" color="#0000ff" />
        ) : (
          <FlatList
            data={initialLeaderboard.slice(3)}
            keyExtractor={item => item.id.toString()}
            renderItem={({ item, index }) => (
              <View style={styles.item}>
                <Text style={styles.listText}>{index + 4}</Text>
                <Text style={styles.listText}>{item.fullName}</Text>
                {/* <Text style={styles.listText}>{item.userName}</Text> */}
                <Text style={styles.listText}>{item.points} points</Text>
              </View>
            )}
          />
        )}
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
container: {
flex: 1,
justifyContent: 'center',
padding: 20,
backgroundColor: colors.navy,
},
  topThreeContainer: {
    height: 200,
    alignItems:'center',
  },
  listContainer: {
    flex: 1,
  },
winnerContainer: {
alignItems: 'center',
marginRight: 10,
},
profileImageContainer: {
position: 'relative',
width: 80,
height: 80,
borderRadius: 40,
backgroundColor: 'white',
justifyContent: 'center',
alignItems: 'center',
},
profileImage: {
width: 70,
height: 70,
borderRadius: 35,
},
winnerName: {
  fontWeight: 'bold',
  marginTop: 20,
},
winnerPoints: {
  fontWeight: 'bold',
},
goldText: {
  color: 'gold',
},
silverText: {
  color: 'silver',
},
bronzeText: {
  color: '#946110',
},
circleTopThree: {
position: 'absolute',
width: 20,
height: 20,
borderRadius: 2,
backgroundColor: 'white',
justifyContent: 'center',
alignItems: 'center',
bottom: -5,
transform: [{ rotate: '45deg' }],
borderWidth: 1.5,
},
circleTopThreeText: {
position: 'absolute',
transform: [{ rotate: '-45deg' }],
fontWeight: 'bold',
color:colors.navy,
},
gold: {
backgroundColor: 'gold',
marginTop:0,
},
silver: {
backgroundColor: 'silver',
marginTop:45,
},
bronze: {
backgroundColor: '#946110',
marginTop:45,
},
item: {
flexDirection: 'row',
justifyContent: 'space-between',
alignItems: 'center',
padding: 24,
borderBottomColor: '#ccc',
borderBottomWidth: 1,
},
pagination: {
flexDirection: 'row',
justifyContent: 'space-between',
padding: 10,
},
listText: {
  color: colors.white,
},

});

export default MonthlyScreen;
