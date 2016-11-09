
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;
import ij.gui.GenericDialog;
/**
 * Oppgave 2 i Oblig 10
 * Den er bare skrevet av fra boken, bortsett fra en egen dialog boks for sCol variablen
 * @author Tine og morten
 */



public class Desaturate_Rgb implements PlugInFilter {

    static double sCol;

    public int setup(String arg, ImagePlus im) {
        return DOES_RGB;
    }

    public void run(ImageProcessor ip) {
        sCol = hentVerdi();
        for (int y = 0; y < ip.getHeight(); y++) {
            for (int x = 0; x < ip.getWidth(); x++) {

                int c = ip.getPixel(x, y);

                int r = (c & 0xff0000) >> 16;
                int g = (c & 0x00ff00) >> 8;
                int b = (c & 0x0000ff);

                double grey = 0.299 * r + 0.587 * g + 0.114 * b;

                r = (int) (grey + sCol * (r - grey));
                g = (int) (grey + sCol * (g - grey));
                b = (int) (grey + sCol * (b - grey));

                c = ((r & 0xff) << 16) | ((g & 0xff) << 8) | b & 0xff;
                ip.putPixel(x, y, c);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////HjelpeMetoder///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * Henter verdien brukeren ¯nsker Â bruke pÂ bildet
 * @return Veriden fra dialog boksen i form av en double
 */
    private static double hentVerdi() {
        double returVerdi;
        do {
            GenericDialog hentData = new GenericDialog("Verdien mÂ vÊre mellom 0.0 og 1");
            hentData.addNumericField("Saturation verdi: ", 0.0, 2);
            hentData.showDialog();
            returVerdi = (double) hentData.getNextNumber();

        } while (returVerdi < 0.0 || returVerdi > 1);

        return returVerdi;

    }

}//slutt p√• klassen
