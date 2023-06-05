import { useEffect, useState } from 'react';
import { Text, View, StyleSheet, ScrollView, Pressable, Alert } from 'react-native'
import { colors } from '../Styles/theme';
const LeaderboardScreen = () => {

  const [leaderboard, setLeaderBoard] = useState([])

  useEffect(() => {
      const initialLeaderboard = [
        {id: 1, fullName:"Jonas Andersen", userName: "Jønna", points: 2000 },
        {id: 2, fullName:"Sander Sanstrød", userName: "Sand", points: 1800 },
        {id: 3, fullName:"Samuel something", userName: "The", points: 1500 },
      ]

      setLeaderBoard(initialLeaderboard)
  }, [])



    return  (
      <View style={styles.container}>
        <View style={styles.categorycontainer}>
          <TouchableOpacity style={styles.category}
          >
            <Text style={styles.categoryText}>Weekly</Text>
          </TouchableOpacity>
          <Pressable style={styles.category}>
            <Text style={styles.categoryText}>Monthly</Text>
          </Pressable>
          <Pressable style={styles.category}>
            <Text style={styles.categoryText}>All-time</Text>
          </Pressable>
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
            style={
              [
                styles.tableRow, index <= 0 && styles.topOneRow,
                styles.tableRow, index <= 1 && index >=1 && styles.topTwoRow,
                styles.tableRow, index <= 2 && index >=2 && styles.topThreeRow,
                styles.tableRow, index > 2 && styles.restPos,

              ]
            }
            key={item.id}
          >
            <Text style={styles.idCell}>{item.id}</Text>
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
    paddingHorizontal: 20,
    backgroundColor: colors.navy
  },
   categorycontainer:{
    backgroundColor: colors.black,
    width: "100%",
    marginVertical: 30,
    padding: 10,
    borderRadius: 20,
    justifyContent:"center",
    gap:50,
    flexDirection:"row"
  },
  category:{
    padding: 5,
  },

  categoryText: {
    fontWeight: "bold",
    color: "white"
  },
  table: {
    borderColor: 'black',
  },
  tableHeader: {
    flexDirection: 'row',
    backgroundColor: colors.navy,
    padding: 10,
  },
  headerIdText:{
    flex:1,
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
  },
  topOneRow: {
    backgroundColor: "green",
    color: colors.white 
  },
  topTwoRow: {
    backgroundColor: "yellow",
    color: colors.white 
  },
  topThreeRow:{
    backgroundColor: "orange",
    color: colors.white 
  },
  restPos: {
    backgroundColor: "gray"
  },
  idCell:{
    flex: 1,
    fontSize: 16, 
    fontWeight: 800
  },
  cell: {
    flex:2,
    fontSize: 16, 
    fontWeight: 800
  },

});


export default LeaderboardScreen