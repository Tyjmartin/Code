package RoboRace;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import COSC3P40.graphics.*;
import java.util.LinkedList;


public class CardPane extends JPanel implements MouseListener {
	
	private Image noCard;
	private Image[] selectImages;
	private CardList cards = null;
        private CardList selectedCard;
        private LinkedList<Integer> sList;
	
	public CardPane() {
		setPreferredSize(new Dimension(644,120));
		ImageManager manager = ImageManager.getInstance();
		noCard = manager.loadImage("Cards/NoCard.png");
		selectImages = new Image[5];
		for(int i=0; i<5; i++)
			selectImages[i] = manager.loadImage("Cards/Select" + (i+1) + ".png");
		addMouseListener(this);
	}
	
	public CardList selectCards(CardList list) {
            cards = list;
            selectedCard = new CardList();
            sList = new LinkedList<Integer>();
            repaint();
            
            synchronized(this){
                try{
                    wait();
                }
                catch(Exception e){
                    
                }
            }
            return selectedCard;
	}
	
	public void paintComponent(Graphics g) {
            g.fillRect(0, 0, this.getWidth(), 120);
            int offSet = 0;
            
            for (int i = 0; i < 7; i++){
                if (cards != null && cards.size() > i){
                    g.drawImage(cards.get(i).getImage(), offSet, 0, this);
                }
                else {
                    g.drawImage(noCard, offSet, 0, null); 
                }
                offSet += 92;
            }
            
            
                for (int i = 0; i < 5 ; i++){
                    if (selectedCard != null && sList.size() > i ){
                    g.drawImage(selectImages[i],(sList.get(i)*92)+30,50,null);
                }
            }
            
	}
	
	public void mouseClicked(MouseEvent e) {
             int x = e.getX();
            int y = e.getY();
            
            repaint();
            
            if (e.getButton() == MouseEvent.BUTTON1){
                if (x > 0 && x < 644 && y > 0 && y < 120){
                    if (!(selectedCard.contains(cards.get(x/92)))){
                        selectedCard.add(cards.get(x/92));
                        sList.add(x / 92);
                    }
                }     
            }
            
            else if (e.getButton() == MouseEvent.BUTTON3){
                if (selectedCard.size() <5 ){
                     selectedCard.removeLast();
                     sList.removeLast();
                }
            }
            
            if (selectedCard.size() == 5){
                synchronized(this){
                    notify();
                }
            }
             repaint();
            
	}
	
	public void mouseExited(MouseEvent e) {
	}
	
	public void mouseEntered(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
           
	}
	
	public void mouseReleased(MouseEvent e) {
	}

	
}