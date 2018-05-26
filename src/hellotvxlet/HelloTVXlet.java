package hellotvxlet;

import javax.tv.xlet.*;
import org.havi.ui.*;
import org.dvb.ui.*;
import java.awt.*;
import java.awt.event.*;
import org.havi.ui.event.*;

public class HelloTVXlet implements Xlet, HActionListener {
    private HScene scene;
    private XletContext actueleXletContext;
    private boolean debug=true;
            
    private HStaticText tekstLabel;
    private HTextButton knop1, knop2, knop3, knop4, knop5, knop6, knop7, newGame;
    int[][] coins = new int [7][4];
    HStaticText [][] blocks = new HStaticText [7][4];
    private int turn = 0;
    
    MijnComponent mc = new MijnComponent("veld.png", 10 , 150);
    
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {
      if(debug) System . out . println ("Xlet Initialiseren");
        this.actueleXletContext = context;
      
        //scene----------Template aanmaken-----------------------------------
      HSceneTemplate sceneTemplate = new HSceneTemplate();
      sceneTemplate.setPreference (
              org.havi.ui.HSceneTemplate.SCENE_SCREEN_DIMENSION,
              new HScreenDimension(1.0f, 1.0f), org.havi.ui.HSceneTemplate.REQUIRED);
      
      sceneTemplate.setPreference (
              org.havi.ui.HSceneTemplate.SCENE_SCREEN_LOCATION,
              new HScreenPoint(0.0f, 0.0f), org.havi.ui.HSceneTemplate.REQUIRED);
        //scene----------Instantie van scene aanvragen-----------------------------------
      scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);
      
        // object aanmaken------------------
      tekstLabel = new HStaticText("4 op een rij");
        //eigenschappen van object---------
      tekstLabel.setLocation(235,0);
      tekstLabel.setSize(250,50);
      tekstLabel.setBackground(new DVBColor(0,127,255,179));
      tekstLabel.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      knop1 = new HTextButton("RIJ 1");
      knop1.setLocation(10, 75);
      knop1.setSize(100, 50);
      knop1.setBackground(new DVBColor(0,0,0,179));
      knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      knop2 = new HTextButton("RIJ 2");
      knop2.setLocation(110, 75);
      knop2.setSize(100, 50);
      knop2.setBackground(new DVBColor(0,0,0,179));
      knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      knop3 = new HTextButton("RIJ 3");
      knop3.setLocation(210, 75);
      knop3.setSize(100, 50);
      knop3.setBackground(new DVBColor(0,0,0,179));
      knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      knop4 = new HTextButton("RIJ 4");
      knop4.setLocation(310, 75);
      knop4.setSize(100, 50);
      knop4.setBackground(new DVBColor(0,0,0,179));
      knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      knop5 = new HTextButton("RIJ 5");
      knop5.setLocation(410, 75);
      knop5.setSize(100, 50);
      knop5.setBackground(new DVBColor(0,0,0,179));
      knop5.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      knop6 = new HTextButton("RIJ 6");
      knop6.setLocation(510, 75);
      knop6.setSize(100, 50);
      knop6.setBackground(new DVBColor(0,0,0,179));
      knop6.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      knop7 = new HTextButton("RIJ 7");
      knop7.setLocation(610, 75);
      knop7.setSize(100, 50);
      knop7.setBackground(new DVBColor(0,0,0,179));
      knop7.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      newGame = new HTextButton("NEW GAME");
      newGame.setLocation(10, 10);
      newGame.setSize(200, 50);
      newGame.setBackground(new DVBColor(0,0,0,179));
      newGame.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      knop1.setFocusTraversal(newGame, null, null, knop2);//op, neer, links, recht
      knop2.setFocusTraversal(newGame, null, knop1, knop3);//op, neer, links, recht
      knop3.setFocusTraversal(newGame, null, knop2, knop4);//op, neer, links, recht
      knop4.setFocusTraversal(newGame, null, knop3, knop5);//op, neer, links, recht
      knop5.setFocusTraversal(newGame, null, knop4, knop6);//op, neer, links, recht
      knop6.setFocusTraversal(newGame, null, knop5, knop7);//op, neer, links, recht
      knop7.setFocusTraversal(newGame, null, knop6, null);//op, neer, links, recht
      newGame.setFocusTraversal(null, knop1, null, null);//op, neer, links, recht
      
      
      scene.add(tekstLabel);
      scene.add(knop1);
      scene.add(knop2);
      scene.add(knop3);
      scene.add(knop4);
      scene.add(knop5);
      scene.add(knop6);
      scene.add(knop7);
      scene.add(newGame);
      scene.add(mc);

      knop1.requestFocus();
      
