package no.kristiania.collectthemunch.database;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

public abstract class ImageManager {

    private String imagePath;
    private String imageType;


    public byte[] convertImageToBytes(String imagePath, String imageType) {
        try {
            File imageFile = new File(imagePath + imageType);

            BufferedImage image = ImageIO.read(imageFile);

            //Convert to byte array
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(image, imageType, out);

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
