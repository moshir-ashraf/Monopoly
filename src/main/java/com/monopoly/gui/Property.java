
package com.monopoly.gui;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public final class Property extends Cell implements Serializable {
    char color;
    int imnum;
    String PropertyName;
    int PropertyWorth;
    int rent;
    int count; //number of upgrades
    int cost;
    double x,y;
    Upgrade room1,room2,room3,room4,floor;
    boolean maxup;
    public ImageView image() throws FileNotFoundException{
        ImageView image = new ImageView(new Image(new FileInputStream("resources\\img\\Property card "+imnum+".jpg")));
        image.setFitHeight(300);image.setPreserveRatio(true);
        return image;
    }
    
    @Override
    public String toString() {
        return "Property{" + "color=" + color + ", PropertyName=" + PropertyName + ", PropertyWorth=" + PropertyWorth + ", rent=" + rent +", index="+index+ '}';
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    Property(String color, String PropertyName, String PropertyWorth,String index,String rent, String cost,String r1,String r2, String r3, String r4, String r5, String x, String y){
        this.color = color.charAt(0);
        this.PropertyName = PropertyName;
        this.PropertyWorth = Integer.parseInt(PropertyWorth);
        setIndex(Integer.parseInt(index));
        this.rent = Integer.parseInt(rent);
        this.cost= Integer.parseInt(cost);
        room1 = new Upgrade(cost,r1); 
        room2 = new Upgrade(cost,r2);
        room3 = new Upgrade(cost,r3);
        room4 = new Upgrade(cost,r4);
        floor = new Upgrade(cost,r5);  
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        count =0;
    } 
    public void buildfloor(){
        rent += floor.rent_addition; 
        maxup=true;
    }
    public void buildroom(Player x){
       int pos=-1;
       for(int i=0;i<8;i++) if(x.colors[i].colored.contains(this)) pos=i;
       if(count == 1) rent += room1.rent_addition;
       if(count>1){ if(x.colors[pos].canupgrade(x)){
              if(!maxup){
       if(count == 2) rent += room2.rent_addition;
       if(count == 3) rent += room3.rent_addition;
       if(count == 4) rent += room4.rent_addition;
       if(count > 4) buildfloor();
        } else  System.out.println("No more upgrades to build");
       } else System.out.println("Can't upgrade property any further without upgrading other properties of color "+x.colors[pos].color);
       }
    }
    public void removefloor(){
        rent -= floor.rent_addition;
        maxup=false;
    }
    public void removeroom(Label l){
       if(count == 1) rent -= room1.rent_addition;
       if(count == 2) rent -= room2.rent_addition;
       if(count == 3) rent -= room3.rent_addition;
       if(count == 4) rent -= room4.rent_addition;
       if(count > 4) removefloor();
       else l.setText("No more upgrades to remove");
    }
}
