package image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;

/**
 * ImageFromBase64 how to encode and decode Image to a Base64 string.
 * 
 * @see http://stackoverflow.com/a/10327584/1048330
 */
public class ImageFromBase64 {
	public static void main(String[] args) {
		try {
			String base64 = image2String("https://duke.kenai.com/wave/Wave.jpg");
			
			BufferedImage image = string2Image(base64);
			
			JOptionPane.showMessageDialog(null, "", "Image from base64", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage string2Image(String base64) throws IOException {
		byte[] btDataFile = Base64.decodeBase64(base64);	
		return ImageIO.read(new ByteArrayInputStream(btDataFile));
	}
	
	public static String image2String(String url) throws MalformedURLException, IOException {
		BufferedImage source = ImageIO.read(new URL(url));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(source, "jpg", baos);
		
		return Base64.encodeBase64String(baos.toByteArray());
	}
}
