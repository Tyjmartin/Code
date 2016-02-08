/**
 * @(#)AdjList.java
 *
 *
 * @author
 * @version 1.00 2012/11/7
 */


public class AdjList {

	Vertex[] vert; //This is the vertex list.
	int i = 0;
	int numEdge = 0;
	int maxEdge = 25;
	int size;
	int[] check = new int[20];

	//creates the list of size a and allows for access of the size.
   public AdjList(int a) {
    	vert = new Vertex[a];
    	size = a;
    }

	//this adds the vertex to the array with error checking to make sure none are the same.
    public void addVert(int v){

    		int x = (int)(Math.random()*100);
    		int y = (int)(Math.random()*100);

    		check[i]=x;
    		check[i+1]=y;
    		for (int j = 0; j<20;j++){
    			if (x == check[j]){
    				x=x+1%100;
    			}
    			if (y == check[j]){
    				y=y+1%100;
    			}
    		}

    	vert[v] = new Vertex(v,x,y);
    	i = i+2;

    }

}