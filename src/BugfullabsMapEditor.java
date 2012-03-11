import java.awt.event.ActionEvent;
import javax.swing.JFrame;


/**
*
* @author Bugfullabs
* 
 */
 
public class BugfullabsMapEditor {

	   static JFrame mFrame;
	   
	   static Menu mMenu;
	   
	    public static void main(String[] args) {
	    	
	      mFrame = new JFrame("Bugfullabs Map Maker");
	      mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      mFrame.setSize(400,400);

	      mFrame.setVisible(true);
	      
	      mMenu = new Menu(mFrame){
	    	  @Override
	    	  public void onAction(ActionEvent e){
	    		  if (e.getSource().equals(this.menuNewFile)) {
	    			  mFrame.setVisible(false);
	    		  }
	    	  }
	      };

	    }


		    	    
}