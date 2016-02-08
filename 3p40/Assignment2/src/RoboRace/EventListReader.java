package RoboRace;

import java.util.*;
import org.w3c.dom.*;
import COSC3P40.xml.*;

import static COSC3P40.xml.XMLTools.getChildNodes;

public class EventListReader implements XMLNodeConverter<EventList> {
	
	private EventReader listReader;
	
	public EventListReader() {
		this.listReader = new EventReader();
	}
	
	public EventList convertXMLNode(Node node) {
		EventList result = new EventList();
		if (node.getNodeName().equals("events")) {
			List<Node> list = getChildNodes(node);
			for(Node n : list)
				result.add(listReader.convertXMLNode(n));
		};
		return result;	
	}
}