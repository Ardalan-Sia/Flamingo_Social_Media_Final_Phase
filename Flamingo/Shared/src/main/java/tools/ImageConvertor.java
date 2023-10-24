package tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class ImageConvertor {

    public static String toString(BufferedImage image , String format) throws IOException {
        if (image == null)
            return null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, format, bos );
        byte [] data = bos.toByteArray();

        return Base64.getEncoder().encodeToString(data);
    }

    public static BufferedImage toBufferedImage(String encoded) throws IOException {
        if (encoded == null)
            return null;
        byte [] data = Base64.getDecoder().decode(encoded);
        InputStream is = new ByteArrayInputStream(data);
        BufferedImage bi = ImageIO.read(is);
        return bi;
    }
}
