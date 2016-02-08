/*
*	Tyler Martin
*	4836318
*	November 22 2013
*/

import java.util.Random;
import java.util.Stack;

public class LAS
{
	public LAS(){
		int [] k = {7,3,8,4,6,20,30,1,78,4,11,45,33,56,3,76,4,1,64,89};
		printLAS(k);
		
		int [] a = {20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
		printLAS(a);
		
		int [] s = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		printLAS(s);
		
		int [] d = {99,2,14,76,2,34,45,1,9,7,5,12,34,5,75,6,88,42,3,5};
		printLAS(d);
		
		int [] f = {1,2,35,4,32,37,89,64,45,1,4,6,58,88,79,52,83,5,54,7};
		printLAS(f);
		
		int [] g = {45,54,1,4,64,42,56,1,27,23,7,45,56,23,75,13,5,4,43,21};
		printLAS(g);
		
		int [] h = {12,21,45,4,1,45,76,45,35,69,53,13,4,45,34,5,5,64,4,1};
		printLAS(h);
		
		int [] j = {34,34,43,12,3,4,64,4,3,1,4,64,12,23,54,34,85,86,65,4};
		printLAS(j);
		
		int [] l = {78,13,1,3,4,6,86,54,43,31,4,34,64,66,67,98,54,65,23,4};
		printLAS(l);
		
		int [] q = {1,2,4,5,5,6,7,7,8,9,6,5,6,3,2,3,4,4,1,2};
		printLAS(q);
		
		
	}

	public static void printLAS(int [] a)
	{
		int maxLen = 1;
		int bestEnd = 0;

		int[] dp = new int[a.length];
		int[] prev = new int[a.length];

		dp[0] = 1;
		prev[0] = -1;

		for (int i = 1; i < a.length; i++){
			dp[i] = 1;
			prev[i] = -1;
			for (int j = i-1; j >= 0; j--){
				if (dp[j] + 1 > dp[i] && a[j] < a[i]){
					dp[i] = dp[j] +1;
					prev[i] = j;
				}
			}
			if (dp[i] > maxLen){
				bestEnd = i;
				maxLen = dp[i];
			}
		}

		Stack<Integer> stack = new Stack<Integer>();

		int k = bestEnd;
		stack.push(a[k]);
		while(prev[k] >=0){
			stack.push(a[prev[k]]);
			k = prev[k];
		}
		while (!stack.isEmpty()){
			System.out.print(stack.pop() + " ");
		}
		System.out.println();
	}

	public static void fillArray(int [] a){
		int size = 20;
		a = new int[size];


		Random generator = new Random();

		for (int i=0; i<size; i++){
			a[i] = generator.nextInt(100);
		}
	}


	public static void main(String[] args){
		
		LAS lis = new LAS ();
			
	}
}