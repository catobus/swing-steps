package image; 

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;

/**
 * ImageChunks demo application illustrates use of ImageReader 
 * and ImageReadParam to read fragments of image. 
 * 
 * @see http://stackoverflow.com/a/15149382/1048330
 */
public class ImageChunks {
    private static void createAndShowUI() {
        try {
            URL url = new URL(
                    "http://duke.kenai.com/wave/.Midsize/Wave.png.png");
            Image chunk = readFragment(url.openStream(), new Rectangle(150,
                    150, 300, 250));
            JOptionPane.showMessageDialog(null, new ImageIcon(chunk), "Duke", 
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Failure",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static BufferedImage readFragment(InputStream stream, Rectangle rect)
            throws IOException {
        ImageInputStream imageStream = ImageIO.createImageInputStream(stream);
        ImageReader reader = ImageIO.getImageReaders(imageStream).next();
        ImageReadParam param = reader.getDefaultReadParam();

        param.setSourceRegion(rect);
        reader.setInput(imageStream, true, true);
        BufferedImage image = reader.read(0, param);

        reader.dispose();
        imageStream.close();

        return image;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowUI();
            }
        });
    }
}
