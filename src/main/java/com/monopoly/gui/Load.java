package com.monopoly.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;
public final class Load implements Currency
{
   static Scanner input = new Scanner(System.in);
    static int numofplayers,maxAt,next;
    static Color propertycolor[] = new Color[8];
    public static boolean Continue() throws ClassNotFoundException{
        System.out.println("\t New Game\tor\t Continue");
        String y = input.nextLine();
        if(y.equalsIgnoreCase("Continue")) return true;
        if((y.equalsIgnoreCase("new game"))||(y.equalsIgnoreCase("newgame"))) return false;
        else {
            System.out.println("\tNew Game is chosen by default");
            return false;
        }
    }
    public static Color[] getcolors (){
        return propertycolor;}
    public static int wish(Alert alert,Property y, Player x, int indexofowner, Label info){
      if(indexofowner == -1){
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(y.PropertyName);
        alert.setHeaderText("Do you wish to buy this property");
        alert.getDialogPane().getButtonTypes().clear();
        ButtonType yes = new ButtonType("BUY");
        ButtonType no = new ButtonType("DECLINE");
        alert.getDialogPane().getButtonTypes().addAll(yes,no);
        alert.getDialogPane().setStyle("-fx-background-color: #f5f7f7; -fx-text-fill: #9b0807; -fx-font-family : 'Algerian';");
        alert.showAndWait();
        if(alert.getResult().equals(yes)){
           x.buy(y);
           info.setText(y.PropertyName + " is successfully bought by "+ x.Name);
           alert.close();
           return 1;
       }
       else { info.setText(y.PropertyName+" was not bought by "+ x.Name);
       alert.close();
           return 0;
       }
      } 
      return -1;    
    }
     public static int wish(Alert alert,Station y, Player x, int indexofowner, Label info){
      if(indexofowner == -1){
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(y.S_Name);
        alert.getDialogPane().setStyle("-fx-background-color: #f5f7f7; -fx-text-fill: #9b0807; -fx-font-family : 'Algerian';");
        alert.setHeaderText("Do you wish to buy this station");
        alert.getDialogPane().getButtonTypes().clear();
        ButtonType yes = new ButtonType("BUY");
        ButtonType no = new ButtonType("DECLINE");
        alert.getDialogPane().getButtonTypes().addAll(yes,no);
        alert.showAndWait();
        if(alert.getResult().equals(yes)){
           x.buy(y);
           info.setText(y.S_Name + " is successfully bought by "+ x.Name);
           alert.close();
           return 1;
       }
       else { info.setText(y.S_Name+" was not bought by "+ x.Name);
       alert.close();
           return 0;
       }
      } 
      return -1;    
    }
     public static int wish(Alert alert,Service y, Player x, int indexofowner, Label info){
      if(indexofowner == -1){
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(y.bname);
        alert.getDialogPane().setStyle("-fx-background-color: #f5f7f7; -fx-text-fill: #9b0807; -fx-font-family : 'Algerian';");
        alert.setHeaderText("Do you wish to buy this station");
        alert.getDialogPane().getButtonTypes().clear();
        ButtonType yes = new ButtonType("BUY");
        ButtonType no = new ButtonType("DECLINE");
        alert.getDialogPane().getButtonTypes().addAll(yes,no);
        alert.showAndWait();
        if(alert.getResult().equals(yes)){
           x.buy(y);
           info.setText(y.bname + " is successfully bought by "+ x.Name);
           alert.close();
           return 1;
       }
       else { info.setText(y.bname+" was not bought by "+ x.Name);
       alert.close();
           return 0;
       }
      } 
      return -1;    
    }
     public static Property[] assign(Property [] x){
         try{
       File n=new File("resources\\Property.txt");
       Scanner in = new Scanner(n);
       String [] cx = new String[22],cy = new String[22],color = new String[22], name = new String[22], worth = new String[22], index = new String[22], rent = new String[22], cost = new String[22], r1 = new String[22], r2 = new String[22], r3 = new String[22], r4 = new String[22], r5 = new String[22];
      for(int i =0; i<22; i++){
          color[i]= in.nextLine();
          name[i] =in.nextLine(); 
          worth[i] =in.nextLine(); 
          index[i]= in.nextLine(); 
          rent[i]= in.nextLine(); 
          cost[i]= in.nextLine(); 
          r1[i]= in.nextLine(); 
          r2[i]= in.nextLine(); 
          r3[i]= in.nextLine(); 
          r4[i]= in.nextLine(); 
          r5[i]= in.nextLine(); 
          cx[i] = in.nextLine();
          cy[i] = in.nextLine();
          x[i] = new Property(color[i],name[i],worth[i],index[i],rent[i],cost[i],r1[i],r2[i],r3[i],r4[i],r5[i],cx[i],cy[i]);
          x[i].imnum = i+1;
      }
      } catch(FileNotFoundException e){}
         return x;
     }
     public static Station[] assign (Station [] x){
           try{
       File n=new File("resources\\Station.txt");
       Scanner in = new Scanner(n);
       String[] name = new String[4], index = new String[4], cx = new String[4], cy = new String[4];
      for(int i =0; i<4; i++){
          name[i] =in.nextLine(); 
          index[i]= in.nextLine(); 
          cx[i] = in.nextLine();
          cy[i] = in.nextLine();
          x[i] = new Station(name[i],index[i],cx[i],cy[i]);
          x[i].imnum = i + 1;
      }
      } catch(FileNotFoundException e){}
           return x;
     }
     public static void assign (double [] x,double [] y){
           try{
       File n=new File("resources\\xy.txt");
       Scanner in = new Scanner(n);
      for(int i =0; i<40; i++){
          x[i] = Double.parseDouble(in.nextLine()); 
          y[i] = Double.parseDouble(in.nextLine());  
      }
      } catch(FileNotFoundException e){System.out.println(e);}
     }
     public static Service[] assign (Service[] x) throws FileNotFoundException{
         x[0] = new Service("SSO's DataBase",12,55.199999999999996,539.1999999999999);
         x[0].imnum= 1;
         x[0].image().setFitHeight(300); x[0].image().setPreserveRatio(true);
         x[1] = new Service("In between Gyro & Sbarro",28,472.79999999999995	,93.6);
         x[1].imnum = 2;
         return x;
     }
     public static void colors (Property [] y){
       Color [] x = new Color[8];
      x[0] = new Color('b',y);
      x[1] = new Color('c',y);
      x[2] = new Color('p',y);
      x[3] = new Color('y',y);
      x[4] = new Color('o',y);
      x[5] = new Color('r',y);
      x[6] = new Color('g',y);
      x[7] = new Color('v',y);
      propertycolor = x;
     }
     public static  void turn(Player[] player, Property property[], Station[] station, Service[] service,Dice a,Dice b,VBox v, Alert t,Label info) throws IOException{
          if(propertyexist(player, property) != -1) {
          v.getChildren().clear();v.getChildren().add(property[propertyexist(player, property)].image());
     if(wish(t,property[propertyexist(player, property)], player[next],  checkforownership(player, property, station, service),info)== -1){
         if(player[checkforownership(player, property, station, service)]==player[next]){
        t = new Alert(AlertType.CONFIRMATION);
        t.setTitle(player[next].Name+"'s Property");
        t.setHeaderText(property[propertyexist(player, property)].PropertyName+ " is already owned by you");
        t.getDialogPane().getButtonTypes().clear();
        ButtonType yes = new ButtonType("UPGRADE");
        ButtonType sell = new ButtonType("SELL");
        ButtonType no = new ButtonType("DECLINE");
        t.getDialogPane().getButtonTypes().addAll(yes,no,sell);
        t.getDialogPane().setStyle("-fx-background-color: #f5f7f7; -fx-font-family : 'Algerian';");
        t.showAndWait();
        if(t.getResult().equals(yes)){
           try{ player[next].buildupgrade(property[propertyexist(player, property)].index,info);}catch(Exception e ){info.setText(property[propertyexist(player, property)].PropertyName+" cannot be upgraded any further");}
        }
        else if(t.getResult().equals(sell)){
            player[next].sell(property[propertyexist(player, property)],property[propertyexist(player, property)].PropertyWorth);
            info.setText(property[propertyexist(player, property)].PropertyName+" was successfully sold back to the university by "+player[next].Name );
        } 
        else {t.close();}
           return;
         }
        t = new Alert(AlertType.CONFIRMATION);
        t.setTitle("RENT");
        t.setHeaderText(property[propertyexist(player, property)].PropertyName+ " is already owned by "+player[ checkforownership(player, property, station, service)].Name);
        t.getDialogPane().getButtonTypes().clear();
        ButtonType yes = new ButtonType("PAY");
        ButtonType no = new ButtonType("EXIT");
        t.getDialogPane().getButtonTypes().addAll(yes,no);
        t.getDialogPane().setStyle("-fx-background-color: #f5f7f7; -fx-text-fill: #9b0807; -fx-font-family : 'Algerian';");
        t.showAndWait();
        if(t.getResult().equals(yes)){
            player[next].payrent(player[checkforownership(player, property, station, service)], player[checkforownership(player, property, station, service)].owned.indexOf(property[propertyexist(player, property)]));
            info.setText(player[next].Name+" payed "+property[propertyexist(player, property)].rent +" to " + player[checkforownership(player, property, station, service)].Name);
        }
        else {t.close(); save(player); System.out.println("BYE BYE");exit(0);}   
    } 
          }
     
     if(stationexist(player, station) != -1) {
    v.getChildren().clear();v.getChildren().add( station[stationexist(player, station)].image());
     if(wish(t,station[stationexist(player, station)], player[next],  checkforownership(player, property, station, service),info)== -1) {
         if(player[checkforownership(player, property, station, service)]==player[next]){
        t = new Alert(AlertType.CONFIRMATION);
        t.setTitle(player[next].Name+"'s Station");
        t.setHeaderText(station[stationexist(player, station)].S_Name+ " is already owned by you");
        t.getDialogPane().getButtonTypes().clear();
        ButtonType sell = new ButtonType("SELL");
        ButtonType no = new ButtonType("DECLINE");
        t.getDialogPane().getButtonTypes().addAll(no,sell);
        t.getDialogPane().setStyle("-fx-background-color: #f5f7f7; -fx-text-fill: #9b0807; -fx-font-family : 'Algerian';");
        t.showAndWait();
        if(t.getResult().equals(sell)){
            player[next].sell(station[stationexist(player, station)],station[stationexist(player, station)].S_worth);
            info.setText(station[stationexist(player, station)].S_Name+" was successfully sold back to the university by "+player[next].Name );
        } 
        else {t.close();}
           return;
         }
         
         t = new Alert(AlertType.CONFIRMATION);
        t.setTitle("RENT");
        t.setHeaderText(station[stationexist(player, station)].S_Name+ " is already owned by "+player[ checkforownership(player, property, station, service)].Name);
        t.getDialogPane().getButtonTypes().clear();
        ButtonType yes = new ButtonType("PAY");
        ButtonType no = new ButtonType("EXIT");
        t.getDialogPane().getButtonTypes().addAll(yes,no);
        t.getDialogPane().setStyle("-fx-background-color: #f5f7f7; -fx-text-fill: #9b0807; -fx-font-family : 'Algerian';");
        t.showAndWait();
        if(t.getResult().equals(yes)){
            player[next].payrent(player[checkforownership(player, property, station, service)], player[checkforownership(player, property, station, service)].scount.indexOf(station[stationexist(player, station)]));
            info.setText(player[next].Name+" paid "+station[stationexist(player, station)].getrent(player[next]) +" to " + player[checkforownership(player, property, station, service)].Name);
        }
        else {t.close(); save(player); System.out.println("BYE BYE");exit(0);}  
    } 
     }
      if(serviceexist(player, service) != -1) {
      v.getChildren().clear(); v.getChildren().add( service[serviceexist(player, service)].image());
     if(wish(t,service[serviceexist(player, service)], player[next],  checkforownership(player, property, station, service),info)== -1) {
        if(player[checkforownership(player, property, station, service)]==player[next]){ 
        t = new Alert(AlertType.CONFIRMATION);
        t.setTitle(player[next].Name+"'s Service");
        t.setHeaderText(service[serviceexist(player, service)].bname+ " is already owned by you");
        t.getDialogPane().getButtonTypes().clear();
        ButtonType sell = new ButtonType("SELL");
        ButtonType no = new ButtonType("DECLINE");
        t.getDialogPane().getButtonTypes().addAll(no,sell);
        t.getDialogPane().setStyle("-fx-background-color: #f5f7f7; -fx-text-fill: #9b0807; -fx-font-family : 'Algerian';");
        t.showAndWait();
        if(t.getResult().equals(sell)){
            player[next].sell(service[serviceexist(player, service)],service[serviceexist(player, service)].bworth);
            info.setText(service[serviceexist(player, service)].bname+" was successfully sold back to the university by "+player[next].Name );
        }
        else {t.close();}
           return;
         }
        t = new Alert(AlertType.CONFIRMATION);
        t.setTitle("RENT");
        t.setHeaderText(service[serviceexist(player, service)].bname+ " is already owned by "+player[ checkforownership(player, property, station, service)].Name);
        t.getDialogPane().getButtonTypes().clear();
        ButtonType yes = new ButtonType("PAY");
        ButtonType no = new ButtonType("EXIT");
        t.getDialogPane().getButtonTypes().addAll(yes,no);
        t.getDialogPane().setStyle("-fx-background-color: #f5f7f7; -fx-text-fill: #9b0807;  -fx-font-family : 'Algerian';");
        t.showAndWait();
        if(t.getResult().equals(yes)){
            player[next].payrent(player[checkforownership(player, property, station, service)], player[checkforownership(player, property, station, service)].bcount.indexOf(service[serviceexist(player, service)]),a.add(b));
            info.setText(player[next].Name+" paid "+service[serviceexist(player, service)].getrent(player[next], a.add(b)) +" to " + player[checkforownership(player, property, station, service)].Name);
        }
        else {t.close(); save(player); System.out.println("BYE BYE");exit(0);}  
     }}
     }
     public static int getnumofplayers(int count){
       try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data\\NumberofPlayers.bin"))) {
                 oos.write(count);
      } catch (IOException ex) {System.out.println(ex);}
         return (numofplayers = count);
     }
     public static Player[] assign(Player[] x,String [] t, Circle [] c, int [] j) throws IOException{
           for(int i = 0; i<numofplayers; i++){
        x[i] = new Player(t[i]);
        x[i].circle_index = j[i];
           } return x;
     }
     public static void whostarts(Player [] x, Label l,HBox h) throws FileNotFoundException{
          int[] who = new int[numofplayers]; Label [] nm = new Label[numofplayers]; VBox [] v = new VBox [numofplayers];
          for(int i = 0; i<numofplayers; i++){  
          who[i] = Dice.rolltostart();
          nm[i] = new Label("Dice rolled by "+x[i].Name);
          nm[i].setTextAlignment(TextAlignment.CENTER); nm[i].setStyle("-fx-text-fill: #a70d12; -fx-font-family : 'Algerian';");
          v[i]= new VBox(nm[i],Dice.getImage(who[i])); v[i].setAlignment(Pos.CENTER);
          h.getChildren().add(v[i]);
          System.out.println(" Dice rolled by "+x[i].Name+" is "+ who[i]);
          }
          
          maxAt = 0;
          for (int i = 0; i < who.length; i++) maxAt = who[i] > who[maxAt] ? i : maxAt; 
          l.setText(x[maxAt].Name+" will start");
          next = -1;
     }
     public static int nextplayer(){
         if(next==3) next = 4;
         if(next==2) next = 3;
         if(next==1) next = 2;
         if(next==0) next = 1;
         if(next>=numofplayers) next = 0;
         if(next==-1) next = maxAt;
         return next;
     }
     public static void leave(){
         System.out.println("\tDo you wish to exit\nYes/No");
         String x = input.nextLine();
         if(x.equalsIgnoreCase("yes")) exit(0);
     }
     public static int propertyexist(Player [] x,Property[] y ){
         for(int i =0; i<22; i++) {if(y[i].index == x[next].move) return i;}
         return -1;
     }
     public static int stationexist(Player [] x,Station[] y ){
         for(int i =0; i<4; i++){ if(y[i].index == x[next].move) return i;}
         return -1;
     }
     public static int serviceexist(Player [] x,Service[] y ){
         for(int i =0; i<2; i++){ if(y[i].index == x[next].move) return i;}
         return -1;
     } 
     public static int checkforownership(Player[] x , Property[] y, Station[] z, Service[] w){
        if(propertyexist(x,y)!= -1) {for(int i = 0; i<numofplayers; i++) {if(x[i].owned.contains(y[propertyexist(x, y)])) { return i; }}}    
        if(stationexist(x,z)!= -1){ for(int i = 0; i<numofplayers; i++){ if(x[i].scount.contains(z[stationexist(x, z)])) {return i;}}}
        if(serviceexist(x,w)!= -1) {for(int i = 0; i<numofplayers; i++){ if(x[i].bcount.contains(w[serviceexist(x, w)])){ return i;}}}
        return -1;
     }
     public static void notabuilding(Player[] x, int num, Alert t, Label info){
         double cx,cy;
        if((x[next].move == 2)||(x[next].move == 17)||(x[next].move == 33)){
            if(x[next].move == 2){cx = 472.79999999999995;cy=671.1999999999999;} 
            if(x[next].move == 17){cx = 55.199999999999996;cy=276.0;} 
            if(x[next].move == 33){cx =588.0;cy=276.0 ;}
             t.setTitle("Community Chest Card");
             t.getDialogPane().setContent(Community.pic[num]);
             t.getDialogPane().setStyle(" ;-fx-background-color :#91ccea;-fx-text-fill: #000000; -fx-font-family : 'Algerian';");
             t.show();
             Community.drawcard(x[next], x ,numofplayers,num);
        }
        if((x[next].move == 7)||(x[next].move == 22)||(x[next].move == 36)) {
            if(x[next].move == 7){cx =225.6;cy=669.5999999999999;} 
            if(x[next].move == 22){cx =77.6;cy=98.39999999999999;} 
            if(x[next].move == 36){cx =590.4;cy=435.2;}
             t.setTitle("Chance Card");
             t.getDialogPane().setContent(Chance.pic[num]);
             t.getDialogPane().setStyle("-fx-background-color :#ef7e2e;-fx-text-fill: #000000; -fx-font-family : 'Algerian';");
             t.show();
             Chance.drawcard(x[next], x ,numofplayers,num,info);
        }
        if(x[next].move ==4)  {
            cx = 373.59999999999997; cy = 669.5999999999999;
        x[next].collect(-Entry);
        t = new Alert(AlertType.INFORMATION);
        t.setHeaderText("   ");
        t.setContentText(x[next].Name+" paid entry tax and now has "+x[next].Balance);
        t.setTitle("DEBT COLLECTOR");
        t.getDialogPane().setStyle("-fx-background-color:#ef7e2e;-fx-text-fill: #000000; -fx-font-family : 'Algerian';");
        t.show();
        }
        if(x[next].move ==38) {
           cx = 592.0; cy = 540.8; 
        x[next].collect(-Iced_coffee);
        t = new Alert(AlertType.INFORMATION);
        t.setHeaderText("   ");
        t.setContentText(x[next].Name+" bought an Iced Coffee and now has "+x[next].Balance);
        t.setTitle("DEBT COLLECTOR");
        t.getDialogPane().setStyle("-fx-text-fill: #000000; -fx-font-family : 'Algerian';");
        t.show();
        }
        if(x[next].move ==10) {
            cx =73.6; cy = 645.5999999999999;
            info.setText(x[next].Name+" is visiting during summer course" );
        }
        if(x[next].move ==30){
            cx = 586.4; cy =100.8;
            x[next].gotosummercourse();
        }
        if(x[next].move ==20) {
            cx =58.4; cy = 99.2;
            info.setText(x[next].Name+" is chilling at the balaa'a" );
        }  
     }
     public static boolean game(Player[]x, Pane p, VBox [] v){  
         Alert t = new Alert(AlertType.INFORMATION);
         t.setTitle("EXPELLED");
         t.setHeaderText("BYE-BYE");
         t.getDialogPane().setStyle("-fx-text-fill: #000000; -fx-font-family : 'Algerian';");
       for (Player x1 : x) {
           if (x1.Balance <= 0) {
               t.setContentText(x1.Name + " is expelled");
               t.showAndWait();
               int i,j;
               for (i = 0, j = 0; j < x.length; j++) {
                   if (!x[j].equals(x1)) {
                       x[i++] = x[j];
                   }
                   else{
                      v[j].getChildren().clear(); 
                      p.getChildren().get(j).setVisible(false);
                   }
               } 
               x = Arrays.copyOf(x, i);
           }
       } 
         getnumofplayers(x.length);
       if(x.length<2){ t.setContentText("Game has ended\n"+x[0].Name+" has won"); t.showAndWait();  exit(0); return false;}
        return true;
     }
     public static void save(Player[]  x) throws IOException{
      try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data\\Players.bin"))) {
               for(int i=0; i<numofplayers; i++)  oos.writeObject(x[i]);
      } catch (IOException ex) {System.out.println(ex);}
     }
     
     public static Player[] load(Player[] x) throws  ClassNotFoundException{
        numofplayers=getnumber();
      try ( FileInputStream fis = new FileInputStream("data\\Players.bin");
          ObjectInputStream ois = new ObjectInputStream(fis)) {
         for(int i =0; i<numofplayers; i++) x[i] = (Player)ois.readObject();
          ois.close(); fis.close();
      } catch (IOException ex) {System.out.println(ex);} {
       }
      return x;
     }
     
     public static int getnumber(){
          try ( ObjectInputStream os = new ObjectInputStream(new FileInputStream("data\\NumberofPlayers.bin"))) {
                 numofplayers= os.read();
      } catch (IOException ex) {System.out.println(ex);}
     return numofplayers;
     }
     public static void summer(Player [] player,Dice a, Dice b, Label info) throws FileNotFoundException{
         if(player[next].injail>0){
         player[next].gotosummercourse();
         info.setText("Turn is skipped");
         a = new Dice(); b = new Dice();
         player[nextplayer()].move(a,b,info);
     }
     }
    
}

