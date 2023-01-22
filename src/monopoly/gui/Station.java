
package monopoly.gui;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Station extends Cell implements Serializable {
    String S_Name;
    final int S_worth;
    double x,y;
    int imnum;
     public void setIndex(int index) {
        this.index = index;
    }
    Station(String S_Name,String index, String x, String y){
         this.S_Name = S_Name;
         this.S_worth =200;
         setIndex(Integer.parseInt(index));
         this.x = Double.parseDouble(x); this.y = Double.parseDouble(y);
     }
    public ImageView image() throws FileNotFoundException{
        ImageView image = new ImageView(new Image(new FileInputStream("C:\\Users\\misho\\OneDrive\\Desktop\\Monopoly\\station "+imnum+".jpg")));
        image.setFitHeight(300);image.setPreserveRatio(true);
        return image;
    }
    public int getrent(Player x){
      if(x.scount.size()==1) return 25;
      if(x.scount.size()==2) return 50;
      if(x.scount.size()==3) return 100;
      if(x.scount.size()==4) return 200;
      else return 0;
    }

    @Override
    public String toString() {
        return "Station{" + "S_Name=" + S_Name + ", S_worth=" + S_worth +", index="+index+ '}';
    }
}
