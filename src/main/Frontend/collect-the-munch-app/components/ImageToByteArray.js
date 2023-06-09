import * as FileSystem from 'expo-file-system';

export const loadImageAndConvertToByteArray = async (imageUri) => {
  try {
    const response = await fetch(imageUri);
    const imageBlob = await response.blob();
    const reader = new FileReader();
    const bytePromise = new Promise((resolve, reject) => {
      reader.onloadend = () => {
        const arrayBuffer = reader.result;
        const byteArray = new Uint8Array(arrayBuffer);
        resolve(byteArray);
      };
      reader.onerror = reject;
    });
    reader.readAsArrayBuffer(imageBlob);
    return bytePromise;
  } catch (error) {
    console.log('Error loading image:', error);
    throw new Error('Error loading image: ' + error.message);
  }
};
