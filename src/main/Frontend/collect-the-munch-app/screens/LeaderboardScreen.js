import React, { useEffect, useState } from 'react';
import { Text, View, StyleSheet, ScrollView, Image, TouchableOpacity } from 'react-native';
import axios from 'axios';
import { colors } from '../Styles/theme';

const defaultProfileImage = require('../assets/Images/profile1.png');

const LeaderboardScreen = () => {
  const [leaderboard, setLeaderBoard] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('Monthly');

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get('https://findthemunchgame.azurewebsites.net/api/user');
        const data = response.data;
        console.log(data);
        setLeaderBoard(data);
      } catch (error) {
        console.error('Error fetching leaderboard:', error);
      }
    };
    fetchUsers();
  }, []);

  // Sort the leaderboard in descending order based on the selected category
  const sortLeaderboard = () => {
    let sortedLeaderboard = [];
    switch (selectedCategory) {
      case 'Weekly':
        sortedLeaderboard = [...leaderboard].sort((a, b) => b.weeklyPoints - a.weeklyPoints);
        break;
      case 'Monthly':
        sortedLeaderboard = [...leaderboard].sort((a, b) => b.monthlyPoints - a.monthlyPoints);
        break;
      case 'All Time':
        sortedLeaderboard = [...leaderboard].sort((a, b) => b.allTimePoints - a.allTimePoints);
        break;
      default:
        sortedLeaderboard = [...leaderboard];
    }
    return sortedLeaderboard;
  };

  const handleCategoryPress = (category) => {
    setSelectedCategory(category);
  };

  return (
    <View style={styles.container}>
      <View style={styles.categorycontainer}>
        <TouchableOpacity
          style={styles.category}
          onPress={() => handleCategoryPress('Monthly')}
        >
          <Text style={[styles.categoryText, selectedCategory === 'Monthly' && styles.selectedCategory]}>
            Monthly
          </Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.category}
          onPress={() => handleCategoryPress('Weekly')}
        >
          <Text style={[styles.categoryText, selectedCategory === 'Weekly' && styles.selectedCategory]}>
            Weekly
          </Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.category}
          onPress={() => handleCategoryPress('All Time')}
        >
          <Text style={[styles.categoryText, selectedCategory === 'All Time' && styles.selectedCategory]}>
            All Time
          </Text>
        </TouchableOpacity>
      </View>

      <View style={styles.headerContainer}>
        <ScrollView horizontal>
          {sortLeaderboard().slice(0, 3).map((item, index) => (
            <View style={styles.winnerContainer} key={item.id}>
              <View style={[
                styles.profileImageContainer,
                index === 0 && styles.gold,
                index === 1 && styles.silver,
                index === 2 && styles.bronze,
              ]}>
                <Image
                  source={item.profileImage || defaultProfileImage}
                  style={styles.profileImage}
                />
                <View style={styles.circleTopThree}>
                  <Text style={styles.circleTopThreeText}>{index + 1}</Text>
                </View>
              </View>
              <Text style={styles.winnerName}>{item.username}</Text>
              <Text style={styles.winnerPoints}>{item[selectedCategory.toLowerCase() + 'Points']} points</Text>
            </View>
          ))}
        </ScrollView>
      </View>

      <ScrollView>
        <View style={styles.table}>
          {sortLeaderboard().slice(3, leaderboard.length).map((item, index) => (
            <View
              style={
                index !== leaderboard.length - 1
                  ? styles.tableRowWithBorder
                  : styles.tableRow
              }
              key={item.id}
            >
              <View style={[styles.flex1, styles.imageContainer]}>
                <Image
                  source={item.profileImage || defaultProfileImage}
                  style={styles.profileImageRest}
                />
              </View>
              <View style={[styles.nameContainer, styles.flex2]}>
                <Text style={styles.fullName}>{item.username}</Text>
                <Text style={styles.userName}>@{item.username}</Text>
              </View>
              <View style={[styles.cell, styles.flex1]}>
                <Text style={styles.pointsColor}>{item[selectedCategory.toLowerCase() + 'Points']}</Text>
                <Text style={styles.pointsColor}> pts</Text>
              </View>
            </View>
          ))}
        </View>
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingHorizontal: 20,
    backgroundColor: colors.navy,
  },
  nameContainer: {
    flex: 2,
    flexDirection: 'column',
    marginLeft: 10,
  },
  fullName: {
    fontSize: 16,
    fontWeight: 'bold',
    color: colors.white,
    fontFamily: 'GirottMunch-Bold',
  },
  userName: {
    fontSize: 12,
    color: 'gray',
    fontFamily: 'GirottMunch-Bold',
    opacity: 0.9
  },
  
  categorycontainer: {
    backgroundColor: colors.black,
    width: '100%',
    marginVertical: 30,
    padding: 10,
    borderRadius: 20,
    justifyContent: 'center',
    flexDirection: 'row',
  },
  category: {
    padding: 5,
  },
  categoryText: {
    fontFamily: 'GirottMunch-Bold',
    fontWeight: 'bold',
    color: colors.white,
  },
  selectedCategory: {
    textDecorationLine: 'underline',
  },
  headerContainer: {
    marginTop: 0,
    marginBottom: 20,
    alignItems: 'center',
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
  imageContainer: {
    alignItems:"center"
  },
  profileImage: {
    width: 70,
    height: 70,
    borderRadius: 35,
  },
  profileImageRest: {
    width: 55,
    height: 55,
    borderRadius: 27.5,
  },
  winnerName: {
    color: colors.white,
    fontWeight: 'bold',
    marginTop: 20,
    fontFamily: 'GirottMunch-Bold',
  },
  winnerPoints: {
    color: colors.white,
    fontWeight: 'bold',
    fontFamily: 'GirottMunch-Bold',
  },
  table: {
    overflow: "hidden",
    borderWidth:3,
    borderColor: colors.darkNavy,
    marginVertical: 20,
    borderRadius: 20,
  },
  tableHeader: {
    flexDirection: 'row',
    backgroundColor: colors.navy,
    padding: 10,
  },
  headerIdText: {
    flex: 1,
    color: colors.white,
    fontWeight: 'bold',
    fontFamily: 'GirottMunch-Bold',
  },
  headerText: {
    flex: 2,
    fontFamily: 'GirottMunch-Bold',
    fontSize: 18,
    color: colors.white,
    fontWeight: 'bold',
  },
  tableRow: {
    flexDirection: 'row',
    padding: 10,
    alignItems: 'center',
  },
  tableRowWithBorder: {
    flexDirection: 'row',
    borderBottomWidth: 1,
    borderColor: 'gray',
    padding: 10,
    alignItems: 'center',
  },
  circle: {
    width: 30,
    height: 30,
    borderRadius: 15,
    backgroundColor: 'white',
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 10,
    
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
    transform: 'rotate(45deg)',
    borderWidth: 1.5
    
  },
  circleTopThreeText: {
    position: 'absolute',
    transform: 'rotate(-45deg)',
    fontWeight: 'bold',
    fontFamily: 'GirottMunch-Bold',
  },
  circleText: {
    fontWeight: 'bold',
    fontFamily: 'GirottMunch-Bold',
  },
  cell: {
    flexDirection: "row",
    flex: 2,
    fontSize: 16,
    fontWeight: 'bold',
    textAlign: 'center',
    color: colors.white,
    fontFamily: 'GirottMunch-Bold',
  },
  gold: {
    backgroundColor: 'gold',
  },
  silver: {
    backgroundColor: 'silver',
  },
  bronze: {
    backgroundColor: '#946110',
  },
  pointsColor: {
    fontSize: 13,
    color:"white"
  },
  flex1: {
    flex:1
  },
  flex2: {
    flex:2
  }
});

export default LeaderboardScreen;
