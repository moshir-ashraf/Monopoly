
package com.monopoly.gui;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public final class Service extends Cell implements Serializable {
    String bname;
    int bworth;
    double x,y;
    int imnum;
     public void setIndex(int index) {
        this.index = index;
    }
     public ImageView image() throws FileNotFoundException{
         ImageView image = new ImageView(new Image(new FileInputStream("resources\\img\\service "+imnum+".jpg")));
         image.setFitHeight(300);image.setPreserveRatio(true);
         return image;
     }
    Service(String name, int index, double x, double y){
        this.bname = name;
        this.bworth = 150;
        setIndex(index);
        this.x = x;
        this.y = y;
    }
    public int getrent(Player x,int z){ //z dice rolled by player
        if(x.bcount.size()==1) return z*4;
        if(x.bcount.size()==2) return z*10;
        else return 0;
    }

    @Override
    public String toString() {
        return "Service{" + "bname=" + bname + ", bworth=" + bworth +", index="+index+ '}';
    }
}
