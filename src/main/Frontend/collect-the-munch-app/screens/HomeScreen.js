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
      <View style={styles.profilePicture} >
        <Image style={styles.reviewImage} source={reviewicon1}/>
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
    <ScrollView contentContainerStyle={styles.container}>
      <View style={styles.dropdownContainer}>
        <TouchableOpacity onPress={toggleMenu} style={styles.dropdownButton}>
          <Entypo name="menu" size={24} color={colors.white} />
        </TouchableOpacity>
        {isMenuOpen && (
          <View style={styles.menu}>
            {menuItems.map((item, index) => (
              <React.Fragment key={item}>
                <TouchableOpacity
                  onPress={() => handleOptionChange(item)}
                  style={[styles.menuItem, index !== 0 && styles.menuItemWithBorder]}
                >
                  <Text style={[styles.menuItemText, selectedOption === item && styles.selectedMenuItemText]}>
                    {renderIcon(item)} {item}
                  </Text>
                </TouchableOpacity>
                {index !== menuItems.length - 1 && <View style={styles.menuItemSeparator} />}
              </React.Fragment>
            ))}
          </View>
        )}
      </View>
      <View style={styles.imageContainer}>
        <Image style={styles.image} source={HeaderImg} resizeMode="cover" />
        <View style={styles.headerContainer}>
          <View style={styles.textContainer}>
            <Text style={styles.text}>MUNCH</Text>
          </View>
        </View>
      </View>
      <View style={styles.textBox}>
        <Text style={styles.textBoxText}>
          I dette spillet får du muligheten til å samle ulike kunstverk. Jo flere verk du samler, desto bedre sjanser har du til å klatre til toppen av poengtavlen og vinne unike premier. Bli den ultimate Munch-samleren og opplev spenningen ved å konkurrere mot andre kunstinteresserte.
        </Text>
      </View>
      <View style={styles.PlayDescription}>
        <Text style={styles.PlayDescriptionText}>
          "Collect the Munch" er en unik måte å oppleve kunstverdenen på, hvor du får både underholdning, kunnskap og sjansen til å vinne flotte premier. Bli med på dette eventyret og la Munchs mesterverker inspirere deg!
        </Text>
      </View>
      <TouchableOpacity style={styles.PlayButton}>
        <Text style={styles.PlayButtonText}>Play Collect the Munch</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.reviewButton}>
        <Text style={styles.reviewButtonText}>Review the app!</Text>
      </TouchableOpacity>
      <ScrollView
        horizontal
        showsHorizontalScrollIndicator={false}
        contentContainerStyle={styles.reviewsScrollView}
      >
        <View style={styles.reviewsContainer}>
          <Text style={styles.reviewsTitle}>Reviews</Text>
          <FlatList
            data={reviews}
            renderItem={({ item }) => <ReviewItem item={item} />}
            keyExtractor={(item) => item.id}
            horizontal
            showsHorizontalScrollIndicator={false}
            contentContainerStyle={styles.reviewsContentContainer}
          />
        </View>
      </ScrollView>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    backgroundColor: colors.navy,
    alignItems: 'center',
  },
  dropdownContainer: {
    position: 'absolute',
    top: 40,
    left: 20,
    zIndex: 1,
  },
  dropdownButton: {
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: colors.navy,
    width: 30,
    height: 30,
    borderRadius: 5,
    elevation: 3,
  },
  menu: {
    marginTop: 5,
    backgroundColor: colors.navy,
    borderRadius: 5,
    paddingHorizontal: 10,
    paddingVertical: 5,
    elevation: 3,
  },
  menuItem: {
    paddingVertical: 8,
  },
  menuItemWithBorder: {
    borderTopColor: colors.white,
    borderTopWidth: 1,
  },
  menuItemSeparator: {
    height: 1,
    backgroundColor: colors.white,
  },
  menuItemText: {
    fontSize: 16,
    color: colors.white,
    marginLeft: 5,
    fontFamily: 'GirottMunch-Bold',
  },
  selectedMenuItemText: {
    fontWeight: 'bold',
  },
  imageContainer: {
    width: '100%',
    height: 400,
    position: 'relative',
  },
  image: {
    flex: 1,
    width: undefined,
    height: undefined,
  },
  headerContainer: {
    position: 'absolute',
    top: '50%',
    left: 0,
    right: 0,
    alignItems: 'center',
    zIndex: 1,
  },
  textContainer: {
    backgroundColor: colors.red,
    paddingHorizontal: 10,
    paddingVertical: 5,
    borderRadius: 5,
    alignSelf: 'flex-start',
    width: '100%',
  },
  text: {
    color: colors.black,
    fontSize: 100,
    fontFamily: 'GirottMunch-BoldBackslant',
    textAlign: 'center',
  },
  textBox: {
    backgroundColor: colors.green,
    padding: 35,
    marginVertical: 20,
    marginHorizontal: 20,
    borderRadius: 25,
  },
  textBoxText: {
    color: colors.white,
    fontSize: 20,
    fontFamily: 'GirottMunch-Bold',
    textAlign: 'center',
  },
  PlayDescription: {
    backgroundColor: colors.navy,
    padding: 35,
    marginVertical: 10,
    marginHorizontal: 20,
    borderRadius: 25,
  },
  PlayDescriptionText: {
    color: colors.white,
    fontSize: 20,
    fontFamily: 'GirottMunch-Bold',
    textAlign: 'center',
  },
  PlayButton: {
    backgroundColor: colors.red,
    paddingHorizontal: 40,
    paddingVertical: 25,
    borderRadius: 10,
    marginVertical: 30,
  },
  PlayButtonText: {
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
  reviewsScrollView: {
    paddingHorizontal: 20,
  },
  reviewsContainer: {
    backgroundColor: colors.red,
    borderRadius: 20,
    padding: 10,
    marginRight: 10,
  },
  reviewsTitle: {
    color: colors.white,
    fontSize: 20,
    fontFamily: 'GirottMunch-Bold',
    marginBottom: 10,
  },
  reviewsContentContainer: {
    alignItems: 'center',
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
