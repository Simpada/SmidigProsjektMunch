import React, { useState, useEffect, useRef } from 'react';
import { View, StyleSheet, Text, TouchableOpacity, Modal, Image } from 'react-native';
import { BarCodeScanner } from 'expo-barcode-scanner';
import * as Animatable from 'react-native-animatable';

const QRScanner = ({ navigation }) => {
  const [hasPermission, setHasPermission] = useState(null);
  const [scanned, setScanned] = useState(false);
  const [isCameraOpen, setIsCameraOpen] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const flipInYRef = useRef(null);

  useEffect(() => {
    (async () => {
      const { status } = await BarCodeScanner.requestPermissionsAsync();
      setHasPermission(status === 'granted');
    })();
  }, []);

  const handleBarCodeScanned = ({ type, data }) => {
    setScanned(true);
    setShowModal(true);
    // You can perform any additional logic here based on the scanned data
  };

  const handleOpenScanner = () => {
    setIsCameraOpen(true);
  };

  const handleCloseScanner = () => {
    setIsCameraOpen(false);
    setScanned(false);
  };

  const handleModalClose = () => {
    flipInYRef.current.reset();
    setShowModal(false);
  };

  const handleModalOpen = () => {
    flipInYRef.current?.animate({
      0: { scale: 1 },
      0.3: { scale: 1.2 },
      0.7: { scale: 1.1 },
      1: { scale: 1 },
    }, 4000, 'ease-in-out');
  
    setTimeout(() => {
      flipInYRef.current?.shake(800);
    }, 3000);
  };

  return (
    <View style={styles.container}>
      {isCameraOpen ? (
        <View style={styles.cameraContainer}>
          <BarCodeScanner
            onBarCodeScanned={scanned ? undefined : handleBarCodeScanned}
            style={styles.scanner}
          />
          <TouchableOpacity style={styles.closeButton} onPress={handleCloseScanner}>
            <Text style={styles.buttonText}>Close Scanner</Text>
          </TouchableOpacity>
        </View>
      ) : (
        <TouchableOpacity style={styles.recordButton} onPress={handleOpenScanner}>
          <View style={styles.circle} />
        </TouchableOpacity>
      )}

      <Modal visible={showModal} animationType="slide" transparent={true} onShow={handleModalOpen}>
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            <Animatable.Image
              ref={flipInYRef}
              animation="flipInY"
              duration={800}
              source={require('../assets/Images/Scream.jpg')}
              style={styles.image}
            />
            <Text style={styles.title}>Scream</Text>
            <View style={styles.rarityContainer}>
              <Text style={styles.rarity}>Rarity:</Text>
              <Text style={[styles.rarity, styles.legendary]}>Legendary</Text>
            </View>
            <TouchableOpacity style={styles.closeButton} onPress={handleModalClose}>
              <Text style={styles.buttonText}>Close</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'flex-end',
  },
  cameraContainer: {
    flex: 0.75,
    width: '100%',
  },
  scanner: {
    aspectRatio: 1,
    width: '100%',
  },
  recordButton: {
    backgroundColor: 'transparent',
    alignItems: 'center',
    justifyContent: 'center',
    width: 80,
    height: 80,
    borderRadius: 40,
    borderWidth: 5,
    borderColor: 'red',
    marginBottom: 20,
  },
  circle: {
    backgroundColor: 'red',
    width: 60,
    height: 60,
    borderRadius: 30,
  },
  closeButton: {
    backgroundColor: '#333',
    paddingVertical: 10,
    paddingHorizontal: 20,
    borderRadius: 5,
    marginTop: 20,
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold',
    textAlign: 'center',
  },
  modalContainer: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  modalContent: {
    backgroundColor: '#fff',
    padding: 20,
    borderRadius: 10,
    alignItems: 'center',
    justifyContent: 'center',
  },
  image: {
    width: 200,
    height: 200,
    marginBottom: 10,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 10,
    textAlign: 'center',
  },
  rarityContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 10,
  },
  rarity: {
    fontSize: 18,
    fontWeight: 'bold',
    marginRight: 5,
  },
  legendary: {
    color: 'orange',
  },
});

export default QRScanner;
