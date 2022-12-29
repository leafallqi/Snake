package org.example;

import org.example.obj.BodyObj;
import org.example.obj.FoodObj;
import org.example.obj.HeadObj;
import org.example.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame{

    //Score window
    public int score =0;
    //game state 0:not start 1:in game 2:pause 3:fail 4.clear 5.restart game when fail 6.next level
    public static int state =0;

    //double cache image
    Image offScreenImage = null;

    int winWidth = 800;
    int winHeight = 600;

    HeadObj headObj = new HeadObj(GameUtils.rightImg, 60, 570, this);

    //Snake body
    public List<BodyObj> bodyObjList = new ArrayList<>();

    //food
    public FoodObj foodObj = new FoodObj().getFood();

    @Override
    public void paint(Graphics g){
        //load offScreen image
        if(offScreenImage == null){
            offScreenImage = this.createImage(winWidth, winHeight);
        }

        Graphics gImage = offScreenImage.getGraphics();


        //gray background
        gImage.setColor(Color.gray);
        gImage.fillRect(0,0,winWidth,winHeight);

        //set rectangle color
        gImage.setColor(Color.black);

        //draw lines
        for(int i = 0; i<20; i++) {
            //horizontal
            gImage.drawLine(0, i*30, 600, i *30);
            //vertical
            gImage.drawLine(i*30, 0, i*30, 600);
        }



        //draw snake body
        for (int i = bodyObjList.size() - 1; i >=0 ; i--) {
            bodyObjList.get(i).paintSelf(gImage);
        }

        //draw food
        foodObj.paintSelf(gImage);

        //draw head
        headObj.paintSelf(gImage);

        //level
        GameUtils.drawWord(gImage, "level" + GameUtils.level, Color.orange, 40, 650, 260);

        //draw score
        GameUtils.drawWord(gImage, score + "", Color.BLUE, 50, 650, 330);

        gImage.setColor(Color.GRAY);
        prompt(gImage);

        g.drawImage(offScreenImage,0,0, null);

    }

    void prompt(Graphics g){
        //not start
        if(state == 0){
            g.fillRect(120, 240, 400, 70);
            GameUtils.drawWord(g, "hit space to start game", Color.yellow, 35, 150, 290);
        }

        if(state == 2){
            g.fillRect(120, 240, 400, 70);
            GameUtils.drawWord(g, "hit space to continue", Color.yellow, 35, 150, 290);
        }

        if(state == 3){
            g.fillRect(120, 240, 400, 70);
            GameUtils.drawWord(g, "Game fail, hit space to restart", Color.red, 35, 150, 290);
        }

        if(state == 4){
            g.fillRect(120, 240, 400, 70);
            if(GameUtils.level == 3){
                GameUtils.drawWord(g, "Game Clear, Congratulation", Color.green, 35, 150, 290);
            }
            else{
                GameUtils.drawWord(g, "Stage Clear, hit space to next level", Color.green, 35, 150, 290);
            }

        }
    }



    public void launch() {

        bodyObjList.add(new BodyObj(GameUtils.bodyImg, 30, 570, this));
        bodyObjList.add(new BodyObj(GameUtils.bodyImg, 0, 570, this));


        this.setVisible(true);

        this.setSize(winWidth, winHeight);

        this.setLocationRelativeTo(null);

        this.setTitle("Snake");

        //keyboard event: to start the game
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    switch(state){
                        //not start
                        case 0:
                            state = 1;
                            break;
                         //in game
                        case 1:
                            state = 2;
                            repaint();
                            break;
                        //pause
                        case 2:
                            state = 1;
                            break;
                        //restart game when fail
                        case 3:
                            state = 5;
                            break;
                        //advance to next level
                        case 4:
                            state = 6;
                            break;
                        default:
                            break;
                    }
                }
            }
        });



        while(true){
            if(state == 1){
                repaint();
            }
            //restart when fail game
            if(state == 5){
                state = 0;
                resetGame();
            }
            if(state == 6 && GameUtils.level !=3){
                state = 1;
                GameUtils.level ++;
                resetGame();
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void resetGame() {
        //close current window
        this.dispose();
        //start new window
        String[] args = {};
        main(args);
    }


        public static void main(String[] args) {
            GameWin gameWin = new GameWin();

            gameWin.launch();
        }




}
