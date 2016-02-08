package RoboRace;

public class Pit implements Tile {
	
	public void effect(EventCounter counter, EventList events, Robot robot, Board board) {
            GameEvent event = new DestroyedEvent(counter, robot.getLocation());
            events.add(event);
            robot.destroyed();
            counter.increaseAction();
	}
	
	public String toXMLString() {
		return "<pit/>";
	}
	
}