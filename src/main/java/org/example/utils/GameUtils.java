package org.example.utils;

import javax.tools.Tool;
import java.awt.*;

public class GameUtils {

    //head
    public static Image upImg = Toolkit.getDefaultToolkit().getImage("img/up.png");
    public static Image downImg = Toolkit.getDefaultToolkit().getImage("img/down.png");
    public static Image leftImg = Toolkit.getDefaultToolkit().getImage("img/left.png");
    public static Image rightImg = Toolkit.getDefaultToolkit().getImage("img/right.png");

    public static Image bodyImg = Toolkit.getDefaultToolkit().getImage("img/body.png");

    public static Image foodImg = Toolkit.getDefaultToolkit().getImage("img/food.png");

    //stage
    public static int level = 1;

    public static void drawWord(Graphics g, String str, Color color, int size, int x, int y){
        g.setColor(color);
        g.setFont(new Font("Times New Roman", Font.BOLD, size));
        g.drawString(str, x , y);
    }
}
