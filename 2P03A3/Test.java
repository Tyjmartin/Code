/**
 * @(#)Test.java
 *
 *
 * @author
 * @version 1.00 2012/11/7
 */


public class Test implements KeyedNode{
	double key;
	int cont;
    public Test(int a, double c) {

    	key=c;
    	cont=a;
    }
    public double getKey(){
    	return key;
    }

}