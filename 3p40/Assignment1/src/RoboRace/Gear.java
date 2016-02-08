package RoboRace;

public class Gear implements Tile {
	
	private boolean clockwise;
	
	public Gear(boolean clockwise) {
		this.clockwise = clockwise;
	}
	
	public boolean isClockwise() {
		return clockwise;
	}
	
	public void effect(EventCounter counter, EventList events, Robot robot, Board board) {
            GameEvent event = new TurnEvent(counter, robot.getLocation(), clockwise);
            events.add(event);
            robot.turn(clockwise);
            counter.increaseAction();
	}
	
	public String toXMLString() {
		return "<gear clockwise=\"" + clockwise + "\"/>";
	}
}