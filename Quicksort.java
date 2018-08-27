/*

Problem Statement
The goal is to sort a long list of words by special order. The special order is as follows. 
All the words are sorted by the 'greatest' character in that word. 
What this means is that all words whose greatest character is a 'a' (i.e. that feature only a's) should come first, followed by all words
whose greatest character is a 'b' (e.g. "baa"), followed by all those words whose greatest character is an 'c' (e.g. "cab") and so forth. 
Here 'greatest'means furthest along in the alphabet. 
The very last words in the list will be those whose greatest character is a 'z' (i.e. any words that have a 'z' in them).
For words that have the same greatest character (e.g. "salt" and "table", which both feature a 't' as their greatest character),
these should be sorted alphabetically (so 'salt' would come first). 

*/

import java.util.*;
public class Quicksort{

    public static String[] myarray;

    public static void main(String[] args){

        Scanner myscanner = new Scanner(System.in);
        int num = Integer.parseInt(myscanner.nextLine());
        String[] array = new String[num];
        myarray = new String[num];
        for(int i=0;i<num;i++){
            myarray[i]=myscanner.nextLine();
        }

        recQuickSort(myarray);

        for(int i=0;i<num;i++){
            System.out.println(myarray[i]);
        }
    }

    public static void recQuickSort(String arr[]){
        recQuickSort(arr, 0, arr.length-1);
    }

     //This will execute the quick sort
     public static void recQuickSort(String arr[], int left, int right){
	//base case
	if(left >= right)
		return;

	//Pivot is rightmost element in the array
	int pivot = right;
	int rightScan = right-1;
	int leftScan = left;

	while(leftScan < pivot && rightScan >= 0){
		if(leftScan == rightScan){
			if(compare(arr[leftScan], arr[pivot]))
				swap(arr, leftScan, pivot);
			break;
		}

		if(leftScan > rightScan){
			swap(arr, leftScan, pivot);
			break;
		}

		if(compare(arr[leftScan], arr[pivot]) && !compare(arr[rightScan], arr[pivot])){
			swap(arr, leftScan, rightScan);
			leftScan++;
			rightScan--;
		}

		else if(compare(arr[leftScan], arr[pivot]))
			rightScan--;

		else if(!compare(arr[rightScan], arr[pivot]))
			leftScan++;

		else{
			leftScan++;
			rightScan--;
		}
	}
	recQuickSort(arr, left, rightScan);
	recQuickSort(arr, leftScan+1, right);
    }
    
    public static boolean check(String one, String two){
	int bigone=0;
	int bigtwo=0;
	for(int i=0;i<one.length();i++){
		if((int)one.charAt(i)>bigone){
			bigone=(int)one.charAt(i);
		}
	}
	for(int i=0;i<two.length();i++){
		if((int)two.charAt(i)>bigtwo){
			bigtwo=(int)two.charAt(i);
		}
	}

	if(bigone>bigtwo){
		return true;
	}else if (bigone<bigtwo){
		return false;
	}
	if(one.compareTo(two)>0){
		return true;
	}else{
		return false;
	}
}
    
