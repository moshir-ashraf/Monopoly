package monopoly.gui;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Dice implements Serializable 
{   
   int x; Image image;

    public Dice(int x) throws FileNotFoundException {
        this.x = x;
        image = new Image(new FileInputStream("C:\\Users\\misho\\OneDrive\\Desktop\\Monopoly\\"+x+".JPG"));
    }
   
    public Dice() throws FileNotFoundException{
         x = (int)(Math.random()*(6)+1);
         image = new Image(new FileInputStream("C:\\Users\\misho\\OneDrive\\Desktop\\Monopoly\\"+x+".JPG"));
    }
    public int getX() 
    {
        return x;
    }

    public ImageView getImage() {
        ImageView im = new ImageView(image);
        im.setFitHeight(80); im.setPreserveRatio(true);
        return im;
    } public static ImageView getImage(int a) throws FileNotFoundException {
      
      Dice d = new Dice(a);
      return d.getImage();
    }

    public int add(Dice y){
    return this.getX() + y.getX();
    }
    public static int rolltostart(){
        return (int)(Math.random()*(6)+1);
    }   
}


