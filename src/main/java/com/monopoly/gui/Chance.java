package com.monopoly.gui;
import java.io.*;
import java.util.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Chance extends Cell{
    static String [] chances =new String[16];
    static ImageView [] pic = new ImageView[16];
    public static void read() {
      try{
          File n=new File("resources\\Chance cards.txt");
       Scanner in = new Scanner(n);
       int i=0;
       while(in.hasNextLine())
       {
         chances[i] = in.nextLine();
         pic[i] = new ImageView(new Image(new FileInputStream("resources\\img\\L"+ i +".jpg")));
         pic[i].setFitWidth(400); pic[i].setPreserveRatio(true);
         i++; }
      } catch(FileNotFoundException e){}
    }
   
    public static void drawcard(Player x, Player[] y, int n, int num, Label d){
         System.out.println(x.Name+" drew a Chance Card "); 
         System.out.println(chances[num]);
         if(num == 0) x.moveto(39,d);
         if(num == 1) x.moveto(0,d);
         if(num == 2) x.moveto(24,d);
         if(num == 3) x.moveto(11,d);
         if((num == 4)||(num==5)){
             int[] a = new int[]{ 5,15,25,35 }; 
             int l =a[0];
             for(int i =0; i<4; i++){ 
                if(Math.abs(x.move-a[i])<Math.abs(x.move-l)) l = a[i];
             }
               x.moveto(l,d);
             }
         if(num == 6) x.moveto(12,d);
         if(num == 7) {x.collect(Redbull); System.out.println(x.Name+" new balance is "+x.Balance);}
         if(num == 8) {x.passcard=true; x.getoutofsummercourse();}
         if(num == 9) x.moveto(x.move-3,d);
         if(num == 10) x.moveto(30,d);
         if(num == 11) x.payperupgrade(OrangeFromFarghaly + Water, Iced_coffee);
         if(num == 12) {x.collect(-(SarookhFromShabrawy+Water));System.out.println(x.Name+" new balance is "+x.Balance);}
         if(num == 13) x.moveto(5,d);
         if(num == 14) {
         x.collect(-Redbull*(n-1));
        for(int i =0; i<n; i++) {if(!x.toString().equals(y[i].toString())) {y[i].collect(Redbull);System.out.println(y[i].Name+" new balance is "+y[i].Balance);}}
        System.out.println(x.Name+" new balance is "+x.Balance);
       }
         if(num == 15) {x.collect(Redbull+Iced_coffee);System.out.println(x.Name+" new balance is "+x.Balance);}
    }
}

