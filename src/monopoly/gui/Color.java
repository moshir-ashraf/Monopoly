
package monopoly.gui;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
public final class Color implements Serializable{
    int num,owned; //num is the number of properties of the same color
    String color;
    ArrayList<Property> colored = new ArrayList<>();
    public Color(char color, Property [] p){
       if (color == 'b'){ this.color = "Brown"; num = 2;}
       if (color == 'p'){ this.color = "Pink"; num =3;}
       if (color == 'c'){ this.color = "Cyan"; num =3;}
       if (color == 'r'){ this.color = "Red"; num =3;}
       if (color == 'v'){ this.color = "Violet"; num =2;}
       if (color == 'o'){ this.color = "Orange"; num =3;}
       if (color == 'y'){ this.color = "Yellow"; num =3;}
       if (color == 'g'){ this.color = "Green"; num =3;}
       for(int i=0; i<22;i++) if(color == p[i].color) colored.add(p[i]);
       owned =0;
    }
    public boolean canupgrade(Player x){
        if(owned == num){
       int[] pos = new int[num];
       for (int i = 0; i < 22; i++){
          for(int j=0;j<num;j++){
              if(x.owned.get(i)==colored.get(j))
                 Array.set(pos, j, i);
          }
    }  int counted=x.owned.get(pos[0]).count;
       for(int i=1; i<num; i++){
               counted = Integer.min(x.owned.get(pos[i]).count, counted);
          }
       return (counted > 0);
        } else return false;
}

    @Override
    public String toString() {
        return "Color{" + "color=" + color + ", colored=" + colored + '}';
    }
}