      knop1.setActionCommand("rij 1 gekozen");
      knop2.setActionCommand("rij 2 gekozen");
      knop3.setActionCommand("rij 3 gekozen");
      knop4.setActionCommand("rij 4 gekozen");
      knop5.setActionCommand("rij 5 gekozen");
      knop6.setActionCommand("rij 6 gekozen");
      knop7.setActionCommand("rij 7 gekozen");
      newGame.setActionCommand("new game");
      
      knop1.addHActionListener(this);
      knop2.addHActionListener(this);
      knop3.addHActionListener(this);
      knop4.addHActionListener(this);
      knop5.addHActionListener(this);
      knop6.addHActionListener(this);
      knop7.addHActionListener(this);
      newGame.addHActionListener(this);
    }
    public void startXlet() throws XletStateChangeException
    {
        if(debug) System.out.println("Xlet starten");
        
        scene.validate();
        scene.setVisible(true);
        
        paintScreen();
        
        for(int i=0; i<7; i++) {
                for(int j=0; j<4; j++) {  
                  blocks[i][j] = new HStaticText("");
                  
                    //eigenschappen van object---------
                  blocks[i][j].setLocation(10 + i*100,150 + j*100);
                  blocks[i][j].setSize(100,100);
                  blocks[i][j].setBackground(new DVBColor(0,0,0,0));
                  blocks[i][j].setBackgroundMode(HVisible.BACKGROUND_FILL);
                  scene.add(blocks[i][j]);
                }
            }
    }
    public void actionPerformed (ActionEvent e) {
        if (turn%2 == 1) {
            System.out.println("blauw is aan beurt");
        }
        else {
            System.out.println("rood is aan beurt");
        }
        if (e.getActionCommand() == "rij 1 gekozen") {
            for (int i = 3; i > -1; i--) {
                if (coins[0][i] == 0) {
                    if (turn % 2 == 0) {
                        coins[0][i] = 1;
                    }
                    else {
                        coins[0][i] = 2;
                    }
                    turn++;
                    break;
                }
            } 
        }
        else if (e.getActionCommand() == "rij 2 gekozen") {
            for (int i = 3; i > -1; i--) {
                if (coins[1][i] == 0) {
                    if (turn % 2 == 0) {
                        coins[1][i] = 1;
                    }
                    else {
                        coins[1][i] = 2;
                    }
                    turn++;
                    break;
                }
            } 
        }
        else if (e.getActionCommand() == "rij 3 gekozen") {
            for (int i = 3; i > -1; i--) {
                if (coins[2][i] == 0) {
                    if (turn % 2 == 0) {
                        coins[2][i] = 1;
                    }
                    else {
                        coins[2][i] = 2;
                    }
                    turn++;
                    break;
                }
            } 
        }
        else if (e.getActionCommand() == "rij 4 gekozen") {
            for (int i = 3; i > -1; i--) {
                if (coins[3][i] == 0) {
                    if (turn % 2 == 0) {
                        coins[3][i] = 1;
                    }
                    else {
                        coins[3][i] = 2;
                    }
                    turn++;
                    break;
                }
            } 
        }
        else if (e.getActionCommand() == "rij 5 gekozen") {
            for (int i = 3; i > -1; i--) {
                if (coins[4][i] == 0) {
                    if (turn % 2 == 0) {
                        coins[4][i] = 1;
                    }
                    else {
                        coins[4][i] = 2;
                    }
                    turn++;
                    break;
                }
            } 
        }
        else if (e.getActionCommand() == "rij 6 gekozen") {
            for (int i = 3; i > -1; i--) {
                if (coins[5][i] == 0) {
                    if (turn % 2 == 0) {
                        coins[5][i] = 1;
                    }
                    else {
                        coins[5][i] = 2;
                    }
                    turn++;
                    break;
                }
            } 
        }
        else if (e.getActionCommand() == "rij 7 gekozen") {
            for (int i = 3; i > -1; i--) {
                if (coins[6][i] == 0) {
                    if (turn % 2 == 0) {
                        coins[6][i] = 1;
                    }
                    else {
                        coins[6][i] = 2;
                    }
                    turn++;
                    break;
                }
            } 
        }
        else if (e.getActionCommand() == "new game") {
            for(int i=0; i<7; i++) {
                for(int j=0; j<4; j++) {
                    coins[i][j] = 0;
                    blocks[i][j].setBackground(new DVBColor(0,0,0,0));
                    turn = 0;
                }
            }
        }
        paintScreen();
    }
    public void paintScreen () {
        for(int i=0; i<7; i++) {
          for(int j=0; j<4; j++) {
              if (coins[i][j] == 1) {
                  blocks[i][j].setBackground(new DVBColor(0,127,255,255));

              }
              else if (coins[i][j] == 2) {
                  blocks[i][j].setBackground(new DVBColor(255,0,0,255));
              }
              scene.repaint();
          }
      }
    }
    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException
    {
     
    }
}
