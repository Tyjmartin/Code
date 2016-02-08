package RoboRace;

import COSC3P40.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Player extends JFrame implements Runnable {
    
    private String name;
    private Board board;
    private BoardCanvas boardCanvas;
    private CardPane cardPane;
    private EventList eList;
    private Port port;
    private Channel chan;
    private CardList cList;

   public Player (String inName, Port por){
        cardPane = new CardPane();
        name = inName;
        port = por;
        new Thread(this).start();
        
    }
   
    public void run(){
        String data, recCard;
        data = port.recieve();
        board = Board.read(new StringReader(data));
        System.out.println("test");
        boardCanvas = new BoardCanvas(board);
        cardPane = new CardPane();
        getContentPane().add("North",boardCanvas);
        getContentPane().add("South",cardPane);
        pack();
        setResizable(false);
        setVisible(true);
        boardCanvas.start();
        
        recCard = port.recieve();
        cList = CardList.read(new StringReader(recCard));
        port.send(selectCards(cList).toXMLString());
        
        EventList e = EventList.read(new StringReader(data));
       
        e.execute(board);
        
    }
        
    public void recieveBoard(Board inBoard){
        board = inBoard;
    }

    public void recieveEvents(EventList list){
        eList = list;
    }

    public CardList selectCards(CardList cList){

        return cardPane.selectCards(cList);

    }

    public void close(){

    }


}