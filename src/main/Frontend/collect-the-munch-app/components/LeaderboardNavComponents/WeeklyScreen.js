import React, { useState, useEffect } from 'react';
import { View, Text, Button, StyleSheet, FlatList, ActivityIndicator, Image, ScrollView, Alert } from 'react-native';
import axios from 'axios';
import { colors } from '../../Styles/theme';


function WeeklyScreen() {
  const [selectedImage, setSelectedImage] = useState({ uri: 'http://placeholder.com/placeholder.png' });
  const [pointsType] = useState('weeklyPoints');
  const [currentPage, setCurrentPage] = useState(1);
  const [isLoading, setIsLoading] = useState(false);
  const [data, setData] = useState([]);
  const [topThree, setTopThree] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    axios.get(`https://findthemunchgame.azurewebsites.net/api/user?page=${currentPage}`)
    .then(response => {
      const leaderboardData = response.data
        .map(user => ({
          id: user.userId,
          
          userName: user.username,
          points: user[pointsType],
          
        }))
        .sort((a, b) => b.points - a.points)
        .map((item, index) => ({
          ...item,
          profileImage: index < 3 ? item.profileImage : undefined,
        }));

      const topThreeData = [
        leaderboardData[1], // Second becomes first
        leaderboardData[0], // First becomes second
        leaderboardData[2], // Third stays the same
      ];

      setTopThree(topThreeData);
      setData(leaderboardData);
      setIsLoading(false);
    })
    .catch(error => {
      console.error(error);
      Alert.alert('Failed to load data');
      setIsLoading(false);
    });
  }, [currentPage]);

  function loadMore() {
    setCurrentPage(currentPage + 1);
  }

  return (
    <View style={styles.container}>
      <View style={styles.topThreeContainer}>
        <ScrollView horizontal contentContainerStyle={{ justifyContent: 'space-around', flexDirection: 'row' }}>
          {topThree && topThree.map((item, index) => (
            item && // added
            <View style={[styles.winnerContainer, index === 1 && styles.goldMargin]} key={item.id}>
              <View
                style={[
                  styles.profileImageContainer,
                  index === 1 && styles.gold,
                  index === 0 && styles.silver,
                  index === 2 && styles.bronze,
                ]}
              >
               
                <View style={styles.circleTopThree}>
                  <Text style={styles.circleTopThreeText}>{index === 1 ? '1' : (index === 0 ? '2' : '3')}</Text>
                </View>
              </View>
              <Text style={[styles.winnerName, index === 1 && styles.goldText, index === 0 && styles.silverText, index === 2 && styles.bronzeText]}>{item.fullName} 
              {'\n'}
              <Text style={styles.userName}>{item.userName}</Text>
              {'\n'}
              </Text>
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
              data={data.slice(3)}
              keyExtractor={item => item.id.toString()}
              renderItem={({ item, index }) => (
                <View style={styles.item}>
                  <View style={styles.profileImageContainerSmall}>
                    <Image source={item.profileImage ? {uri: item.profileImage} : selectedImage} style={styles.profileImageSmall} />
                    <Text style={styles.numberLabel}>
                      <View style={styles.numberLabelBackground}>
                        <Text style={styles.numberLabelText}>{index + 4}</Text>
                      </View>
                    </Text>
                  </View>
                  <View style={styles.userInfoContainer}>
                    <Text style={styles.listText}>
                      {item.fullName}
                      {'\n'}
                      <Text style={styles.userName}>{item.userName}</Text>
                    </Text>
                  </View>
                  <Text style={styles.listText}>{item.points} points</Text>
                </View>
              )}
             // onEndReached={loadMore}
             // onEndReachedThreshold={0}
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
      height: 220,
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
    transform: "rotate(45deg)",
    borderWidth: 1.5,
    },
    circleTopThreeText: {
      position: 'absolute',
      transform: "rotate(-45deg)",
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
      padding: 10,
      borderBottomWidth: 0.5,
      borderBottomColor: '#d6d7da',
    },
    listText: {
      color: colors.white,
      fontSize: 16,
      },

    userInfoContainer: {
      flex: 1,
    },
    profileImageContainerSmall: {
      borderRadius: 40,
      backgroundColor: 'white',
      justifyContent: 'center',
      alignItems: 'center',
      marginRight: 10,
      position:'relative',
      overflow: '',
    },
    numberLabel: {
      position: 'absolute',
      left: 16,
      top: 33,
    },
    numberLabelBackground: {
      backgroundColor: 'white',
      width: 16,
      height: 16,
      borderRadius: 2,
      justifyContent: 'center',
      alignItems: 'center',
      transform: "rotate(45deg)",
      borderWidth: 1.5,
      position: 'absolute',
    },
    numberLabelText: {
      transform: "rotate(-45deg)",
      fontWeight: 'bold',
      fontSize:'9px',
      color: colors.navy,
    },
    profileImageSmall: {
      width: 50,
      height: 50,
      borderRadius: 25,
    },
    fullName: {
      fontSize:20,
      color: 'white',
      fontWeight: 'bold',
    },
    userName: {
      alignItems:'center',
      fontSize: 10,
      color: 'grey',
    }
});

export default WeeklyScreen;
