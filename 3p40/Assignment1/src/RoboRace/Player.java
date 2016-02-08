package RoboRace;

import COSC3P40.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Player extends JFrame {
	
	private String name;
	private Board board;
	private BoardCanvas boardCanvas;
	private CardPane cardPane;
	
        public Player (String inName, Board gameBoard){
                cardPane = new CardPane();
                name = inName;
                board = gameBoard;
                boardCanvas = new BoardCanvas(board);
                cardPane = new CardPane();
                getContentPane().add("North",boardCanvas);
                getContentPane().add("South",cardPane);
                pack();
                setResizable(false);
                setVisible(true);
                boardCanvas.start();
            }
        
        public void recieveBoard(Board inBoard){
            board = inBoard;
        }
        
        public void recieveEvents(EventList list){
            
        }
        
        public CardList selectCards(CardList cList){
            
            return cardPane.selectCards(cList);
            
        }
        
        public void close(){
            
        }
	
	/* some code that will be needed for display
		
		
        */
}