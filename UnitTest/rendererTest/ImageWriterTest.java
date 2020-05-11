package rendererTest;
import geometries.*;
import primitives.*;
import elements.*;
import renderer.*;
import org.junit.Test;
import java.awt.Color;
import static org.junit.Assert.*;


public class ImageWriterTest {

    @Test
    public void writeToImage() {
        ImageWriter imageWriter = new ImageWriter("My picture", 1000, 1600, 500, 800);
        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                if (i % 50 == 0 || j % 50 == 0) {
                    imageWriter.writePixel(j, i, Color.green );
                }
                else {
                    imageWriter.writePixel(j, i, Color.blue);
                }
            }
        }
        imageWriter.writeToImage();
    }
}