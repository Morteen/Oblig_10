
import ij.ImagePlus;
import ij.plugin.filter.Binary;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;


public class Roter_Farger implements PlugInFilter {

    public int setup(String arg, ImagePlus im) {
        return DOES_RGB;
    }

    public void run(ImageProcessor ip) {
       int [] pixels = (int[]) ip.getPixels();

       for (int i = 0; i < pixels.length; i++) {
       	int c = pixels[i];
       	int r = (c & 0xff0000) >> 16; 
       	int g = (c & 0x00ff00) >> 8; 
       	int b = (c & 0x0000ff); 

       	int red = g; 
       	int green = b;
       	int blue = r; 

       	pixels[i] = ((red & 0xff)<<16) | ((green & 0xff)<<8) | blue & 0xff;
       }

    }
   

}//slutt på klassen
