import { Image } from 'react-native';
import * as FileSystem from 'expo-file-system';

export const ImageToByteArray = async (imagePath) => {
  try {
    const fileUri = Image.resolveAssetSource(imagePath).uri;
    const base64Image = await FileSystem.readAsStringAsync(fileUri, {
      encoding: FileSystem.EncodingType.Base64,
    });

    const byteArray = Uint8Array.from(atob(base64Image), (c) => c.charCodeAt(0));
    return byteArray;
  } catch (error) {
    console.error('Error converting image to byte array:', error);
    return null;
  }
};

