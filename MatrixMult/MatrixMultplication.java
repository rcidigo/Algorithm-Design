////////
//FIXES- STATUS --> 'x'= Complete, 't'= currently on task, 'n'= need assistance
/////////
// program strassen for multiple case n> 2 (t)
// --- check split algo in strassen, X
// replace iterative prints with displayMatrix from dc and up X
// Get rid of lines commented out, replaced with method calls, CHECK FIRST
//
//
package cs331;
import java.util.*;
public class MatrixMultplication {

	public static long startTime;
	public static void main(String[] args) {

		// Variables needed
		Scanner kb=new Scanner(System.in);
		int [][] matrixA;
		int [][] matrixB;

		System.out.println("Please enter size of matrix n, such that resulting matrix is 2^n");
		int n= kb.nextInt();


		matrixA= new int[n][n];
		matrixB= new int[n][n];
		// populate matrix

		kb.nextLine(); //consume rest of line

		int y=0;
		for (int row=0; row < matrixA.length; row++)
			for (int col=0; col<matrixA[row].length; col++)
				matrixA[row][col]=y++;

		int y1=1;
		for (int row=0; row < matrixB.length; row++)
			for (int col=0; col<matrixB[row].length; col++)
				matrixB[row][col]=y1++;

		//		//display matrix
		//		System.out.println("MATRIX A :");
		//		displayMatrix(matrixA);
		//
		//		System.out.println("\n\n MATRIX B :");
		//		displayMatrix(matrixB);

		System.out.println("\nPlease choose the algorithm you would like to use : "
				+ " \nC for classic mult"
				+ " \nD for divide and conquer"
				+ " \nS for Strassens");

		String choice = kb.nextLine();

		startTime= System.nanoTime();
		// switch block for multiplication algorithms
		switch( choice.toUpperCase())
		{

		case "C" : classicMult(matrixA, matrixB,n);
		break;

		case "D" : dcmulti(matrixA,matrixB,n);
		break;

		case "S" : strassenmult(n,matrixA,matrixB);
		break;

		default  : System.out.println("Error not a recognizable algorithm"); System.exit(0);
		break;
		}
	}

	public static void classicMult ( int [][] a,  int[][] b, int n)
	{
		System.out.println(" You have chosen the classic matrix multiplication");
		int [][] c= new int [n][n];

		c=multMatrices(a,b);

		System.out.println("RESULTING MATRIX C :\n");

		displayMatrix(c);

		long finaltime= System.nanoTime();
		System.out.println("Total time in nanos: "+(finaltime-startTime));
	}

	public static void dcmulti ( int [][] a,  int[][] b, int n)
	{
		System.out.println(" You have chosen the divide and conquer matrix multiplication");
		int [][] c= new int [n][n];

		if( n==2)
		{
			int [][] C= new int [n][n];
			C=multMatrices(a,b);

			System.out.println("This is C \n");
			displayMatrix(C);


			long finalTime = System.nanoTime();
			System.out.println("The total time :" + (finalTime-startTime));
		}

		else if(n>2)
		{
			int x=0;
			//			for (int row=0; row < a.length; row++)
			//				for (int col=0; col<a[row].length; col++)
			//					a[row][col]=x++;

			for( int row1=0; row1 < a.length; row1++)
			{	
				for( int col1=0; col1<a[row1].length; col1++)
					System.out.print("["+ a[row1][col1]+"]");
				System.out.println();
			}

			System.out.println();
			// populate matrix b
			//			int y=1;
			//			for (int row=0; row < b.length; row++)
			//				for (int col=0; col<b[row].length; col++)
			//					b[row][col]=y++;

			for( int row1=0; row1 < b.length; row1++)
			{	
				for( int col1=0; col1<b[row1].length; col1++)
					System.out.print("["+ b[row1][col1]+"]");
				System.out.println();
			}

			if(n>2)
			{
				/// copy into a11
				int k=0;
				int l=0;
				int [][] a11= new int [n/2][n/2];
				int [][] a12= new int [n/2][n/2];
				int [][] a21= new int [n/2][n/2];
				int [][] a22= new int [n/2][n/2];

				int [][] b11= new int [n/2][n/2];
				int [][] b12= new int [n/2][n/2];
				int [][] b21= new int [n/2][n/2];
				int [][] b22= new int [n/2][n/2];

				//////////////////////////////////////////////////////
				//			MATRIX A
				/////////////////////////////////////////
				for (int i =0; i< n/2; i++)
				{
					for(int j=0; j <n; j++)
					{
						if(l < n/2 )
							a11[k][l++]=a[i][j];
						else if (l >= n/2)
						{
							a12[k][l-(n/2)]= a[i][j];
							l++;
						}			
					}
					l = 0;
					k++;			
				}

				k=0;
				l=0; //resetting l and k

				// for a21 and a22
				for (int i =n/2 ; i< n; i++)
				{
					for(int j=0; j <n; j++)
					{
						if(l < n/2 )
							a21[k][l++]=a[i][j];
						else if (l >= n/2)
						{
							a22[k][l-(n/2)]= a[i][j];
							l++;
						}			
					}
					l = 0;
					k++;			
				}

				/////////////////////////
				// *****B MATRIX*******		
				////////////////////////////
				int o=0;
				int p=0;
				for (int i =0; i< n/2; i++)
				{
					for(int j=0; j <n; j++)
					{
						if( p < n/2 )
							b11[o][p++]=b[i][j];
						else if (p >= n/2)
						{
							b12[o][p-(n/2)]= b[i][j];
							p++;
						}			
					}
					p = 0;
					o++;			
				}

				o=0;
				p=0; //resetting l and k

				// for b21 and b22
				for (int i =n/2 ; i< n; i++)
				{
					for(int j=0; j <n; j++)
					{
						if(p < n/2 )
							b21[o][p++]=b[i][j];
						else if (p >= n/2)
						{
							b22[o][p-(n/2)]= b[i][j];
							p++;
						}			
					}
					p = 0;
					o++;			
				}
				//			


				////////////////////////////////////////////////////
				// DISPLAY ALL ARRAYS
				//////////////////////////////

				System.out.println();
				displayMatrix(a11);

				System.out.println();
				displayMatrix(a12);

				System.out.println();
				displayMatrix(a21);

				System.out.println();
				displayMatrix(a22);


				System.out.println();
				displayMatrix(b11);

				System.out.println();
				displayMatrix(b12);

				System.out.println();
				displayMatrix(b21);

				System.out.println();
				displayMatrix(b22);

				int [][] c11= findC(a11,b11,a12,b21 ,n);
				int [][] c12= findC(a11,b12,a12,b22, n);
				int [][] c21= findC(a21,b11,a22,b21, n);
				int [][] c22= findC(a21,b12,a22,b22, n);

				long finaltime= System.nanoTime();

				System.out.println("Total time "+ (finaltime-startTime));
			}
		}
	}

