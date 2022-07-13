package misl.testcode;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class ColorPicker {
	
	public static void main(String[] args) {
		System.out.println("ColorPicker");
		
		JFrame jFrame = new JFrame("ColorPicker");
		JPanel jPanel = new JPanel();
		jPanel.setPreferredSize(new Dimension(500, 400));
		jPanel.setBackground(Color.WHITE);
		jPanel.setLayout(null);
		
		int x = 0;
		int y = 0;
		int width = 50;
		int height = 20;

		float hue = 0f;
		float saturation = 1.0f;
		float brightness = 1.0f;

		for(int row=3; row<=5; row++) {
			int inc = 255/row;
			System.out.println("inc:" + inc);
			hue = 0f;
			for(int col=0; col<row; col++) {
				
				System.out.println("hue:" + hue + ", saturation:" + saturation 
						+ ", brightness:" + brightness);
				Color color = new Color(Color.HSBtoRGB((hue/255), saturation, brightness));
				System.out.println("red:" + color.getRed() + ", green:" + color.getGreen()
						+ ", blue:" + color.getBlue());
				
				JLabel jLabel = new JLabel(row+"-"+col);
				jLabel.setOpaque(true);
				jLabel.setBackground(color);
				jLabel.setPreferredSize(new Dimension(width, height));
				jLabel.setBounds(x, y, width, height);
				jPanel.add(jLabel);
				
				hue += inc;
				x += width;
			}
			x = 0;
			y += height;
		}
		
		jFrame.getContentPane().add(jPanel);
		jFrame.pack();
		jFrame.setVisible(true);
	}

}
