import RoboRace.*;
import javax.swing.*;
import COSC3P40.graphics.*;
import COSC3P40.xml.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RoboRace {
    
//Server Roborace
    
    public static void main(String[] args) {
    	JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
	ImageManager.getInstance().setImagePath("./Images/");
	XMLReader.setXMLPath("./");
	XMLReader.setXSDPath("./XSD/");	
    	int nHuman = 0;
    	while (nHuman==0 || nHuman>4) {
	    	try {
	    		nHuman = Integer.parseInt(GameDialogs.showInputDialog("Number of human players","Please, input the number of human players (1-4):"));
	    	} catch(Exception e) {};
	};
	String[] names = new String[nHuman];
	Port[] ports = new NetworkPort[nHuman];
        
        try {
            ServerSocket server = new ServerSocket(10997);
            for (int i = 0; i < nHuman; i++){  
                Socket client = server.accept();
                ports[i]= new NetworkPort(client);
                names[i] = ports[i].recieve();
            }
        }
         catch(Exception e){
         }
    	(new GameMaster(nHuman,names,ports)).run();
    }	   
}
