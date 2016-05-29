import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * A utility class for loading an image from a URL
 * @author stefan
 *
 */
public class ImageFetcher {
	
	/**
	 * Download an image from a URL.
	 * 
	 * If the image cannot be loaded, null is returned.
	 * 
	 * @param url the URL to load.
	 * @return The downloaded image, or null.
	 */
	public static BufferedImage fetchImage(String url) {
		if (url == null)
			return null;
		
		try {
			URL u = new URL(url);
			
			return ImageIO.read(u);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
