import axios from 'axios';

export const imageToByteArray = async (imageUrl) => {
  try {
    const response = await axios.get(imageUrl, {
      responseType: 'arraybuffer',
    });

    const byteArray = new Uint8Array(response.data);
    console.log('Byte Array:', byteArray);
    return byteArray;
  } catch (error) {
    console.log('Image to Byte Array Error:', error);
    return null;
  }
};
