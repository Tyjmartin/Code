package RoboRace;

import java.util.*;
import java.io.*;

public class GameMaster { 
    
    // number of players
    private int numberPlayers;
    private String playerName[];
    private Robot numBots[];
    private Board gameBoard;
    private CardFactory card; 
    private CardList cList;
    private CardList selectList[];
    private Player player[]; 
    
    public GameMaster(int numPlayers, String[] playerNames){
       numberPlayers = numPlayers;
       playerName = playerNames;
       numBots = new Robot[numberPlayers];
       
       for (int i = 0; i < numberPlayers; i++){
           numBots[i] = new Robot(playerName[i], (2*i)+1);
       }
      gameBoard = new Board(Factory.load("Factory.xml"),numberPlayers,numBots);
      card = new CardFactory();
      player = new Player[numPlayers];
      for (int i = 0; i<numPlayers; i++){
        player[i] = new Player(playerName[i], gameBoard);
      }
    }
    
    public void run (){
       EventCounter counter = new EventCounter();
       EventList events = new EventList();
       selectList = new CardList[numberPlayers];
       for (Robot r: numBots){
           if (!r.isAlive()){
               r.revitalize();
           }
       }
       
       for (int j = 0; j < numberPlayers; j++){
           cList = new CardList();
           for (int i = 0; i < 7 ; i++){
                cList.add(card.createCard());
           }
         selectList[j] = player[j].selectCards(cList);
       }
         //CardList tempList = new CardList();
        for (int i = 0; i < 5; i++){
           for (int j = 0; j < numberPlayers; j++){
               //   tempList.add(selectList[j].get(i));
              //}
              //Collections.sort(tempList);

              //for (int k = 0; k < tempList.size(); k++){
                 //selectList[k].equals(tempList.get(k));
            //}


             selectList[j].get(i).execute(counter, events, numBots[j], gameBoard);
             gameBoard.getLocation(numBots[j].getLocation()).effect(counter, events, i, numBots[j], gameBoard);
           }
       }
    }
    
}
		