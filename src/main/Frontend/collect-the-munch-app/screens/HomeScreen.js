import React, { useEffect, useState } from 'react';
import { Text, View, StyleSheet, Image, TouchableOpacity, ScrollView, FlatList } from 'react-native';
import * as Font from 'expo-font';
import { colors } from '../Styles/theme';
import HeaderImg from '../assets/Images/munch-museet.avif';
import { Entypo, Feather, FontAwesome5, AntDesign } from '@expo/vector-icons';
import Review from '../components/Review';
import reviewicon1 from "../assets/Images/profile1.png";
import axios from 'axios';
import Header from '../components/Header';

const HomeScreen = () => {
  const [fontsLoaded, setFontsLoaded] = useState(false);
  const [isMenuOpen, setMenuOpen] = useState(false);
  const [selectedOption, setSelectedOption] = useState('Option 1');
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    const loadFonts = async () => {
      await Font.loadAsync({
        'GirottMunch-BoldBackslant': require('../assets/fonts/GirottMunch-BoldBackslant.ttf'),
        'GirottMunch-Bold': require('../assets/fonts/GirottMunch-Bold.ttf'),
        'GirottMunch-BoldSlant': require('../assets/fonts/GirottMunch-BoldSlant.ttf'),
      });
      setFontsLoaded(true);
    };

    loadFonts();
    fetchReviews();
  }, []);


  const fetchReviews = async () => {
    try {
      const response = await axios.get('https://findthemunchgame.azurewebsites.net/api/review/app/');
      const reviewsData = response.data;
      const reviewArray = reviewsData.map((review) => ({
        user: review.userName,
        text: review.reviewText,
        stars: review.numOfStars,
      }));
      setReviews(reviewArray);
    } catch (error) {
      console.error('Error fetching reviews:', error);
    }
  };


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
              size={25}
              color={star <= item.stars ? colors.yellow : colors.grey}
              solid={star <= item.stars}
              style={styles.starIcon}
            />
          ))}
        </View>
      </View>
    </View>
  );

  if (!fontsLoaded) {
    return null;
  }


  return (
  <>
      <Header />
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
        
      </View>

      <View style={styles.playDescription}>
        <Text style={styles.playDescriptionText}>
          "Collect the Munch" er en unik måte å oppleve kunstverdenen på. Bli med på dette eventyret og la Munchs mesterverker inspirere deg!
        </Text>
      </View>

      <View style={[styles.reviewsBackground, { width: '100%' }]}>
        <Text style={styles.reviewsTitle}>Reviews</Text>
        {reviews.length > 0 ? (
          <FlatList
          data={reviews}
          renderItem={({ item }) => <ReviewItem item={item} />}
          keyExtractor={(item, index) => (item.id ? item.id.toString() : index.toString())}
          horizontal
          showsHorizontalScrollIndicator={false}
          />
          ) : (
            <Text style={styles.noReviewsText}>No reviews available</Text>
            )}
      </View>
      <View style={styles.leaveReviewContainer}>
        <Review reviewType="app"/>
      </View>
    </ScrollView>
</>
  );
};

const styles = StyleSheet.create({
  midPageContainer: {
    flexGrow: 1,
    backgroundColor: colors.navy,
    alignItems: 'center',
    paddingBottom: 100,
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

  playDescription: {
    textAlign: 'left',
    marginVertical: 10,
    marginHorizontal: 20,
  },
  playDescriptionText: {
    color: colors.white,
    fontSize: 20,
    paddingVertical: 100,
    lineHeight: 30,
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
    backgroundColor:"rgba(0,0,0,0.5)",
    width: 250,
    padding: 10,
    borderRadius: 10,
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
  noReviewsText: {
    color: colors.white,
    fontSize: 16,
    fontFamily: 'GirottMunch-Bold',
    textAlign: 'center',
    marginTop: 10,
  },
  leaveReviewContainer:{
    marginVertical: 0,
    width: '100%',
  }
});

export default HomeScreen;
