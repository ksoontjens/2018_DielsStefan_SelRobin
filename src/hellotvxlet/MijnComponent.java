/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hellotvxlet;

import java.awt.*;
import org.havi.ui.*;



/**
 *
 * @author student
 */
public class MijnComponent extends HComponent {
    
    private Image bmap;
    private MediaTracker mtrack;
    //Plaats en locatie instellen in de constructor
    public MijnComponent(String bitmn, int x , int y )
    {

        bmap = this.getToolkit().getImage(bitmn);

        mtrack=new MediaTracker ( this ) ;
        mtrack.addImage(bmap, 0);
        try
        {
            mtrack. waitForAll ( ) ; // wacht tot alle bitmaps geladen zijn
        }
        catch ( Exception e )
        {
            System. out . println ( e . toString ( ) ) ;
        }
        
        this.setBounds(x, y, bmap.getWidth( null ), bmap.getWidth(null )) ;
        // opgegeven plaats en afmeting van de bitmap
    }
    // Paint methode overriden
    public void paint ( Graphics g )
    {
        g.drawImage(bmap, 0, 0, null ) ;
    }

}
