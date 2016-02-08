/**
 * @(#)Vertex.java
 *
 *
 * @author
 * @version 1.00 2012/11/6
 */
import java.util.*;

public class Vertex {

	int label, vx, vy;
	int numCount;
	Vertex next, start;
	List connects;
	boolean known = false;
	double maxValue = Double.MAX_VALUE;//the maximum value for prims

	//The actual vertex is created and connects to the arrayList
    public Vertex(int a, int x, int y) {
		label = a;
		vx = x;
		vy = y;
		this.connects = new ArrayList();
    }

    private Vertex (Vertex v){
    	label = v.label;
    	vx = v.vx;
    	vy = v.vy;
    	next = null;
    }

    public void connect(Vertex v){
    	next = new Vertex(v);
    	numCount++;
    }

	//The print for the adjacency list.
    public String print(){
    	String value = "Vertex "+this.label+"  "+" --> ";
    	for(int i = 0; i < connects.size(); i++){
    		value +=((Vertex)connects.get(i)).label+"  ";


    	}
    	return value;
    }

}