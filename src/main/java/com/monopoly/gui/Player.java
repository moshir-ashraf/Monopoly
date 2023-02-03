package com.monopoly.gui;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;
public class Player implements Serializable  {
    int prev;
     int Balance ;
     String Name;
     int move;
     Color [] colors;
     ArrayList<Property> owned = new ArrayList<>();
     ArrayList<Station> scount = new ArrayList<>();
     ArrayList<Service> bcount = new ArrayList<>();
     int injail;
     int circle_index;
     boolean passcard = false;
    Player(String Name){
         this.Name = Name;
         Balance = 1300;
         move = 0;
         colors = Load.getcolors();
         injail = 0;
         prev = 0;
     }

    public Circle getCircle(Circle[] c) {
        c[circle_index].setRadius(35);
        return c[circle_index];
    }
     public void collect(int x){
         Balance +=x;
     }

     public void sell (Property a, int sellfor){
       this.owned.remove(a);
       this.Balance += sellfor;
       for(int i=0; i<8; i++){
             if(colors[i].colored.contains(a)) {colors[i].owned--;}
         }
     }
      public void sell ( Station a, int sellfor){
       this.scount.remove(a);
       this.Balance += sellfor;
     }
      public void sell (Service a, int sellfor){
       this.bcount.remove(a);
       this.Balance += sellfor;
     }
     public void buy (Property a){
         owned.add(a);
         this.Balance -= a.PropertyWorth;
         for(int i=0; i<8; i++){
             if(colors[i].colored.contains(a)) colors[i].owned++;
         }
     }
     public void buy (Station a){
         scount.add(a);
         this.Balance -= a.S_worth;
     }
     public void buy (Service a){
         bcount.add(a);
         this.Balance -= a.bworth;
     }
     public void payrent(Player x, int index){
        this.Balance -= x.owned.get(index).rent;
        x.Balance += x.owned.get(index).rent;
         System.out.println(Name+" paid rent for "+x.Name);
     }
     public void payrent(Player x, String index){
       this.Balance -= x.scount.get(Integer.parseInt(index)).getrent(x);
         x.Balance += x.scount.get(Integer.parseInt(index)).getrent(x);
         System.out.println(Name+" paid rent for "+x.Name);
     }
     public void payrent(Player x, int index, int dicerolled){
       this.Balance -= x.bcount.get(index).getrent(x,dicerolled);
        x.Balance += x.bcount.get(index).getrent(x,dicerolled);
         System.out.println(Name+" paid rent for "+x.Name);
     }
     public void buildupgrade(int index, Label l){
         int z=-1;
         for(int i=0; i<owned.size();i++){
             if(index==owned.get(i).index) z = i;
         }
         if(owned.contains(owned.get(z))) {
         owned.get(z).count++;
         owned.get(z).buildroom(this);
         Balance-= owned.get(z).cost;
         l.setText(owned.get(z).PropertyName+" is successfully upgraded");
     } else l.setText(Name+" must buy property before attempting to upgrade");
     }
     public void removeupgrade(int index, Label l){
         if((index>=owned.size())){l.setText(Name+" does not own this property");
         return;
      }
          if(owned.contains(owned.get(index))) {
         owned.get(index).removeroom(l);
         owned.get(index).count--;
         Balance+= owned.get(index).cost;
     }l.setText(Name+" must buy property before attempting to remove upgrade");
     }
     public void payperupgrade(int a,int b){
         int tr=0,tf=0;
         for (Property property : owned) {
             tr += property.count;
             if (property.maxup) {
                 tf++;
                 tr--;
             }
         }
         Balance -= (a*tr);
         Balance -= (b*tf);
     }
     public void move(Dice a, Dice b, Label l){
         prev = move;
         if(move>39) move -= 40;
         if(move == 0 ) {collect(Currency.Entry); l.setText(Name+" scanned ID");}
         move += a.add(b);
         if(move>39) move -= 40;
         if(move<prev) {collect(Currency.Entry); l.setText(Name+" scanned ID");}
         l.setText(l.getText()+"\n"+this.Name +" moved to "+ move);
     }
     public void moveto(int index, Label l){
         prev = move;
         if(move>39) move -= 40;
         move = index;
         if(move ==0 ) {collect(Currency.Entry); l.setText(Name+" scanned ID");}
         if(move<prev) {collect(Currency.Entry); l.setText(Name+" scsnned ID");}
         l.setText(l.getText()+" "+this.Name +" moved to "+ move);
     }
     public void gotosummercourse(){
        Alert t = new Alert(AlertType.NONE);
        t.getDialogPane().setStyle("-fx-background-color: #ef7e2e;-fx-text-fill: #000000; -fx-font-family : 'Algerian';");
        t.setTitle("Summer Course");
        t.setHeaderText("F");
         if(!passcard){
          t.setAlertType(AlertType.WARNING);
         if(injail>2) {getoutofsummercourse(); return;}
         else if(injail == 0){
         t.setContentText(this.Name +", you failed, go to summer course, you forgot your ID");
         injail++;
         }
         else {
             injail++;
             t.setContentText(this.Name+" still hasn't passed their summer course");  
         }
         t.show();
       } else{
             t.setAlertType(AlertType.CONFIRMATION);
             t.setContentText("You have a free pass");
             t.getDialogPane().getButtonTypes().clear();
             ButtonType use = new ButtonType("USE");
             ButtonType wait = new ButtonType("FAIL");
             t.getDialogPane().getButtonTypes().addAll(use,wait);
             t.getDialogPane().setStyle("-fx-background-color: #ef7e2e;-fx-text-fill: #000000; -fx-font-family : 'Algerian';");
             t.show();
             if(t.getResult().equals(use)){t.close(); passcard = false; getoutofsummercourse();}
              else{
                 if(injail>2) {getoutofsummercourse(); return;}
                 else if(injail == 0){
                 t.setContentText(this.Name +", you failed, go to summer course, you forgot your ID");
                injail++;
                }
             else {
                 injail++;
                 t.setContentText(this.Name+" still hasn't passed their summer course");  
                }
              t.close();
             } 
         }
     }
     public void getoutofsummercourse(){
        Alert t = new Alert(AlertType.INFORMATION);
        t.getDialogPane().setStyle("-fx-background-color: #ef7e2e;-fx-text-fill: #000000; -fx-font-family : 'Algerian';");
        t.setTitle("Summer Course");
        t.setHeaderText("PASSED");
         if(injail >0) {
             t.setContentText(this.Name +" passed summer course, and will re-enroll in the next semester");
             injail = 0;
         }
         else t.setContentText(this.Name +" is not attending the summer course");
         t.show();
     }

    @Override
    public String toString() {
        return "Player{" + "Balance=" + Balance + ", Name=" + Name + ", move=" + move + '}';
    }
    void animation(Pane m, double [] x, double [] y){
        if(prev>39) prev -= 40;
        double cx, cy;
        TranslateTransition transition = new TranslateTransition();
       transition.setNode( m.getChildren().get(Load.next));
        cx = m.getChildren().get(Load.next).getLayoutX()-x[prev];
       cy = m.getChildren().get(Load.next).getLayoutY()-y[prev];
       transition.setFromX( -cx);
       transition.setFromY( -cy);
       cx = m.getChildren().get(Load.next).getLayoutX()-x[move];
       cy = m.getChildren().get(Load.next).getLayoutY()-y[move];
       transition.setToX(-cx); transition.setToY(-cy);
       transition.setDuration(Duration.seconds(2));
       transition.play();
       m.getChildren().get(Load.next).setLayoutX(x[move]);
       m.getChildren().get(Load.next).setLayoutY(y[move]);

    }
}
