
import { useEffect } from 'react';
import { View, StyleSheet} from 'react-native'
import * as Font from 'expo-font';
import { colors } from '../Styles/theme';
const HomeScreen = () => {
  useEffect(() => {
    loadFonts();
  }, []);

  const loadFonts = async () => {
    await Font.loadAsync({
      'GirottMunch-BoldBackslant': require('../assets/fonts/GirottMunch-BoldBackslant.ttf'),
    });
  };
    return (
      <View style={styles.container}>
      </View>
    )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.navy,
    alignItems: 'center',
    justifyContent: "center"
  },
  text: {
    color: colors.red,
    fontSize: 100,
    fontFamily: "GirottMunch-BoldBackslant"
  },
  munchtext: {
    fontSize: 100,
    textAlign:"center",
    color: "black",
  },
  image: {
    width: 100, 
    height: 100
  },
  munchContainer: {
    backgroundColor: "#FE390F",
    width: "100%",
  },
  stars: {
    flexDirection: "row"
  }


});
export default HomeScreen