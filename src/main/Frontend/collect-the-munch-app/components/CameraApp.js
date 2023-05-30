import React from 'react';
import { View, Button, Linking, Alert } from 'react-native';

const CameraApp = () => {
  const openCamera = async () => {
    const canOpenCamera = await Linking.canOpenURL('camera://');

    if (canOpenCamera) {
      await Linking.openURL('camera://');
    } else {
      Alert.alert('Camera', 'Camera app is not available on your device.');
    }
  };

  return (
    <View>
      <Button title="Open Camera" onPress={openCamera} />
    </View>
  );
};

export default CameraApp;