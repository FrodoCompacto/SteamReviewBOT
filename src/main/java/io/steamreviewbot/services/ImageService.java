package io.steamreviewbot.services;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.AttributedString;
import java.time.Month;

import javax.annotation.processing.FilerException;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import io.steamreviewbot.domain.PostInformations;

@Service
public class ImageService {
	
	@SuppressWarnings("deprecation")
	public BufferedImage genNewReviewImage(PostInformations newPost) throws IOException{

		BufferedImage newImage = new BufferedImage(637,403, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = newImage.createGraphics();
		g2.setPaint(Color.CYAN);
        g2.fillRect(0, 0, 637,403);
		
		
        try {
			BufferedImage template = ImageIO.read(new URL("https://forbidden-bot.s3-sa-east-1.amazonaws.com/templateSTEAM.jpg"));
	        g2.drawImage(template, null, 0, 0);
	        
	        g2.drawImage(newPost.getUserProfilePic(), null, 18,20);
	        String recommended = "Not Recommended";
	        URL thumbURL;
	        if (newPost.getUpvote()) {
	        	thumbURL = new URL("https://forbidden-bot.s3-sa-east-1.amazonaws.com/like.jpg");
	        	recommended = "Recommended";
	        } else thumbURL = new URL("https://forbidden-bot.s3-sa-east-1.amazonaws.com/dislike.jpg");
	        BufferedImage thumb = ImageIO.read(thumbURL);
	        g2.drawImage(thumb, null, 222,18);
	        g2.drawImage(generateText(newPost.getProducts() + " products in account", newImage, "Arial", Color.decode("#c1dbf4"), 11, Font.PLAIN), null, 60, 41);
	        g2.drawImage(generateText(newPost.getReviews() + " reviews", newImage, "Arial", Color.decode("#c1dbf4"), 11, Font.PLAIN), null, 60, 56);
	        g2.drawImage(generateText("POSTED: " + newPost.getCreated().getDate() + " " + Month.of(newPost.getCreated().getMonth()+1).name(), newImage, "Arial", Color.decode("#8091a2"), 10, Font.PLAIN), null, 222, 76);
	        g2.drawImage(generateText(newPost.getHelpful() + " people found this review helpful", newImage, "Arial", Color.decode("#647580"), 12, Font.PLAIN), null, 222, 357);
	        g2.drawImage(generateText(newPost.getFunny() + " people found this review funny", newImage, "Arial", Color.decode("#647580"), 12, Font.PLAIN), null, 222, 372);
	        
	        String username;
	        if (newPost.getUsername().length() > 15) username = newPost.getUsername().substring(0, 15);
	        else username = newPost.getUsername();
	        g2.drawImage(generateText(username, newImage, "Arial", Color.decode("#c1dbf4"), 16, Font.BOLD), null, 60, 20);
	        g2.drawImage(generateText(recommended, newImage, "Arial", Color.decode("#d6d7d8"), 17, Font.PLAIN), null, 272, 21);
	        g2.drawImage(generateText(String.format("%.1f", newPost.getTimeOnRecord()) + " hrs on record (" + String.format("%.1f", newPost.getTimeOnLastWeeks()) + " hrs past 2 weeks)", newImage, "Arial", Color.decode("#8091a2"), 12, Font.PLAIN), null, 272, 41);
	        	        
	        drawParagraph(g2, newPost.getReviewBody(), 385, 222, 95);
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g2.dispose();
		
		return newImage;
		
	}
	
	private static BufferedImage generateText(String text,BufferedImage sourceImg , String fontName, Color color, Integer px, int fontType) {
		
		
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font(fontName, fontType, px);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text + "     ");
        int height = fm.getHeight();
        g2d.dispose();
        
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(color);
        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();

        return img;
	}
	
	void drawParagraph(Graphics2D g, String paragraph, float width, int posX, int posY) {
		AttributedString as = new AttributedString(paragraph);
		as.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 18));
		as.addAttribute(TextAttribute.FOREGROUND, Color.decode("#ebebeb"));
		
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		
	    LineBreakMeasurer linebreaker = new LineBreakMeasurer(as
	        .getIterator(), g.getFontRenderContext());
	    
	    
	    int y = posY;
	    while (linebreaker.getPosition() < paragraph.length()) {
	      TextLayout textLayout = linebreaker.nextLayout(width);

	      y += textLayout.getAscent();
	      textLayout.draw(g, posX, y);
	      y += textLayout.getDescent() + textLayout.getLeading();
	    }
	  }
	
	public InputStream getInputStream(BufferedImage img, String extension) throws IOException {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FilerException("Failed to read file.");
		}
	}
	
}
