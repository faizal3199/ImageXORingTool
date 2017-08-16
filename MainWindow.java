//version 1.0
package ImageXORingTool;


import ImageXORingTool.AdvancedBufferedImage;

import javax.imageio.ImageIO;


import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

public class MainWindow extends JFrame {
	public static int arrLength;
	public static void main(String[] arg) throws IOException {
		
	
		//Creating ProgressBar
		SwingProgressBarExample prog = new SwingProgressBarExample();
		
		JFrame frame = new JFrame("Progress");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(prog);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		prog.setText("Select ALL the images you want to XOR");//Using Multiple files selection, select all images you want to XOR
		prog.updateBar(0);
		
		Component c = new Component() { };
		
		
		//Select Files From User(Multiple Files Selecting)
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
	    int returnVal = chooser.showOpenDialog(c);
	    File[] f = chooser.getSelectedFiles();
		
		//Computing Array Length for further use
		arrLength = f.length;
	    
		//Creating and Initializing Objects
		AdvancedBufferedImage[] image = new AdvancedBufferedImage[arrLength];
		for(int i =0 ;i<arrLength ;i++){
			image[i] = new AdvancedBufferedImage();
		}
		
		prog.setText("Copying images to memory");
		prog.updateBar(0);
	    
		//Copying images to memory
		for(int i =0 ;i<arrLength;i++){
			image[i].AdvancedBufferedImageGet(ImageIO.read(f[i]));
		}
	   
		prog.setText("Converting Images");
		prog.updateBar(10);

		//Geting pixels of an image and Pixels for an images in a Row
		//Every Row represent different image
		int[][] buffer = new int[arrLength][];
		for(int i = 0; i<arrLength;i++){
			buffer[i] = image[i].getPixels();
		}
		
		prog.setText("XORing images"); 
		prog.updateBar(30);
		
		//XORing all the images using their Pixels values
		for (int rows=0;rows<arrLength-1;rows++){
			for( int i = 0 ; i < image[rows].bufferedImage().getWidth() * image[rows].bufferedImage().getHeight() ; i++ ){
				buffer[rows+1][i] = buffer[rows][i] ^ (buffer[rows+1][i]);  //IF you want to multiply pixles value then change XOR sign(^) to Multiplication sign(*)
			}
		}
		
		//Creating Final Single Dimensional Array for final image saving
		int[] bufferfinal = new int[image[arrLength-1].bufferedImage().getWidth() * image[arrLength-1].bufferedImage().getHeight()];
		
		//Assigning pixels values of last image(XOR of all images) to 'bufferfinal' array
		for( int i = 0 ; i < image[arrLength-1].bufferedImage().getWidth() * image[arrLength-1].bufferedImage().getHeight() ; i++ ){
			bufferfinal[i] = buffer[arrLength-1][i];
		}
		
		prog.setText("Converting image");
		prog.updateBar(40);
		
		//Sending Values
		image[arrLength-1].setPixels(bufferfinal);
		
		prog.setText("Saving to file");
		prog.updateBar(70);
		
		//Ask for Saving Location
	    chooser = new JFileChooser();
	    chooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "*.png";
			}
			
			@Override
			public boolean accept(File arg0) {
				return arg0.getName().endsWith(".png");
			}
		});
		
		//creating and saving output file to specified location
	    returnVal = chooser.showSaveDialog(c);
	    if(returnVal == JFileChooser.APPROVE_OPTION){
	    	f[arrLength-1] = chooser.getSelectedFile();
	    	String filePath = f[arrLength-1].getPath();
	    	if(!filePath.toLowerCase().endsWith(".png"))
	    	{
	    	    f[arrLength-1] = new File(filePath + ".png");
	    	}
	    }
		
		File saveFile = new File(f[arrLength-1].getAbsolutePath());
		
		ImageIO.write( image[arrLength-1].bufferedImage()  , "png" , saveFile );
		
		prog.updateBar(100);
		System.out.println("Result image saved at : "+saveFile.getAbsolutePath());
		System.exit(0);
		return;
	}
}
