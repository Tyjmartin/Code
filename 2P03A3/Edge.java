/**
 * @(#)Edge.java
 *
 *
 * @author
 * @version 1.00 2012/11/6
 */

import java.lang.Math.*;

public class Edge implements KeyedNode {

	public int Ux;
	public int Uy;
	public int Vx;
	public int Vy;
	public Vertex X,Y;//modified edge class to take the vertex.


	double edgeWeight = 0.0;

    public Edge(double edgeValue) {
    	edgeWeight=edgeValue;
    }

    public Edge(Vertex u, Vertex v){
    	this.Ux=u.vx;
    	this.Uy=u.vy;
    	this.Vx=v.vx;
    	this.Vy=v.vy;
    	this.X = u;
    	this.Y = v;

    	calcEdge();
    }


    public double calcEdge(){
    	return edgeWeight = Math.sqrt(Math.pow(Vy-Uy,2)+ Math.pow(Vx-Ux,2));
    }

    public double getKey(){
    	return this.edgeWeight;
    }
}