import React, { useState, useEffect } from 'react';
import { View, StyleSheet, Text, TouchableOpacity, Modal, Image, Dimensions } from 'react-native';
import { BarCodeScanner } from 'expo-barcode-scanner';

const QRScanner = ({ navigation }) => {
  const [hasPermission, setHasPermission] = useState(null);
  const [scanned, setScanned] = useState(false);
  const [isCameraOpen, setIsCameraOpen] = useState(false);
  const [showModal, setShowModal] = useState(false);

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

  const windowWidth = Dimensions.get('window').width;
  const windowHeight = Dimensions.get('window').height;

  return (
    <View style={styles.container}>
      {isCameraOpen ? (
        <View style={[styles.cameraContainer, { width: windowWidth, height: windowHeight }]}>
          <BarCodeScanner
            onBarCodeScanned={scanned ? undefined : handleBarCodeScanned}
            style={[styles.scanner, { width: windowWidth, height: windowHeight }]}
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

      <Modal visible={showModal} animationType="slide" transparent={true}>
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            <Image source={require('../assets/Images/Scream.jpg')} style={styles.image} />
            <Text style={styles.title}>Scream</Text>
            <View style={styles.rarityContainer}>
              <Text style={styles.rarity}>Rarity:</Text>
              <Text style={[styles.rarity, styles.legendary]}>Legendary</Text>
            </View>
            <TouchableOpacity style={styles.closeButton} onPress={() => setShowModal(false)}>
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
    flex: 1,
  },
  scanner: {
    flex: 1,
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
    marginTop: 0, // Adjust the marginTop value as desired
    overflow: 'hidden'
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
