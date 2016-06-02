package view;

import java.awt.Image;
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
	
	private static final String PLACEHOLDER_IMAGE_URL = "http://www.viscofoods.com/wp-content/themes/456market/assets//img/placeholder.png";
	
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
			return fetchImage(PLACEHOLDER_IMAGE_URL);
		
		if (url == PLACEHOLDER_IMAGE_URL)
			throw new RuntimeException("Could not load placeholder image.");
		
		try {
			URL u = new URL(url);
			
			return ImageIO.read(u);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fetchImage(PLACEHOLDER_IMAGE_URL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fetchImage(PLACEHOLDER_IMAGE_URL);
		}
	}
	
	/**
	 * Download an image and scale it to the given dimensions.
	 * 
	 * If the image is too wide, equal amounts are cropped from the left and right.
	 * If the image is too narrow, the top and bottom are cropped.
	 * If the image is exactly the requested size, it is returned as-is.
	 * 
	 * If the image cannot be loaded, null is returned.
	 * @param url the URL to load.
	 * @param width the desired width.
	 * @param height the desired height.
	 * @return the resized image, or null.
	 */
	public static Image fetchImage(String url, int width, int height) {
		BufferedImage fresh = fetchImage(url);
		if (fresh == null)
			return null;
		
		int freshWidth = fresh.getWidth();
		int freshHeight = fresh.getHeight();
		double freshAspectRatio = 1.0 * freshWidth / freshHeight;
		
		double desiredAspectRatio = 1.0 * width / height;
		
		// First crop the image to the correct aspect ratio
		BufferedImage cropped;
		if (freshAspectRatio > desiredAspectRatio) {
			// If the image is too wide
			int widthAtDesiredAR = (int)(freshHeight * desiredAspectRatio);
			int excessWidth = freshWidth - widthAtDesiredAR;
			
			cropped = fresh.getSubimage(
					excessWidth / 2, 
					0, 
					widthAtDesiredAR, 
					freshHeight
			);
		} else if (freshAspectRatio < desiredAspectRatio) {
			// If the image is too narrow
			int heightAtDesiredAR = (int)(freshWidth / desiredAspectRatio);
			int excessHeight = freshHeight - heightAtDesiredAR;
			
			cropped = fresh.getSubimage(
					0, 
					excessHeight / 2, 
					freshWidth, 
					heightAtDesiredAR
			);
		} else {
			// If the image is just right
			cropped = fresh;
		}
		
		
		// If the image is exactly the right size, don't mess with it.
		// This gives the user the option to provide a perfectly-sized
		// image to avoid rescaling artifacts.
		if (cropped.getWidth() == width &&
			cropped.getHeight() == height)
			return cropped;
		else	// Otherwise resize the image
			return cropped.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
	}

}
