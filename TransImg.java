import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

/*
 * This class is used to create a transparent image.
 * It is used to display the background image of the main upper panel.
 */
public class TransImg extends JPanel {
	// Declare variables
	private BufferedImage image;  
	private float alpha;  // Transparency
	
	// Constructor. Set the image path and transparency
	public TransImg(String imagePath, float alpha) {
		try {
			this.setLayout(new GridBagLayout());
			image = ImageIO.read(getClass().getResource(imagePath));
			this.alpha = alpha;
		} catch (IOException e) { // Catch exception
			e.printStackTrace();
		}
	}
	
	// Override the paintComponent method
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g.create();  // Create a Graphics2D object
		g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));  // Set transparency
		g2d.drawImage(image, 0, 0, this);  		// Draw the image
		g2d.dispose();  // Dispose the Graphics2D object
	}
}
