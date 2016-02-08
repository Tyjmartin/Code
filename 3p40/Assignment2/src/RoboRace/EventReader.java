package RoboRace;

import COSC3P40.xml.*;
import org.w3c.dom.*;

import static COSC3P40.xml.XMLTools.*;

public class EventReader implements XMLNodeConverter<GameEvent> {

    public GameEvent convertXMLNode(Node node) {
        GameEvent result = null;

            int x = getIntAttribute(node, "x");
            int y = getIntAttribute(node, "y");
            int step = getIntAttribute (node,"step");
            int action = getIntAttribute (node,"action");
            String name = node.getNodeName();
            EventCounter counter = new EventCounter(step, action);
            



        if (name.equals("bump")){
            Direction direction = (Direction)getEnumAttribute(Direction.class,node,"direction");
            result = new BumpEvent(counter,x,y,direction);
        }

         if (name.equals("halfturn")){
             result = new HalfturnEvent(counter, x, y);

        }
          if (name.equals("move")){
              Direction direction = (Direction)getEnumAttribute(Direction.class,node,"direction");
              result = new MoveEvent(counter, x, y, direction);

        }
           if (name.equals("turn")){
                Boolean clockwise = getBoolAttribute (node,"clockwise");
                result = new TurnEvent(counter, x, y, clockwise);

        }
          if (name.equals("victory")){
              String names = getStringAttribute (node,"name");
              result = new VictoryEvent(counter, x, y, names);

        }
           if (name.equals("desroyed")){
               result = new DestroyedEvent(counter, x, y);
        }

            return result;
    }
}