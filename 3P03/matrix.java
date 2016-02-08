/*
*	Tyler Martin
*	4836318
*	November 22 2013
*/


public class matrix{


	int[][] m;
	int[][] n;
	int num;

	public void multiply(int[] arr){
		num = arr.length-1;
		m = new int[num][num];
		n = new int[num][num];

		for (int i = 0; i<num; i++){
			m[i] = new int[num];
			m[i][i] = 0;
			n[i] = new int[num];

		}
		for(int v = 1; v<num; v++){
			for (int i=0;i<num-v;i++){
				int j = i+v;
				m[i][j] = Integer.MAX_VALUE;
				for(int k = i; k<j; k++){
					int q=m[i][k]+m[k+1][j]+arr[i]*arr[k+1]*arr[j+1];
					if(q<m[i][j]){
						m[i][j] = q;
						n[i][j] = k;
					}
				}
			}
		}
	}

	public void printOpt(){
		printOpt(n,0,num-1);
	}

	public void printOpt(int[][] s, int i, int j){
		if (i==j){
			System.out.print("A" + "<" + i + ">"+ " ");
		}
		else{
			System.out.print(" ( ");
			printOpt(n,i,n[i][j]);
			printOpt(n,n[i][j]+1,j);
			System.out.print(" ) ");
		}
	}

	public static void main (String[] args){
		matrix mat = new matrix();

		int[] test ={10,20,50,100};
		try{
			mat.multiply(test);
			mat.printOpt();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}