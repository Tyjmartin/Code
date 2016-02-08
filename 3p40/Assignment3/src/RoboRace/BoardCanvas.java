package RoboRace;

import java.awt.*;
import java.awt.image.*;
import COSC3P40.graphics.*;
import java.util.List;

public class BoardCanvas extends Canvas implements Runnable {
	
	private Board board;
	private boolean running;
	private Thread thread;
        
	
	public BoardCanvas(Board board) {
		this.board = board;
		Dimension dim = board.getDisplaySize();
		setSize(dim.width,dim.height);
	}
	
	public void start() {
		thread = new Thread(this);
		running = true;
		board.start();
		thread.start();
	}
	
	public void stop() {
		running = false;
		try{
			thread.join();
		} catch(Exception e) {}
	}
	
	public void run() {
            
            this.createBufferStrategy(2);
            BufferStrategy strategy = getBufferStrategy();
            Graphics g = strategy.getDrawGraphics();
            long aTime = System.currentTimeMillis();
            while(true){
                g = strategy.getDrawGraphics();
                board.update(System.currentTimeMillis()-aTime);
                aTime = System.currentTimeMillis();
                board.draw(g);
                g.dispose();
                strategy.show();
                try{
                    thread.sleep(2);
                }
                catch (InterruptedException ex){}
            }
        }
}