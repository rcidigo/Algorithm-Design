package cs331;
import java.util.*;
public class SortingK {
	public static int pos;
	public static Random rand= new Random();
	public static long startTime;

	public static void main(String[] args) {

		Scanner kb = new Scanner(System.in);

		System.out.println("Enter size of array n");
		int n= kb.nextInt();

		int [] array1= new int[n];
		int [] array2= new int[n];
		int [] array3= new int[n];
		int [] array4= new int[n];

		int randomInt;

		// populate array with random integers
		for(int i=0;i< array1.length;i++)
		{
			randomInt= (rand.nextInt(1000))+1;
			array1[i]=randomInt;

		}

		// copying elements from array 1 to other arrays
		for(int i=0;i<array1.length;i++)
			array2[i]=array1[i];

		for(int i=0;i<array1.length;i++)
			array3[i]=array1[i];

		for(int i=0;i<array1.length;i++)
			array4[i]=array1[i];

		//  displayMatrix(array2);

		//display matrix
		//displayMatrix(array1);

		int left=0;
		int right= (array1.length)-1;
		System.out.println();

		algo1(array1, left, right, (3*n)/4);
		//		displayMatrix(array1);
		//		System.out.println();
		algo2(array2, left, right, (3*n/4));
		algo3(array3, left, right, (3*n)/4);
		algo4(array4, left, right, (3*n)/4);
		//		
		//display after sort
		//displayMatrix(array2);

			
		//		displayMatrix(array2);
	}
	public static void algo1( int [] a, int leftindex, int rightindex, int k)
	{
		System.out.println("\nALGORITHM 1:");
		startTime=System.nanoTime();
		doMergeSort(a, leftindex, rightindex);
		System.out.print("The "+k+" smallest item is: "+a[k-1]+"\n");
		long finalTime=System.nanoTime();

		System.out.println("Time taken: "+ (finalTime-startTime));
	}
	public static void algo3( int [] a, int leftindex, int rightindex, int k)
	{
		System.out.println("ALGORITHM 3:");
		startTime=System.nanoTime();
		partition(a, k, leftindex, rightindex);

		// time take
		long finalTime=System.nanoTime();
		System.out.println("Time taken: "+ (finalTime-startTime));

	}
	public static int partition( int [] a, int k,  int left, int right)
	{
		int P= rand.nextInt(right-left +1)+left;
		int count=0;

		//System.out.println("Pivot index is "+P);
		// count how many items are less than pivot
		for(int i=0; i< a.length; i++)
			if(a[i] < a[P]) count++;

		//System.out.println("Count is "+ count);
		// switch pivot place to count
		int temp= a[P];
		a[P]= a[count];
		a[count]= temp;

		//	System.out.println("AFTER PIVOT AND COUNT SWITCH");
		//	displayMatrix(a);
		//	System.out.println();
		// pivot element equals count
		P=count;

		// swapping elements on left of pivot with those on right

		for(int t=0; t<P; t++)
		{
			if(a[t] > a[P])
			{
				for(int j=P; j< a.length;j++ )
					if( a[j] < a[P])
					{
						int temp2= a[j];
						a[j]=a[t];
						a[t]=temp2;
						break;
					}

			}
		}

		if (k==P) return a[P];
		if (k< P) return partition(a,k, left,P);
		if (k> P) return partition(a,k, P, right);

		return 0;
	}
	public static void algo2(int [] a, int leftindex, int rightindex, int k )
	{
		System.out.println("ALGORITHM 2: ITERATIVE QUICKSORT");
		startTime=System.nanoTime();
		
		iterativeQsort(a);

		// time take
		long finalTime=System.nanoTime();
		System.out.println("Time taken: "+ (finalTime-startTime));

	}
	
