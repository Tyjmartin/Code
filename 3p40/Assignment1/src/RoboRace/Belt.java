package RoboRace;

public class Belt implements Tile {
	
	private Direction direction;
	
	public Belt(Direction direction) {
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void effect(EventCounter counter, EventList events, Robot robot, Board board) {
            //MoveEvent move = new MoveEvent (counter, robot.getScreenX(),robot.getScreenY(),direction);
            //events.add(move);
            board.step(counter, events, robot, direction);
            counter.increaseAction();
            
	}
	
	public String toXMLString() {
		return "<belt direction=\"" + direction + "\"/>";
	}
}