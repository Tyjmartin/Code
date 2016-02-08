/**
 * @(#)TestHarnessA.java
 * This is my main prims class, please ignore that it is called test harness
 * Also I am forgoing user input, it works for 10 and 25 and can be modified in the code to work for other values, I just wanted it handed in.
 *
 * @author
 * @version 1.00 2012/11/7
 */
import java.util.*;

public class TestHarnessA {

	public	Edge[] wtf; //Where all my edges are stored and then they are dumped to the vertices.
    public int edNum = 25;
    public	int verNum = 10;
    MinHeap<Edge> a= new MinHeap<Edge>(50); // min heap of 50, because the values go both ways
    public	AdjList list;

    // This code works for part A, if you want to test just block out the other test harness constructor.
    //
   /* public TestHarnessA() {
    	int num;
    	Random generator = new Random();

    	MinHeap<Edge> a= new MinHeap<Edge>(20);

    	for(int i = 0;i<20;i++){
			num = generator.nextInt(100);
    		a.Add(new Edge(num));
    	}
		while(!a.isEmpty()){
			Edge f= a.remove();
			System.out.println(f.edgeWeight);
		}

    }*/

	// This is part B, I did all my work in the constructor I am sorry this is really bad form. But it works and its is 11:30 and i am not changing it.
    public TestHarnessA() {
    	int edNum, verNum;
    	Vertex vert;
		Edge work;

		//creates a new adjacency list of size 10.
		list = new AdjList(10);
		verNum = 10;


		//Filling the adjacency list with values,
		for (int i = 0; i < verNum;i++){
			list.addVert(i);
		}

		//createEdge will fill an edge array with edges for vertices
		createEdge();
		work = new Edge (list.vert[0],list.vert[0]);//this is pointing to itself. and calculating the edgeweight on 0.
		Edge temp;
		list.vert[0].maxValue=0.0;
		list.vert[0].next = list.vert[0];
		a.Add(work);//adding to the heap.
		//This is my prims alg it is directly from profs slides
		while(!a.isEmpty()){
			work = (Edge)a.remove();
			if(work.X.known){continue;}
			work.X.known = true;
			Object[] test = work.X.connects.toArray();
			for(Object o: test){
				Vertex v = (Vertex)o;
				temp = new Edge(v, work.X);
				if (v.maxValue > temp.edgeWeight && !v.known ){
					v.maxValue = temp.edgeWeight;
					v.next = work.X;
					a.Add(temp);
				}
			}


		}

		// This is my print for the adjacency list, it calls a method for vertex that loops through each vertex and returns all values in a string.
		for (int i = 0; i<list.size;i++){
			System.out.println(list.vert[i].print());
		}
		System.out.println();


		//The print for the table, done here because of ease and time. Made a table class dont actually know if it works/

		System.out.println("Minimim Spanning Tree");
		System.out.println("-----------------------------------------");
		System.out.println("Vertex  "+"Known  "+"dv  "+"pv ");

		double max = 0.0;
		for(int i = 0; i<list.vert.length;i++){
			max = list.vert[i].maxValue + max;
			System.out.print(list.vert[i].label+"  ");
			System.out.print(list.vert[i].known+"   ");
			System.out.print(list.vert[i].maxValue+"  ");
			System.out.print(list.vert[i].next.label+"\r\n ");
		}
		System.out.println();
		System.out.println("--------------------------------");
		System.out.println("Total Edge Weight "+ max);

	}

	//This is creating edges for the list
	public void createEdge(){
		int one, two;
		wtf = new Edge[2*edNum]; // doing 2 the edge number because the list is bi-directional

		for (int i = 0; i < wtf.length; i= i+2){
			one = (int)(Math.random()*10);
			two = (int)(Math.random()*10);

			while (one == two){
				two = (int)(Math.random()*10);
			}

			// error check making sure the numbers are not all ready in the list, and being able to provide new numbers and checking over and over.
			Edge temp = new Edge(list.vert[one],list.vert[two]);
			boolean isValid = false;
			while(true){
				isValid = false;
				for(int j = 0; j < i; j++){
					if (temp.X == temp.Y){
						isValid = true;
						break;
					}
					if (temp.X.label == wtf[j].X.label){
						if (temp.Y.label == wtf[j].Y.label){
							isValid = true;
							break;
						}
					}
				}
				if (isValid){
						two = (int)(Math.random()*10);
						temp = new Edge(list.vert[one],list.vert[two]);
						continue;
					}
				else{
					break;
				}
			}
			//Filling and edge array with all the edges, doing it bi-directional.
			wtf[i] = temp;
			wtf[i+1] = new Edge(list.vert[two],list.vert[one]);
		}
		//Setting the actual edges in the arraylist
		for (Edge ed: wtf){
			int u = ed.X.label;
			int v = ed.Y.label;
			list.vert[u].connects.add(list.vert[v]);
			//System.out.println(u+" is Connected to "+v+" with weight of "+ed.edgeWeight);
			}


	}


    public static void main(String [ ] args){new TestHarnessA();
    }

}
