export const convertToImage = async (byteArray) => {
    try {
        console.log("From db: " + byteArray);

        if (!Array.isArray(byteArray)) {
            throw new Error('byteArray must be an array');
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