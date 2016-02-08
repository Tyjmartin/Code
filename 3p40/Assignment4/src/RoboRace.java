import RoboRace.*;
import javax.swing.*;
import COSC3P40.graphics.*;
import COSC3P40.xml.*;
import java.net.Socket;

//Client roboRace

public class RoboRace {
    
    public static void main(String[] args) {
    	JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
	ImageManager.getInstance().setImagePath("./Images/");
	XMLReader.setXMLPath("./");
	XMLReader.setXSDPath("./XSD/");	
    	int nHuman = 0;
    	/*while (nHuman==0 || nHuman>4) {
	    	try {
	    		nHuman = Integer.parseInt(GameDialogs.showInputDialog("Number of human players","Please, input the number of human players (1-4):"));
	    	} catch(Exception e) {};
	};*/
	String names = new String();
        Socket socks = new Socket();
        
        
        names = GameDialogs.showInputDialog("","Name of Player #" + ":"); 
        Port net = new NetworkPort(10997);
        net.send(names);
        new Player(names, net);
    }	   
}
