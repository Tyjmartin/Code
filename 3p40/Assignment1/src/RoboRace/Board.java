package RoboRace;

import COSC3P40.xml.*;
import java.awt.*;
import java.io.*;

public class Board implements XMLObject {
	
	private Factory factory;
	private int numberRobots;
	private Robot[] robots;
	
	public static Board read(Reader input) {
		XMLReader<Board> reader = new XMLReader<Board>();
        reader.setXMLSchema("board.xsd");
        reader.setXMLNodeConverter(new BoardReader());
        return reader.readXML(input);
	}
	
	public Board(Factory factory, int numberRobots, Robot[] robots) {
		this.factory = factory;
		this.numberRobots = numberRobots;
		this.robots = robots;
	}
	
	public Location getLocation(int x, int y) {
		return factory.getLocation(x,y);
	}
	
	public Location getLocation(Point p) {
		return factory.getLocation(p);
	}
	
	public Robot robotAt(int x, int y) {
		for(Robot robot : robots)
			if (x == robot.getLocation().x && y == robot.getLocation().y) 
				return robot;
		return null;
	}
	
	public void step(EventCounter counter, EventList events, Robot robot, Direction direction) {
            Robot isRobot;
            if (getLocation(robot.getScreenX(),robot.getScreenY()).hasWall(direction)){
                GameEvent event = new BumpEvent(counter, robot.getLocation(), direction);
                events.add(event);
                counter.increaseAction();
                return;
            }
            int locx = robot.getLocation().x;
            int locy = robot.getLocation().y;
            int nextx = locx;
            int nexty = locy;
           
            switch(direction){
                case North: nextx--;
                    break;
                case South: nextx++;
                    break;
                case West: nexty--;
                    break;
                case East: nexty++;
                    break;
            }
            isRobot = robotAt(nextx,nexty);
            
            if(isRobot == null){
                GameEvent event = new MoveEvent(counter, robot.getLocation(), direction);
                events.add(event);
                robot.move(direction);
                counter.increaseAction();
            }
            if (robot.getLocation().x< 0 || robot.getLocation().y <0 || robot.getLocation().x>= factory.getXSize() || robot.getLocation().y >= factory.getYSize() || factory.getLocation(robot.getLocation()).isPit()){
                GameEvent event = new DestroyedEvent(counter, robot.getScreenX(), robot.getScreenY());
                events.add(event);
                counter.increaseAction();
            }
            
            else {
                step(counter, events, isRobot, direction);
            }
	}
	
	public void revitalize() {
		for(Robot robot : robots)
			if (!robot.isAlive() && robotAt(0,robot.getStart())==null) 
				robot.revitalize();
	}

	public String toXMLString() {
		String result = "<board>\n";
		result += "<robots number=\"" + numberRobots + "\">\n";
		for(Robot robot : robots) 
			result += robot.toXMLString() + "\n"; 
		result += "</robots>\n";
		result += factory.toXMLString() + "\n";
		return result + "</board>";
	}
	
	public Dimension getDisplaySize() {
		return factory.getDisplaySize();
	}
	
	public void start() {
		factory.start();
	}
	
	public void update(long delta) {
		factory.update(delta);
		for(Robot robot : robots)
			robot.update(delta);
	}
	
	public void draw(Graphics graphics) {
		factory.draw(graphics);
		for(Robot robot : robots)
			graphics.drawImage(robot.getImage(),robot.getScreenX(),robot.getScreenY(),null);
	}
	
	public void waitOnRobots() {
		for(Robot robot : robots) 
			robot.waitOnRobot();
		
	}
	
}