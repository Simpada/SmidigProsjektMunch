import React, { useEffect, useState } from 'react';
import { Text, View, StyleSheet, ScrollView, Image } from 'react-native';
import { colors } from '../Styles/theme';

const LeaderboardScreen = () => {
  const [leaderboard, setLeaderBoard] = useState([]);

  useEffect(() => {
    const initialLeaderboard = [
      { id: 1, fullName: 'Lily Pilly', userName: 'lily23', points: 2468, profileImage: require('../assets/Images/lily.jpg') },
      { id: 2, fullName: 'Jackson Hubert', userName: 'Jackson_H', points: 1882, profileImage: require('../assets/Images/profile1.png') },
      { id: 3, fullName: 'Shockdoggo', userName: 'shockdoggo47', points: 1532, profileImage: require('../assets/Images/shockdoggo.jpg') },
      { id: 4, fullName: 'John Smithies', userName: 'JohnSmithies66', points: 1521, profileImage: require('../assets/Images/profile1.png') },
      { id: 5, fullName: 'Arnold Schwarzenegger', userName: 'Arnold', points: 1511, profileImage: require('../assets/Images/profile1.png') },
      { id: 6, fullName: 'Carly Harley', userName: 'iCarly3', points: 1501, profileImage: require('../assets/Images/profile1.png') },
      { id: 7, fullName: 'Jesper Hansen', userName: 'jSper_00', points: 1493, profileImage: require('../assets/Images/profile1.png') },
      { id: 8, fullName: 'Isak Einarsen', userName: 'Izzy', points: 1492, profileImage: require('../assets/Images/profile1.png') },
      { id: 9, fullName: 'Alex Dragon', userName: 'DRAGONALEX44', points: 1451, profileImage: require('../assets/Images/profile1.png') },
      { id: 10, fullName: 'Karen Haren', userName: 'karen_haren', points: 1447, profileImage: require('../assets/Images/profile1.png') },
    ];

    setLeaderBoard(initialLeaderboard);
  }, []);

  return (
    <View style={styles.container}>
      <View style={styles.categorycontainer}>
        <View style={styles.category}>
          <Text style={styles.categoryText}>Weekly</Text>
        </View>
        <View style={styles.category}>
          <Text style={styles.categoryText}>Monthly</Text>
        </View>
        <View style={styles.category}>
          <Text style={styles.categoryText}>All-time</Text>
        </View>
      </View>

      <View style={styles.headerContainer}>
     

<ScrollView horizontal>
  {leaderboard.slice(0, 3).map((item, index) => (
    <View style={styles.winnerContainer} key={item.id}>
      <View style={[
        styles.profileImageContainer,
        index === 0 && styles.gold,
        index === 1 && styles.silver,
        index === 2 && styles.bronze,
      ]}>
        <Image source={item.profileImage} style={styles.profileImage} />
        <View style={styles.circleTopThree}>
          <Text style={styles.circleTopThreeText}>{index + 1}</Text>
        </View>
      </View>
      <Text style={styles.winnerName}>{item.fullName}</Text>
      <Text style={styles.winnerPoints}>{item.points} points</Text>
    </View>
  ))}
</ScrollView>



</View>

      <ScrollView>
        <View style={styles.table}>
        
      
        {leaderboard.slice(3, leaderboard.length).map((item, index) => (
  <View
    style={
      index !== leaderboard.length - 1
        ? styles.tableRowWithBorder
        : styles.tableRow
    }
    key={item.id}
  >
    <View style={[styles.profileImageContainer, styles.flex1]}>
      <Image source={item.profileImage} style={styles.profileImage} />
    </View>
    <View style={[styles.nameContainer, styles.flex2]}>
      <Text style={styles.fullName}>{item.fullName}</Text>
      <Text style={styles.userName}>@{item.userName}</Text>
    </View>
    <View style={[styles.cell, styles.flex1]}>
      <Text style={styles.pointsColor}>{item.points}</Text>
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
  profileImage: {
    width: 70,
    height: 70,
    borderRadius: 35,
  },
  winnerName: {
    color: colors.white,
    fontWeight: 'bold',
    marginTop: 5,
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
    backgroundColor: 'green',
    justifyContent: 'center',
    alignItems: 'center',
    bottom: -5,
    transform: 'rotate(45deg)'
    
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
    backgroundColor: 'brown',
  },
  pointsColor: {
    fontSize:20,
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
