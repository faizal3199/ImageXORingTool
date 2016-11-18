package ImageXORingTool;

import java.awt.image.BufferedImage;

public class AdvancedBufferedImage {

	BufferedImage bi;
	
	public void AdvancedBufferedImageGet(BufferedImage bi){
		this.bi = bi;		
	}
	
	public BufferedImage bufferedImage(){
		return bi;
	}
	
	public int[] getPixels(){
	
		int[] buf = new int[ bi.getWidth() * bi.getHeight() ];
		   
		for( int i = 0 ; i < bi.getWidth() ; i++ )
			for( int j = 0 ; j < bi.getHeight() ; j++ )
				buf[ i * bi.getHeight() + j ] = bi.getRGB(i, j);
		   
		return buf;
	}
	
	public void setPixels(int[] buf){
		
	  for( int i = 0 ; i < bi.getWidth() ; i++ )
		  for( int j = 0 ; j < bi.getHeight() ; j++ )
			  bi.setRGB(i, j,  buf[ i * bi.getHeight() + j ]);
		
	}
	
	public int getPixelsBufferSize(){
		return bi.getHeight() * bi.getWidth();
	}

}
