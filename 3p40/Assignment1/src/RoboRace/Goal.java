package RoboRace;

public class Goal implements Tile {
	
	public void effect(EventCounter counter, EventList events, Robot robot, Board board) {
            GameEvent event = new VictoryEvent(counter, robot.getScreenX(), robot.getScreenY(), robot.getName());
            events.add(event);
            counter.increaseAction();
	}
	
	public String toXMLString() {
		return "<goal/>";
	}
	
}