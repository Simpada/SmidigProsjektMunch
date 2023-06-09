export const loadImageAndConvertToByteArray = async () => {
    try {
        const imageUri = "../assets/Images/test.jpg"; // Update with the correct image path

        const response = await fetch(imageUri);

        const arrayBuffer = await response.arrayBuffer();

        const byteNumbers = new Uint8Array(arrayBuffer);



        const byteArray = Array.from(byteNumbers);


        console.log(byteArray)

        // for (let i = 0; i < byteArray.length; i++) {
        //     console.log(byteNumbers[i]);
        // }

        return byteArray;
    } catch (error) {
        console.log('Error loading image:', error);
        throw new Error('Error loading image: ' + error.message);
    }
};
