import RoboRace.*;
import javax.swing.*;
import COSC3P40.graphics.*;
import COSC3P40.xml.*;
import java.io.StringReader;

public class RoboRace {
    
    public static void main(String[] args) {
        /*EventCounter counter = new EventCounter();
        EventList list = new EventList();
        
        GameEvent turn = new TurnEvent(counter, 1,1 , true);
        GameEvent move = new MoveEvent(counter, 1, 1, Direction.North);
        GameEvent bump = new BumpEvent(counter, 1, 1, Direction.North);
      
        list.add(move);
        list.add(turn);
        list.add(bump);
        
        String myTest = list.toXMLString();
        EventListReader read = new EventListReader();
        
        
        EventList newEl = EventList.read(new StringReader(myTest));
        
        System.out.println(newEl.size());
        
        myTest = newEl.toXMLString();
        
        System.out.println(myTest);
        */
        
        
    	JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
	ImageManager.getInstance().setImagePath("./Images/");
	XMLReader.setXMLPath("./");
	XMLReader.setXSDPath("./XSD/");	
    	int nHuman = 0;
        Player[] players;
        Channel[] chan;
        Port[] port;
    	while (nHuman==0 || nHuman>4) {
	    	try {
	    		nHuman = Integer.parseInt(GameDialogs.showInputDialog("Number of human players","Please, input the number of human players (1-4):"));
	    	} catch(Exception e) {};
	};
	String[] names = new String[nHuman];
	for (int i=0; i<nHuman; i++) {
            names[i] = GameDialogs.showInputDialog("Player #" + (i+1),"Name of Player #" + (i+1) + ":");
        };
        
        players = new Player[nHuman];
        chan = new Channel[nHuman];
        port = new Port[nHuman];
        
        // creating each player with the thread.
        for (int i = 0; i < nHuman; i++){
            chan[i] = new Channel();
            players[i] = new Player(names[i],chan[i].asPort1());
            port[i] = new Channel().asPort2();
        }
        
        
        GameMaster gm = new GameMaster(nHuman, names, port);
        
    }	   
}