	public static void algo4(int [] a, int leftindex, int rightindex, int k)
	{
		System.out.println("ALGORITHM 4: Median of Medians");
		startTime=System.nanoTime();
		
		int Pivot=choosePivot(a);
		
		partition2( a, k, leftindex, rightindex, Pivot );

		// time take
		long finalTime=System.nanoTime();
		System.out.println("Time taken: "+ (finalTime-startTime));

	}
	public static int choosePivot( int [] a)
	{
		if( a.length<5)
		{
			int left=0;
			int right= a.length-1;
			doMergeSort(a, left, right);
			int P= a[(left+right)/2];
			System.out.println("Pivot is element"+ P);
			return P;
		}

		int left=0;
		int [] C= new int[a.length/5];

		for (int i=0; i< a.length+1; i++) // try incrementing i by 5
		{
			if(i > 0 && i%5==0)
			{
				int right=i-1;
				doMergeSort(a, left,right);
				left+=5;
			}

		}
		System.out.println(" \n Matrix After ");
		displayMatrix(a);

		int x=0, y=4;
		for(int i=0; i< C.length;i++)
		{
			C[i]= a[(x+y)/2];
			x+=5;
			y+=5;

		}

//		System.out.println(" \n Median Array consists of: ");
//		displayMatrix(C);
		return choosePivot(C);


	}
	public static int partition2(int [] a, int k,  int left, int right, int P )
	{
		int PivotIndex=0;
		// find pivot in original array
		for(int i=0; i< a.length;i++)
		{
			if(a[i]==P)
			{
				PivotIndex=i;
				break;
			}
		}	
		
		int count=0;

		System.out.println("Pivot index is "+P);
		// count how many items are less than pivot
		for(int i=0; i< a.length; i++)
			if(a[i] < a[P]) count++;

		System.out.println("Count is "+ count);
		// switch pivot place to count
		int temp= a[P];
		a[P]= a[count];
		a[count]= temp;

		System.out.println("AFTER PIVOT AND COUNT SWITCH");
		displayMatrix(a);
		System.out.println();
		// pivot element equals count
		P=count;

		// swapping elements on left of pivot with those on right

		for(int t=0; t<P; t++)
		{
			if(a[t] > a[P])
			{
				for(int j=P; j< a.length;j++ )
					if( a[j] < a[P])
					{
						int temp2= a[j];
						a[j]=a[t];
						a[t]=temp2;
						break;
					}

			}
		}
		
		if(k==PivotIndex)  return a[PivotIndex];
		if(k<PivotIndex)   return partition2(a, k, left, PivotIndex, P);
		if(k >PivotIndex)  return partition2(a,k-PivotIndex-1,PivotIndex+1, right,P);
		
		return 0;
	}
	public static void iterativeQsort(int[] arr) { 
	    Stack<Integer> stack = new Stack<Integer>();
	    stack.push(0);
	    stack.push(arr.length);
	    while (!stack.isEmpty()) {
	        int end = stack.pop();
	        int start = stack.pop();
	        if (end - start < 2) continue;
	        int p = start + ((end-start)/2);
	        p = iterativepartition(arr,p,start,end);

	        stack.push(p+1);
	        stack.push(end);

	        stack.push(start);
	        stack.push(p);

	    }
	}
	public static int iterativepartition(int[] arr, int p, int start, int end) {
	    int l = start;
	    int h = end - 2;
	    int piv = arr[p];
	    swap(arr,p,end-1);

	    while (l < h) {
	        if (arr[l] < piv) {
	            l++;
	        } else if (arr[h] >= piv) { 
	            h--;
	        } else { 
	            swap(arr,l,h);
	        }
	    }
	    int idx = h;
	    if (arr[h] < piv) idx++;
	    swap(arr,end-1,idx);
	    return idx;
	}
	public static void swap(int [] array, int s1, int s2 )
	{
		int temp= array[s1];
		array[s1]=array[s2];
		array[s2]=temp;
	}

	public static void doMergeSort(int [] a, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) 
		{
			int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
			// Below step sorts the left side of the array
			doMergeSort(a,leftIndex, middleIndex);
			// Below step sorts the right side of the array
			doMergeSort(a, middleIndex + 1, rightIndex);
			// Now merge both sides
			mergeParts(a,leftIndex, middleIndex, rightIndex);
		}
	}

	public static void mergeParts(int [] a, int leftIndex, int middleIndex, int rightIndex) 
	{

		if(a[middleIndex ]<= a[middleIndex+1]) return;

		//setting p and q cursors to heads of lists
		// creating new temporary array with fresh corresponding to index
		int p = leftIndex;
		int q = middleIndex + 1;
		int fresh=0; 
		// new array elements will be copied into
		int []ab=new int[(rightIndex+1)-leftIndex];

		while (p <= middleIndex && q <= rightIndex)
		{

			// a[p]gets added to new array and p is inc
			if (a[p] <= a[q]) 
			{
				ab[fresh++] = a[p];
				p++;

			}
			if (a[p] >= a[q])
			{
				ab[fresh++]=a[q];
				q++;

			}
		}

		// case where atleast one list has some elements left over that are just copied into ab
		while(p <= middleIndex)
		{
			ab[fresh++]=a[p];
			p++;

		}

		while(q <= rightIndex)
		{
			ab[fresh++]=a[q];
			q++;

		}

		// putting elements in ab to a
		for(int i=leftIndex; i<= rightIndex;i++)
		{
			a[i]=ab[i - leftIndex];

		}

	}
	public static void displayMatrix(int [] a){
		for(int i=0;i< a.length;i++)
		{
			if(i%10==0)
				System.out.println();
			System.out.print(a[i]+",");

		}
	}

}