	public static int [][] strassenmult  (int n ,int [][] a,  int[][] b )
	{
		System.out.println(" You have chosen the Strassen matrix multiplication");
		int [][] c11= new int [n][n];
		int [][] c12= new int [n][n];
		int [][] c21= new int [n][n];
		int [][] c22= new int [n][n];

		int [][] C= new int[n][n];

		if (n==2)
		{
			int [][] a11= new int [n/2][n/2];
			int [][] a12= new int [n/2][n/2];
			int [][] a21= new int [n/2][n/2];
			int [][] a22= new int [n/2][n/2];

			int [][] b11= new int [n/2][n/2];
			int [][] b12= new int [n/2][n/2];
			int [][] b21= new int [n/2][n/2];
			int [][] b22= new int [n/2][n/2];

			int k=0, l=0;
			for (int i =0; i< n/2; i++)
			{
				for(int j=0; j <n; j++)
				{
					if(l < n/2 )
						a11[k][l++]=a[i][j];
					else if (l >= n/2)
					{
						a12[k][l-(n/2)]= a[i][j];
						l++;
					}			
				}
				l = 0;
				k++;			
			}

			k=0;
			l=0; //resetting l and k

			// for a21 and a22
			for (int i =n/2 ; i< n; i++)
			{
				for(int j=0; j <n; j++)
				{
					if(l < n/2 )
						a21[k][l++]=a[i][j];
					else if (l >= n/2)
					{
						a22[k][l-(n/2)]= a[i][j];
						l++;
					}			
				}
				l = 0;
				k++;			
			}

			//for b
			int o=0;
			int p=0;
			for (int i =0; i< n/2; i++)
			{
				for(int j=0; j <n; j++)
				{
					if( p < n/2 )
						b11[o][p++]=b[i][j];
					else if (p >= n/2)
					{
						b12[o][p-(n/2)]= b[i][j];
						p++;
					}			
				}
				p = 0;
				o++;			
			}

			o=0;
			p=0; //resetting l and k

			// for b21 and b22
			for (int i =n/2 ; i< n; i++)
			{
				for(int j=0; j <n; j++)
				{
					if(p < n/2 )
						b21[o][p++]=b[i][j];
					else if (p >= n/2)
					{
						b22[o][p-(n/2)]= b[i][j];
						p++;
					}			
				}
				p = 0;
				o++;			
			}
			//			c11=addMatrices(multMatrices(a11,b11),multMatrices(a12,b21));
			//			c12=addMatrices(multMatrices(a11,b12),multMatrices(a12,b22));
			//			c21=addMatrices(multMatrices(a21,b11),multMatrices(a22,b21));
			//			c22=addMatrices(multMatrices(a21,b12),multMatrices(a22,b22));

			C= multMatrices(a,b);

			long finalTime= System.nanoTime();
//			System.out.println("Total time " +(finalTime-startTime));
//			System.out.println("This is C");
//			displayMatrix(C);
			return C;
		}

		else{

			int [][] a11= new int [n/2][n/2];
			int [][] a12= new int [n/2][n/2];
			int [][] a21= new int [n/2][n/2];
			int [][] a22= new int [n/2][n/2];

			int [][] b11= new int [n/2][n/2];
			int [][] b12= new int [n/2][n/2];
			int [][] b21= new int [n/2][n/2];
			int [][] b22= new int [n/2][n/2];

			// for a 
			int k=0, l=0;
			for (int i =0; i< n/2; i++)
			{
				for(int j=0; j <n; j++)
				{
					if(l < n/2 )
						a11[k][l++]=a[i][j];
					else if (l >= n/2)
					{
						a12[k][l-(n/2)]= a[i][j];
						l++;
					}			
				}
				l = 0;
				k++;			
			}

			k=0;
			l=0; //resetting l and k

			// for a21 and a22
			for (int i =n/2 ; i< n; i++)
			{
				for(int j=0; j <n; j++)
				{
					if(l < n/2 )
						a21[k][l++]=a[i][j];
					else if (l >= n/2)
					{
						a22[k][l-(n/2)]= a[i][j];
						l++;
					}			
				}
				l = 0;
				k++;			
			}

			//for b
			int o=0;
			int p=0;
			for (int i =0; i< n/2; i++)
			{
				for(int j=0; j <n; j++)
				{
					if( p < n/2 )
						b11[o][p++]=b[i][j];
					else if (p >= n/2)
					{
						b12[o][p-(n/2)]= b[i][j];
						p++;
					}			
				}
				p = 0;
				o++;			
			}

			o=0;
			p=0; //resetting l and k

			// for b21 and b22
			for (int i =n/2 ; i< n; i++)
			{
				for(int j=0; j <n; j++)
				{
					if(p < n/2 )
						b21[o][p++]=b[i][j];
					else if (p >= n/2)
					{
						b22[o][p-(n/2)]= b[i][j];
						p++;
					}			
				}
				p = 0;
				o++;			
			}

			int [][] P= strassenmult(n/2,addMatrices(a11, a22),addMatrices(b11,b22));
			int [][] Q= strassenmult(n/2, addMatrices(a21,a22), b11);
			int [][] R= strassenmult(n/2, a11,subtractMatrices(b12,b22));
			int [][] S= strassenmult(n/2, a22, subtractMatrices(b21,b11));
			int [][] T= strassenmult(n/2, addMatrices(a11,a12),b22);
			int [][] U= strassenmult(n/2, subtractMatrices(a21,a11), addMatrices(b11,b12));
			int [][] V= strassenmult(n/2, subtractMatrices(a12,a22), addMatrices(b21,b22));

			c11= addMatrices(subtractMatrices(addMatrices(P,S),T),V);	
			c12= addMatrices(R,T);
			c21= addMatrices(Q,S);
			c22= addMatrices(subtractMatrices(addMatrices(P,R),Q),U);
			long finalTime=System.nanoTime();
			
//			System.out.println("Display all matrices");
//			displayMatrix(c11);
//			displayMatrix(c12);
//			displayMatrix(c21);
//			displayMatrix(c22);

			
			System.out.println("Total time :" +(finalTime-startTime));
			return c11;
		}
	}
	public static int [][] findC(int [][] a1, int [][] b1, int [][] a2, int [][] b2, int n)
	{
		int [][]temp1= new int [n/2][n/2];
		int [][]temp2= new int [n/2][n/2];
		int [][] c= new int [n/2][n/2];

		temp1=multMatrices( a1,b1);

		System.out.println("This is temp 1 \n");
		displayMatrix(temp1);

		// temp2 a12c *b21
		temp2=multMatrices(a2, b2);

		System.out.println("This is temp 2 \n");
		displayMatrix(temp2);

		// c= temp1 + temp2
		c= addMatrices(temp1,temp2);

		System.out.println("This is c11 \n");
		displayMatrix(c);

		return c;
	}

	public static int [][] multMatrices (int[][]a, int[][] b)
	{
		int [][] result= new int [a.length][a.length];
		for (int row=0; row < result.length; row++)
		{
			for(int col=0; col < result.length; col++)
			{
				int sum =0;
				for (int t =0 ; t<result.length ; t++)
				{
					sum+= a[row][t]* b[t][col];
				}
				result[row][col]=sum;
			}
		}
		return result;
	}
	public static int [][] addMatrices( int a [][], int [][] b)
	{
		int [][] result = new int [a.length][a.length];
		for(int i=0;i<result.length;i++)
			for(int j=0; j<result.length;j++)
				result[i][j]= a[i][j] + b[i][j];
		return result;

	}
	public static int [][] subtractMatrices (int [][] a, int [][] b)
	{
		int [][] result = new int [a.length][a.length];
		for(int i=0;i<result.length ;i++)
			for(int j=0; j<result.length;j++)
				result[i][j]= a[i][j] - b[i][j];
		return result;
	}
	public static void displayMatrix(int [][] a)
	{
		for( int row=0; row < a.length; row++)
		{	
			for( int col=0; col < a[row].length; col++)
				System.out.print("["+ a[row][col]+"]");
			System.out.println();
		}
	}

}



