/**
 * @(#)Table.java
 *
 *
 * @author
 * @version 1.00 2012/11/7
 */


public class Table {

	private int[][] tab;

    public Table(int size) {
    	tab=new int[size][4];
    }

    public int getPv(int loc){
    	return tab[loc][3];

    }

    public int getDv(int loc){
    	return tab[loc][2];
    }

    public boolean getKnown(int loc){
    	if(tab[loc][1]==1) return true;
    	else return false;
    }

    public void setKnown(int loc, boolean value){
    	if(value) tab[loc][1]=1;
    	else tab[loc][1]=0;
    }

    public void setPv(int loc, int value){
    	tab[loc][3]=value;
    }

    public void setDv(int loc, int value){
    	tab[loc][2]=value;
    }

    public int totalWeight(){
    	int total = 0;
    	for (int i = 0; i < tab.length; i++){
    		total = total + getDv(i);
    	}
    	return total;
    }


    public void print(){
    	System.out.println("Minimum Spanning Tree");
    	System.out.println("------------------------------------------");
    	System.out.println("Vertex " + " Known " + " dv " + " pv " );
    	for (int i = 0; i < tab.length; i++){
    		for (int j = 0; j < 4; j++){
    			if(j==1) System.out.print(getKnown(i) +"  ");
    			else System.out.print(tab[i][j]+"  ");
    		}
    		System.out.println();
    	}
    	System.out.println("------------------------------------------");
    	System.out.println("Total Edge Weight = " + totalWeight());
    }
}