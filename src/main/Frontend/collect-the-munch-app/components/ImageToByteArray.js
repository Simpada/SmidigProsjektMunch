export const loadImageAndConvertToByteArray = async () => {
    try {
        const imageUri = "../assets/Images/test.jpg"; // Update with the correct image path

        const response = await fetch(imageUri);

        const arrayBuffer = await response.arrayBuffer();

        const byteNumbers = new Uint8Array(arrayBuffer);

        const byteArray = Array.from(byteNumbers);

        console.log(byteArray)


        return byteArray;
    } catch (error) {
        console.log('Error loading image:', error);
        throw new Error('Error loading image: ' + error.message);
    }
};

export const convertToImage = async (stringVal) => {
    try {
        console.log("From db: " + stringVal);

        const byteStrings = stringVal.split(",");
        const byteNumbers = byteStrings.map((byteString) => parseInt(byteString.trim(), 10));
        const byteArray = new Uint8Array(byteNumbers);

        if (!Array.isArray(byteNumbers)) {
            throw new Error('byteNumbers must be an array');
        }

        const uint8Array = new Uint8Array(byteArray);
        const blob = new Blob([uint8Array], { type: 'image/jpeg' });

        const dataURL = URL.createObjectURL(blob);
        const image = new Image();

        image.src = dataURL;

        return new Promise((resolve, reject) => {
            image.onload = () => {
                // The image has loaded, you can now use the 'image' element
                // for displaying or further processing
                document.body.appendChild(image); // Example: Append the image to the body
                resolve(image);
            };

            image.onerror = () => {
                reject(new Error('Failed to load the image.'));
            };
        });
    } catch (error) {
        console.log('Error loading image:', error);
        throw new Error('Error loading image: ' + error.message);
    }
};

