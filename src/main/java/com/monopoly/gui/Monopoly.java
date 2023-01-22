package com.monopoly.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.System.exit;

import java.util.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Monopoly extends Application
{
    public static double[] sx = new double[40], sy = new double[40];
    public static Dice a, b;
    public static Stage myStage;
    public static Circle[] circles = new Circle[4];
    public static Player[] player;
    public static Property[] property = new Property[22];
    public static Station[] station = new Station[4];
    public static Service[] service = new Service[2];
    public static Button end = new Button("Drop out of semester");

    static
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                Monopoly.myStage = new Stage();
            }
        });
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        initialize();
        property = Load.assign(property);
        station = Load.assign(station);
        service = Load.assign(service);
        Load.colors(property);
        Community.read();
        Chance.read();
        Load.assign(sx, sy);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException
    {
        primaryStage = startpage(myStage);
    }

    public static void setdimensions(HBox h)
    {
        h.setSpacing(15);
        h.setPadding(new Insets(15));
        h.setAlignment(Pos.CENTER);
    }

    public static void setdimensions(VBox v)
    {
        v.setSpacing(15);
        v.setPadding(new Insets(15));
        v.setAlignment(Pos.CENTER);
    }

    void setcolor(Button x)
    {
        x.setStyle("-fx-background-color: #f5f7f7; -fx-text-fill: #9b0807; -fx-font-size: 2em; -fx-font-family : 'Algerian'");
    }

    public static void setcolor(CheckBox[] x)
    {
        for (CheckBox x1 : x)
        {
            x1.setStyle("-fx-text-fill: #a70d12; -fx-font-size: 1.4em; -fx-font-family : 'Algerian';");
        }
    }

    void setcolor(Label[] x)
    {
        for (Label x1 : x)
        {
            x1.setStyle("-fx-text-fill: #a70d12; -fx-font-size: 2em; -fx-font-family : 'Algerian';");
        }
    }

    public Stage startpage(Stage primaryStage) throws FileNotFoundException
    {
        primaryStage.setTitle("Start");
        Image logo = new Image(new FileInputStream("resources\\img\\logobeda2y.jpg"));
        ImageView st_logo = new ImageView(logo);
        st_logo.setFitHeight(400);
        st_logo.setFitWidth(550);
        Button newgame = new Button("Enroll for a new semester");
        Button load = new Button("Continue the current semester");
        setcolor(load);
        setcolor(end);
        setcolor(newgame);
        end.setOnAction(e -> {
            primaryStage.close();
            exit(0);
        });
        VBox V = new VBox(st_logo, newgame, load, end);
        setdimensions(V);
        Scene startup = new Scene(V, 1000, 750);
        primaryStage.setScene(startup);
        primaryStage.show();
        V.setStyle("-fx-background-color: #dcfbcb ");
        newgame.setOnAction(e -> {
            primaryStage.close();
            setplayer(myStage).show();
        });
        try
        {
            load.setOnAction(e -> {
                player = new Player[Load.getnumber()];
                try
                {
                    player = Load.load(player);
                    primaryStage.close();
                    game(myStage).show();
                } catch (FileNotFoundException | ClassNotFoundException ex)
                {
                    System.out.println(ex);
                }
            });
        } catch (Exception e)
        {
            newgame.fire();
        }
        startup.setOnKeyPressed(value -> {
            KeyCode key = value.getCode();
            if (key == KeyCode.ESCAPE)
                end.fire();
            if (key == KeyCode.C)
                load.fire();
            if (key == KeyCode.N)
                newgame.fire();
            if (key == KeyCode.ENTER)
                newgame.fire();
        });
        return primaryStage;
    }

    public Stage setplayer(Stage stage)
    {
        stage.setTitle("Student Application Form");
        TextField[] txt = new TextField[4];
        CheckBox[] num = new CheckBox[4];
        Button next = new Button("Next");
        Button back = new Button("Back");
        back.setOnAction(e -> {
            stage.close();
            try
            {
                startpage(myStage);
            } catch (FileNotFoundException ex)
            {
            }
        });
        initialize(txt);
        num[0] = new CheckBox("Pink Player");
        num[1] = new CheckBox("Yellow Player");
        num[2] = new CheckBox("Green Player");
        num[3] = new CheckBox("Blue Player");
        Label label = new Label("Please select number of students");
        label.setStyle("-fx-text-fill: #a70d12; -fx-font-size: 2em; -fx-font-family : 'Algerian';");
        VBox[] v = new VBox[5];
        v[0] = new VBox(num[0], circles[0], txt[0]);
        v[1] = new VBox(num[1], circles[1], txt[1]);
        v[2] = new VBox(num[2], circles[2], txt[2]);
        v[3] = new VBox(num[3], circles[3], txt[3]);
        HBox h = new HBox(v[0], v[1], v[2], v[3]);
        HBox bt = new HBox(back, next);
        setdimensions(bt);
        bt.setSpacing(750);
        v[4] = new VBox(label, h, bt);
        for (int i = 0; i < 5; i++) setdimensions(v[i]);
        setdimensions(h);
        next.setOnAction(e -> {
            try
            {
                if (number(num, txt))
                {
                    stage.close();
                    game(myStage).show();
                }
            } catch (IOException ex)
            {
                System.out.println(ex);
            }
        });
        Scene scene = new Scene(v[4], 1000, 750);
        scene.setOnKeyPressed(value -> {
            KeyCode key = value.getCode();
            if (key == KeyCode.ESCAPE)
                back.fire();
            if (key == KeyCode.N)
                next.fire();
            if (key == KeyCode.DIGIT1)
            {
                if (num[0].isSelected()) num[0].setSelected(false);
                else
                {
                    num[0].setSelected(true);
                    txt[0].requestFocus();
                }
            }
            if (key == KeyCode.DIGIT2)
            {
                if (num[1].isSelected()) num[1].setSelected(false);
                else
                {
                    num[1].setSelected(true);
                    txt[1].requestFocus();
                }
            }
            if (key == KeyCode.DIGIT3)
            {
                if (num[2].isSelected()) num[2].setSelected(false);
                else
                {
                    num[2].setSelected(true);
                    txt[2].requestFocus();
                }
            }
            if (key == KeyCode.DIGIT4)
            {
                if (num[3].isSelected()) num[3].setSelected(false);
                else
                {
                    num[3].setSelected(true);
                    txt[3].requestFocus();
                }
            }
        });
        v[4].setStyle("-fx-background-color: #dcfbcb ");
        setcolor(next);
        setcolor(back);
        setcolor(num);
        stage.setScene(scene);
        stage.show();
        return stage;
    }

    void initialize(Label[] l)
    {
        for (int i = 0; i < l.length; i++) l[i] = new Label();
    }

    static void initialize()
    {
        for (int i = 0; i < circles.length; i++) circles[i] = new Circle(1 * i, 0, 50);
        circles[0].setFill(javafx.scene.paint.Color.rgb(245, 39, 108));
        circles[1].setFill(javafx.scene.paint.Color.rgb(255, 192, 66));
        circles[2].setFill(javafx.scene.paint.Color.rgb(15, 122, 67));
        circles[3].setFill(javafx.scene.paint.Color.rgb(9, 129, 173));

    }

    void initialize(TextField[] t)
    {
        for (int i = 0; i < t.length; i++) t[i] = new TextField();
    }

    boolean number(CheckBox[] c, TextField[] t) throws IOException
    {
        int count = 0;
        int j[] = new int[4];
        for (int i = 0; i < c.length; i++)
        {
            if (c[i].isSelected())
            {
                j[count] = i;
                count++;
            }
        }
        if (count < 2)
        {
            Alert less = new Alert(AlertType.WARNING);
            less.setTitle("Caution");
            less.setHeaderText("Not enough students to kick off the semester");
            less.setContentText("Please select more than 2 students");
            less.show();
            return false;
        }
        else
        {
            Load.getnumofplayers(count);
            player = new Player[count];
            String names[] = new String[count];
            for (int i = 0; i < count; i++)
            {
                names[i] = t[j[i]].getText();
            }
            Load.assign(player, names, circles, j);
            return true;
        }
    }

    Stage game(Stage stage) throws FileNotFoundException
    {
        Pane move = new Pane();
        Circle[] moveable = new Circle[Load.numofplayers];
        stage.setTitle("Monopoly");
        Image image = new Image(new FileInputStream("resources\\img\\board.JPG"));
        ImageView board = new ImageView(image);
        board.setFitHeight(700);
        board.setFitWidth(650);
        StackPane play = new StackPane(board, move);
        Button roll = new Button("Roll");
        setcolor(roll);
        Label info = new Label();
        info.setStyle("-fx-text-fill: #a70d12; -fx-font-family : 'Algerian';");
        info.setTextAlignment(TextAlignment.CENTER);
        HBox die = new HBox();
        Load.whostarts(player, info, die);
        Label[] label = new Label[Load.numofplayers];
        VBox card = new VBox();
        card.setAlignment(Pos.CENTER);
        setdimensions(die);
        Alert cc = new Alert(AlertType.INFORMATION);
        cc.getDialogPane().getButtonTypes().clear();
        ButtonType ok = new ButtonType("OKEAH !");
        cc.getDialogPane().getButtonTypes().add(ok);
        cc.setGraphic(null);
        cc.setHeaderText("  ");
        VBox v[] = new VBox[Load.numofplayers];
        Button exit = new Button("Exit");
        setcolor(exit);
        roll.setOnAction(e -> {

            if (Load.game(player, move, v))
            {
                try
                {
                    card.getChildren().clear();
                    a = new Dice();
                    b = new Dice();
                    die.getChildren().clear();
                    die.getChildren().addAll(a.getImage(), b.getImage());
                    info.setText(player[Load.nextplayer()].Name + "'s turn");
                    Load.summer(player, a, b, info);
                    player[Load.next].move(a, b, info);
                    player[Load.next].animation(move, sx, sy);
                    int num = (int) (Math.random() * (15));
                    Load.notabuilding(player, num, cc, info);
                    player[Load.next].animation(move, sx, sy);
                    Load.turn(player, property, station, service, a, b, card, cc, info);
                    label[Load.next].setText(player[Load.next].Name + " : " + player[Load.next].Balance + "$");

                } catch (FileNotFoundException ex)
                {
                    System.out.println(ex);
                } catch (IOException ex)
                {
                    System.out.println(ex);
                }
            }
            else
            {
                info.setText("Semester has ended\n Please exit");
            }
        });

        exit.setOnAction(e -> {
            if (Load.numofplayers < 2) end.fire();
            if (Load.next == -1)
            {
                stage.close();
                setplayer(myStage).show();
                return;
            }
            Alert SAVE = new Alert(AlertType.CONFIRMATION);
            SAVE.setTitle("Exit");
            SAVE.setHeaderText("Do you wish to save your progress before you exit");
            SAVE.getDialogPane().getButtonTypes().clear();
            ButtonType yes = new ButtonType("YES");
            ButtonType no = new ButtonType("NO");
            ButtonType cancel = new ButtonType("Cancel");
            SAVE.getDialogPane().getButtonTypes().addAll(yes, no, cancel);
            SAVE.showAndWait();
            if (SAVE.getResult() == yes)
            {
                try
                {
                    Load.save(player);
                } catch (IOException ex)
                {
                }
                stage.close();
                exit(0);
            }
            else if (SAVE.getResult() == no)
            {
                stage.close();
                exit(0);
            }
            else if (SAVE.getResult() == cancel) SAVE.close();
        });
        HBox names = new HBox();
        Button d = new Button("DISPLAY OWNED");
        d.setStyle("-fx-background-color: #f5f7f7; -fx-text-fill: #9b0807; -fx-font-family : 'Algerian'");
        for (int i = 0; i < Load.numofplayers; i++)
        {
            label[i] = new Label(player[i].Name + " : " + player[i].Balance + "$");
            v[i] = new VBox(label[i], player[i].getCircle(circles));
            setdimensions(v[i]);
            names.getChildren().add(v[i]);
            moveable[i] = new Circle(10);
            moveable[i].setFill(circles[player[i].circle_index].getFill());
            move.getChildren().add(moveable[i]);
            move.getChildren().get(i).setLayoutX(sx[0]);
            move.getChildren().get(i).setLayoutY(sy[0]);
        }
        setdimensions(names);
        setcolor(label);
        VBox white = new VBox(names, d, info, die, roll, card, exit);
        setdimensions(white);
        white.setStyle("-fx-background-color:White");
        white.setAlignment(Pos.TOP_CENTER);
        HBox ALL = new HBox(play, white);
        setdimensions(ALL);
        Scene scene = new Scene(ALL, 1200, 750);
        scene.setOnKeyPressed(value -> {
            KeyCode key = value.getCode();
            if (key == KeyCode.ESCAPE)
                exit.fire();
            if (key == KeyCode.ENTER)
                roll.fire();
            if (key == KeyCode.R)
                roll.fire();
            if (key == KeyCode.D)
                d.fire();

        });
        d.setOnAction(value -> {
            try
            {
                disp(info);
            } catch (FileNotFoundException ex)
            {
            }
        });
        stage.setScene(scene);
        stage.setMaximized(true);
        return stage;

    }

    Stage disp(Label l) throws FileNotFoundException
    {

        Stage s = new Stage();
        try
        {
            s.setTitle(player[Load.next].Name + " owned cards");
            Label name = new Label(player[Load.next].Name + " : ");
            name.setStyle("-fx-text-fill: #a70d12; -fx-font-size: 2em; -fx-font-family : 'Algerian';");
            Button next = new Button(">");
            Button prev = new Button("<");
            ArrayList<ImageView> images = new ArrayList<>();
            for (int i = 0; i < player[Load.next].owned.size(); i++) images.add(player[Load.next].owned.get(i).image());
            for (int i = 22; i < player[Load.next].scount.size(); i++)
                images.add(player[Load.next].scount.get(i).image());
            for (int i = 26; i < player[Load.next].bcount.size(); i++)
                images.add(player[Load.next].bcount.get(i).image());
            HBox h = new HBox(prev, images.get(0), next);
            setdimensions(h);
            Button close = new Button("CLOSE");
            setcolor(close);
            VBox v = new VBox(name, h, close);
            setdimensions(v);
            v.setStyle("-fx-background-color: #dcfbcb ");
            Scene scene = new Scene(v, 400, 500);
            s.setScene(scene);
            close.setOnAction(value -> {
                s.close();
            });
            next.setOnAction(value -> {
                if (images.indexOf(h.getChildren().get(1)) == images.size() - 1)
                {
                    h.getChildren().add(2, images.get(0));
                    h.getChildren().remove(1);
                }
                else
                {
                    h.getChildren().add(2, images.get(images.indexOf(h.getChildren().get(1)) + 1));
                    h.getChildren().remove(1);
                }
            });
            prev.setOnAction(value -> {
                if (images.indexOf(h.getChildren().get(1)) == 0)
                {
                    h.getChildren().add(2, images.get(images.size() - 1));
                    h.getChildren().remove(1);
                }
                else
                {
                    h.getChildren().add(2, images.get(images.indexOf(h.getChildren().get(1)) - 1));
                    h.getChildren().remove(1);
                }
            });
            setcolor(next);
            setcolor(prev);
            scene.setOnKeyPressed(value -> {
                KeyCode key = value.getCode();
                if (key == KeyCode.N)
                    next.fire();
                if (key == KeyCode.P)
                    prev.fire();
                if (key == KeyCode.ESCAPE)
                    close.fire();
            });
            s.show();
        } catch (Exception e)
        {
            l.setText("Cannot display outside of Turn");
            s.close();
        }
        return s;
    }
}






