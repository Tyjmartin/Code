/**
 *
 * @(#)TestHarnessB.java
 *
 *
 * @author
 * @version 1.00 2012/11/9
 */
import java.util.*;

public class TestHarnessB {

    public TestHarnessB() {
/*    	int coordX, coordY, edNum, verNum, rand1, rand2;
    	Vertex vert;
    	edNum = 25;
    	verNum = 10;

    	Random number = new Random();
    	AdjList list = new AdjList(verNum);

    		coordX = number.nextInt(580);
    		coordY = number.nextInt(580);
    		vert = new Vertex(0, coordX, coordY);

    	for(int i = 0; i < verNum; i++){
    		coordX = number.nextInt(580);
    		coordY = number.nextInt(580);
    		vert = new Vertex(i, coordX, coordY);
    		list.addVert(vert,i);
    	}
    	for (int i = 0; i < edNum; i++){
    		Vertex temp;
    		temp = vert;
    		rand1 = number.nextInt(verNum);
    		rand2 = number.nextInt(verNum);
    		if (rand1 == rand2)
    			rand2 = number.nextInt(verNum);
    		temp = list.vert[rand1];
    		list.addEdge(temp, rand2);
    		temp = list.vert[rand2];
    		list.addEdge(temp,rand1);


    	}

		list.print();
*/
    }

	public static void main(String [ ] args){new TestHarnessB();
	}

}