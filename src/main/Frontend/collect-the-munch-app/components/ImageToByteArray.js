import * as FileSystem from 'expo-file-system';
import test from "../assets/Images/test.png";

export const loadImageAndConvertToByteArray = async () => {
    try {
        const imageUri = test; // Update with the correct image path

        const fileUri = FileSystem.cacheDirectory + imageUri;
        await FileSystem.downloadAsync(imageUri, fileUri);

        const fileContent = await FileSystem.readAsStringAsync(fileUri, {
            encoding: FileSystem.EncodingType.Base64,
        });

        const byteCharacters = atob(fileContent);
        const byteNumbers = new Uint8Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
            byteNumbers[i] = byteCharacters.charCodeAt(i);
        }

        return byteNumbers;
    } catch (error) {
        console.log('Error loading image:', error);
        throw new Error('Error loading image: ' + error.message);
    }
};
