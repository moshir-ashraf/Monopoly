package monopoly.gui;
import java.io.*;
import java.util.*;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Community extends Cell{
    static String [] community =new String[16];
    static ImageView [] pic = new ImageView[16];
    static int [] arr=new int[16];
    public static void read() {
      try{
          File n=new File("C:\\Users\\misho\\OneDrive\\Desktop\\Monopoly\\Community Card.txt"); 
       Scanner in = new Scanner(n);
       int i=0;
       while(in.hasNextLine())
       {
         community[i] = in.nextLine();
         pic[i] = new ImageView(new Image(new FileInputStream("C:\\Users\\misho\\OneDrive\\Desktop\\Monopoly\\R"+i+".JPG")));   
         pic[i].setFitWidth(400); pic[i].setPreserveRatio(true);
         i++; }
      } catch(FileNotFoundException e){}
      arr[0]= Entry;
      arr[1]=Entry;
      arr[2]=-Redbull;
      arr[3]=Redbull;
      arr[4]=0;
      arr[5]=-Entry;
      arr[6]=Iced_coffee;
      arr[7]=OrangeFromFarghaly;
      arr[8]=-1000;
      arr[9]=Iced_coffee;
      arr[10]=-Iced_coffee;
      arr[11]=-Redbull;
      arr[12]=OrangeFromFarghaly+Water;
      arr[13]=-1000;
      arr[14]=SarookhFromShabrawy;
      arr[15]=Iced_coffee;
    } 
   
    public static void drawcard(Player x, Player y[], int n, int num){
       System.out.println(x.Name+" drew a Community Chest Card "); 
       System.out.println(community[num]);
       if ((num!=8)&&(num!=13)&&(num!=4)) {x.collect(arr[num]); System.out.println(x.Name+" new balance is "+x.Balance);}
       if (num == 13){ x.payperupgrade((2*OrangeFromFarghaly), (Iced_coffee + Water + SarookhFromShabrawy)); System.out.println(x.Name+" new balance is "+x.Balance);}
       if(num ==4 ){ x.passcard=true; x.getoutofsummercourse(); }
       if(num == 8) {
          x.collect(SarookhFromShabrawy*(n-1));
        for(int i =0; i<n; i++) if(x.toString().equals(y[i].toString())!=true){ y[i].collect(-SarookhFromShabrawy);System.out.println(y[i].Name+" new balance is "+y[i].Balance); }
        System.out.println(x.Name+" new balance is "+x.Balance); 
       }
    }
}

