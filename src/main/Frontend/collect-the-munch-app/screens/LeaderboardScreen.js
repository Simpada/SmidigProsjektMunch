import React, { useEffect, useState } from 'react';
import { Text, View, StyleSheet, ScrollView, Image } from 'react-native';
import { colors } from '../Styles/theme';

const LeaderboardScreen = () => {
  const [leaderboard, setLeaderBoard] = useState([]);

  useEffect(() => {
    const initialLeaderboard = [
      { id: 1, fullName: 'Jonas Andersen', userName: 'Jønna', points: 2000, profileImage: require('../assets/profile1.png') },
      { id: 2, fullName: 'Sander Sanstrød', userName: 'Sand', points: 1800, profileImage: require('../assets/profile2.png') },
      { id: 3, fullName: 'Samuel something', userName: 'The', points: 1500, profileImage: require('../assets/profile3.png') },
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
        <Text style={styles.headerText}>Top 3 Winners</Text>
        <ScrollView horizontal>
          {leaderboard.slice(0, 3).map((item) => (
            <View style={styles.winnerContainer} key={item.id}>
              <View style={styles.profileImageContainer}>
                <Image source={item.profileImage} style={styles.profileImage} />
              </View>
              <Text style={styles.winnerName}>{item.fullName}</Text>
              <Text style={styles.winnerPoints}>{item.points} points</Text>
            </View>
          ))}
        </ScrollView>
      </View>

      <ScrollView>
        <View style={styles.table}>
          <View style={styles.tableHeader}>
            <Text style={styles.headerIdText}>ID</Text>
            <Text style={styles.headerText}>Full Name</Text>
            <Text style={styles.headerText}>Name</Text>
            <Text style={styles.headerText}>Points</Text>
          </View>
          {leaderboard.map((item, index) => (
            <View
              style={[
                styles.tableRow,
                index === 0 && styles.topOneRow,
                index === 1 && styles.topTwoRow,
                index === 2 && styles.topThreeRow,
              ]}
              key={item.id}
            >
              <View style={styles.circle}>
                <Text style={styles.circleText}>{item.id}</Text>
              </View>
              <Text style={styles.cell}>{item.fullName}</Text>
              <Text style={styles.cell}>{item.userName}</Text>
              <Text style={styles.cell}>{item.points}</Text>
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
    fontWeight: 'bold',
    color: 'white',
  },
  headerContainer: {
    marginTop: 20,
    marginBottom: 10,
    alignItems: 'center',
  },
  headerText: {
    fontSize: 18,
    fontWeight: 'bold',
    color: colors.white,
    marginBottom: 10,
  },
  winnerContainer: {
    alignItems: 'center',
    marginRight: 10,
  },
  profileImageContainer: {
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
  },
  winnerPoints: {
    color: colors.white,
    fontWeight: 'bold',
  },
  table: {
    borderColor: 'black',
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
  },
  headerText: {
    flex: 2,
    fontSize: 18,
    color: colors.white,
    fontWeight: 'bold',
  },
  tableRow: {
    flexDirection: 'row',
    borderBottomWidth: 1,
    borderColor: 'gray',
    padding: 10,
    alignItems: 'center',
  },
  topOneRow: {
    backgroundColor: 'green',
    color: colors.white,
  },
  topTwoRow: {
    backgroundColor: 'yellow',
    color: colors.white,
  },
  topThreeRow: {
    backgroundColor: 'orange',
    color: colors.white,
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
  circleText: {
    fontWeight: 'bold',
  },
  cell: {
    flex: 2,
    fontSize: 16,
    fontWeight: 'bold',
    textAlign: 'center',
  },
});

export default LeaderboardScreen;
