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
    private HTextButton knop1, knop2, knop3, knop4, knop5;
  
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {
      if(debug) System.out.println("Xlet Initialiseren");
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
      knop1.setLocation(36, 75);
      knop1.setSize(100, 50);
      knop1.setBackground(new DVBColor(0,0,0,179));
      knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      knop2 = new HTextButton("RIJ 2");
      knop2.setLocation(172, 75);
      knop2.setSize(100, 50);
      knop2.setBackground(new DVBColor(0,0,0,179));
      knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      knop3 = new HTextButton("RIJ 3");
      knop3.setLocation(308, 75);
      knop3.setSize(100, 50);
      knop3.setBackground(new DVBColor(0,0,0,179));
      knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      knop4 = new HTextButton("RIJ 4");
      knop4.setLocation(444, 75);
      knop4.setSize(100, 50);
      knop4.setBackground(new DVBColor(0,0,0,179));
      knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      knop5 = new HTextButton("RIJ 5");
      knop5.setLocation(580, 75);
      knop5.setSize(100, 50);
      knop5.setBackground(new DVBColor(0,0,0,179));
      knop5.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      knop1.setFocusTraversal(null, null, null, knop2);//op, neer, links, recht
      knop2.setFocusTraversal(null, null, knop1, knop3);//op, neer, links, recht
      knop3.setFocusTraversal(null, null, knop2, knop4);//op, neer, links, recht
      knop4.setFocusTraversal(null, null, knop3, knop5);//op, neer, links, recht
      knop5.setFocusTraversal(null, null, knop4, null);//op, neer, links, recht
      
      
      scene.add(tekstLabel);
      scene.add(knop1);
      scene.add(knop2);
      scene.add(knop3);
      scene.add(knop4);
      scene.add(knop5);

      knop1.requestFocus();
      
      knop1.setActionCommand("rij 1 gekozen");
      knop2.setActionCommand("rij 2 gekozen");
      knop3.setActionCommand("rij 3 gekozen");
      knop4.setActionCommand("rij 4 gekozen");
      knop5.setActionCommand("rij 5 gekozen");
      
      knop1.addHActionListener(this);
      knop2.addHActionListener(this);
      knop3.addHActionListener(this);
      knop4.addHActionListener(this);
      knop5.addHActionListener(this);
    }
    public void startXlet() throws XletStateChangeException
    {
        if(debug) System.out.println("Xlet starten");
        
        scene.validate();
        scene.setVisible(true);
    }
    public void actionPerformed (ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException
    {
     
    }
}
