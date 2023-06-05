import React, { useEffect, useState } from 'react';
import { Text, View, StyleSheet, Image, TouchableOpacity, ScrollView, FlatList } from 'react-native';
import * as Font from 'expo-font';
import { colors } from '../Styles/theme';
import HeaderImg from '../assets/Images/munch-museet.avif';
import { Entypo, Feather, FontAwesome5, AntDesign } from '@expo/vector-icons';
import reviewicon1 from "../assets/Images/samuel.png"


const HomeScreen = () => {
  useEffect(() => {
    loadFonts();
  }, []);

  const loadFonts = async () => {
    await Font.loadAsync({
      'GirottMunch-BoldBackslant': require('../assets/fonts/GirottMunch-BoldBackslant.ttf'),
      'GirottMunch-Bold': require('../assets/fonts/GirottMunch-Bold.ttf'),
      'GirottMunch-BoldSlant': require('../assets/fonts/GirottMunch-BoldSlant.ttf'),
    });
  };

  const [isMenuOpen, setMenuOpen] = useState(false);
  const [selectedOption, setSelectedOption] = useState('Option 1');

  const handleOptionChange = (value) => {
    setSelectedOption(value);
    setMenuOpen(false);
  };

  const toggleMenu = () => {
    setMenuOpen(!isMenuOpen);
  };

  const renderIcon = (option) => {
    switch (option) {
      case 'User':
        return <AntDesign name="user" size={16} color={colors.white} />;
      case 'The Collection':
        return <Feather name="archive" size={16} color={colors.white} />;
      case 'Inventory':
        return <FontAwesome5 name="box-open" size={16} color={colors.white} />;
      case 'Leaderboards':
        return <FontAwesome5 name="trophy" size={16} color={colors.white} />;
      case 'Settings':
        return <AntDesign name="setting" size={16} color={colors.white} />;
      default:
        return null;
    }
  };

  const menuItems = ['User', 'The Collection', 'Inventory', 'Leaderboards', 'Settings'];

  const reviews = [
    {
      id: '1',
      user: 'Jenni',
      rating: 5,
      text: 'Great app! I love collecting Munch artworks.',
    },
    {
      id: '2',
      user: 'Rafael',
      rating: 5,
      text: 'The best art collection game out there. Highly recommended!',
    },
    {
      id: '3',
      user: 'Mathias',
      rating: 5,
      text: 'Good concept, I want to bring all my friends!',
    },
  ];

  const ReviewItem = ({ item }) => (
    <View style={styles.reviewContainer}>
      <View style={styles.profilePicture}>
        <Image style={styles.reviewImage} source={reviewicon1} />
      </View>
      <View style={styles.reviewTextContainer}>
        <Text style={styles.reviewName}>{item.user}</Text>
        <Text style={styles.reviewText}>{item.text}</Text>
        <View style={styles.reviewRatingContainer}>
          {[1, 2, 3, 4, 5].map((star) => (
            <FontAwesome5
              key={star}
              name="star"
              size={12}
              color={colors.yellow}
              solid yellow
              style={styles.starIcon}
            />
          ))}
        </View>
      </View>
    </View>
  );

  return (
    <ScrollView contentContainerStyle={styles.midPageContainer}>
      <View style={styles.munchImageContainer}>
        <Image style={styles.munchImage} source={HeaderImg} resizeMode="cover" />
        <View style={styles.munchHeaderContainer}>
          <View style={styles.munchHeaderTextContainer}>
            <Text style={styles.headlineText}>MUNCH</Text>
          </View>
        </View>
      </View>
      <View style={styles.gameDescriptionContainer}>
        <Text style={styles.gameDescriptionText}>
          I dette spillet får du muligheten til å samle ulike kunstverk. Jo flere verk du samler, desto bedre sjanser har du til å klatre til toppen av poengtavlen og vinne unike premier. Bli den ultimate Munch-samleren og opplev spenningen ved å konkurrere mot andre kunstinteresserte.
        </Text>
      </View>

      <View style={styles.playDescription}>
        <Text style={styles.playDescriptionText}>
          "Collect the Munch" er en unik måte å oppleve kunstverdenen på, hvor du får både underholdning, kunnskap og sjansen til å vinne flotte premier. Bli med på dette eventyret og la Munchs mesterverker inspirere deg!
        </Text>
      </View>

      <TouchableOpacity style={styles.playButton}>
        <Text style={styles.playButtonText}>Play Collect the MUNCH</Text>
      </TouchableOpacity>

      <TouchableOpacity style={styles.reviewButton}>
        <Text style={styles.reviewButtonText}>Review the app!</Text>
      </TouchableOpacity>
      
      <View style={[styles.reviewsBackground, { width: '100%' }]}>
        <Text style={styles.reviewsTitle}>Reviews</Text>
        <ScrollView horizontal showsHorizontalScrollIndicator={false}>
          {reviews.map((item) => (
            <ReviewItem key={item.id} item={item} />
          ))}
        </ScrollView>
      </View>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  midPageContainer: {
    flexGrow: 1,
    backgroundColor: colors.navy,
    alignItems: 'center',
    paddingBottom: 100
  },

  munchImageContainer: {
    width: '100%',
    height: 400,
    position: 'relative',
  },
  munchImage: {
    flex: 1,
    width: undefined,
    height: undefined,
  },
  munchHeaderContainer: {
    position: 'absolute',
    top: '50%',
    left: 0,
    right: 0,
    alignItems: 'center',
    zIndex: 1,
  },
  munchHeaderTextContainer: {
    backgroundColor: colors.red,
    paddingHorizontal: 10,
    paddingVertical: 5,
    borderRadius: 5,
    alignSelf: 'flex-start',
    width: '100%',
  },
  headlineText: {
    color: colors.black,
    fontSize: 100,
    fontFamily: 'GirottMunch-BoldBackslant',
    textAlign: 'center',
  },
  gameDescriptionContainer: {
    backgroundColor: colors.green,
    padding: 35,
    marginVertical: 20,
    marginHorizontal: 20,
    borderRadius: 25,
  },
  gameDescriptionText: {
    color: colors.white,
    fontSize: 20,
    fontFamily: 'GirottMunch-Bold',
    textAlign: 'center',
  },
  playDescription: {
    backgroundColor: colors.navy,
    padding: 35,
    marginVertical: 10,
    marginHorizontal: 20,
    borderRadius: 25,
  },
  playDescriptionText: {
    color: colors.white,
    fontSize: 20,
    fontFamily: 'GirottMunch-Bold',
    textAlign: 'center',
  },
  playButton: {
    backgroundColor: colors.red,
    paddingHorizontal: 40,
    paddingVertical: 25,
    borderRadius: 10,
    marginVertical: 30,
  },
  playButtonText: {
    color: colors.white,
    fontSize: 20,
    fontFamily: 'GirottMunch-BoldSlant',
    textAlign: 'center',
  },
  reviewButton: {
    backgroundColor: colors.green,
    paddingHorizontal: 20,
    paddingVertical: 10,
    borderRadius: 50,
    marginBottom: 10,
    alignSelf: 'flex-start',
  },
  reviewButtonText: {
    color: colors.white,
    fontSize: 16,
    fontFamily: 'GirottMunch-BoldSlant',
    textAlign: 'center',
  },
  reviewsBackground: {
    backgroundColor: colors.red,
    padding: 10,
    marginRight: 10,
  },
  reviewsTitle: {
    color: colors.white,
    fontSize: 20,
    fontFamily: 'GirottMunch-Bold',
    marginBottom: 10,
  },
  reviewContainer: {
    backgroundColor: colors.grey,
    width: 200,
    padding: 10,
    borderRadius: 20,
    marginRight: 10,
  },
  profilePicture: {
    width: 40,
    height: 40,
    borderRadius: 20,
    overflow: 'hidden',
  },
  reviewImage: {
    flex: 1,
    width: undefined,
    height: undefined,
  },
  reviewTextContainer: {
    marginLeft: 10,
    flex: 1,
  },
  reviewName: {
    color: colors.white,
    fontSize: 14,
    fontFamily: 'GirottMunch-Bold',
  },
  reviewText: {
    color: colors.white,
    fontSize: 12,
    fontFamily: 'GirottMunch-Bold',
    marginTop: 5,
  },
  reviewRatingContainer: {
    flexDirection: 'row',
    marginTop: 5,
  },
  star: {
    marginHorizontal: 1,
  },
});

export default HomeScreen;
