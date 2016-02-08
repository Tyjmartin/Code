package RoboRace;

import java.util.*;
import java.io.*;

public class GameMaster implements Runnable {
    private int numberPlayers;
    private String playerName[];
    private Robot numBots[];
    private Board gameBoard;
    private CardFactory card; 
    private CardList cList[];
    private CardList selectList[];
    private Port port[];
    
    public GameMaster(int numPlayers, String[] playerNames, Port[] ports){
        numberPlayers = numPlayers;
        port = ports;
        
        numBots = new Robot[numberPlayers];
        for (int i = 0; i < numberPlayers; i++){
          numBots[i] = new Robot(playerNames[i], (2*i)+1);
        }
        
        gameBoard = new Board(Factory.load("Factory.xml"),numberPlayers,numBots);
        String boardData = gameBoard.toXMLString();
        
        for(int i = 0; i < numberPlayers; i++){
            port[i].send(boardData);
        }
        new Thread (this).start();
    }
    
    public void run (){
         card = new CardFactory();
         cList = new CardList[numberPlayers];
         selectList = new CardList[numberPlayers];
         
         String sendCard; // string for xml to send data
         
         for (int j = 0 ; j < numberPlayers; j++){
             cList[j] = new CardList();
            for (int i = 0; i < 7; i++){
                cList[j].add(card.createCard());
            }
           sendCard = cList[j].toXMLString();
           port[j].send(sendCard);
         }
         
         String selectedList;//string for xml cards that are selected
         for (int i = 0; i < numberPlayers; i++){
             selectedList = port[i].recieve();
             selectList[i] = CardList.read(new StringReader(selectedList));
         }
         
         EventList eList = new EventList();
         EventCounter counter = new EventCounter();
         String sList;
         
         for (int i = 0; i < numberPlayers; i++){
             for (int j = 0; j < 5; j++){
                selectList[j].get(i).execute(counter, eList, numBots[j], gameBoard);
                gameBoard.getLocation(numBots[j].getLocation()).effect(counter, eList, i, numBots[j], gameBoard);
             }
            gameBoard.getLocation(numBots[i].getLocation()).effect(counter, eList, i, numBots[i], gameBoard);
         }
         
         for (int i = 0; i < numberPlayers; i++){
             port[i].send(eList.toXMLString());
         }
         
    }
}
		