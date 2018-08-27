/*
Problem Statement
The goal is to insert all the words into the hash table and then find them all again with the minimum number of collisions
*/

import java.util.*;
import java.math.BigInteger;

public class Hashing{    
    
    public static void main (String[] args){
        Scanner myscanner = new Scanner(System.in);
        int items = myscanner.nextInt();
        myscanner.nextLine();
        String[] contents = new String[items];
        for(int i=0;i<items;i++){
            contents[i]=myscanner.nextLine();
        }
        HashTable mytable = new HashTable();
        Solution mysolution = new Solution(mytable);
        mysolution.fill(contents);
        mysolution = new Solution(mytable);
        for(int i=0;i<items;i++){
            int rand = (int)(Math.random()*items);
            String temp = contents[i];
            contents[i]=contents[rand];
            contents[rand]=temp;
        }
        for(int i=0;i<items;i++){
            int slot = mysolution.find(contents[i]);
            if(!mytable.check(slot,contents[i])){
                System.out.println("error!");
            }
        }
        System.out.println(mytable.gettotal());
    }
}
     
class HashTable{
    
    public int size = 99991;
    private String[] hashTable;
    private int total=0;

    public HashTable(){
        hashTable = new String[size];
        for(int i=0;i<size;i++){
            hashTable[i]="";
        }
    }

    public boolean check(int slot, String check){
        if(hashTable[slot].equals(check)){
            return true;
        }else{
            total++;
            return false;
        }
    }
    
    public void set(int slot, String word){
        hashTable[slot]=word;
    }
    
    public int gettotal(){
        return total;
    }
} 

//Bellow is my written code in order to completer the problem
class Solution{
    HashTable mytable = new HashTable();
    static int size;
    public Solution(HashTable input){
        mytable = input;
        size = input.size;
    }
    //fill this in so as to minimize collisions
    //this method should return the slot in the HashTable where the word is 
    public int find(String word){
        int index = getHashKey(word);
        int doubleHashIndex = getDoubleHashKey(word);
        //While there's a collision
        while(mytable.check(index, word) == false){
        	//Add the double hash jump size until you find word 
            index= index + doubleHashIndex;
            index= index % size;
        }
        return index;
    }
    //fill this in using some hashing strategy
    //this should add all the words in the array into the HashTable
    public void fill(String[] array){
        for(int i=0; i<array.length;i++){
            int index = getHashKey(array[i]);
            int doubleHash = getDoubleHashKey(array[i]);
            
            //While there are collisions
            while(mytable.check(index, "") == false){
            	//Add double hash jump until a free slot is found
                index=index+doubleHash;
                index=index%size;
            }
            
            mytable.set(index, array[i]);
         }
    }
    //this is the primary hash key function - it should return a number which is a slot in the hash table
  	//for words, a good strategy is to raise each character to successive powers of the alphabet size
  	//assume that the alphabet is ASCII characters - a total of 256 possibilities
  	//each successive character value should be raised to successive powers of 256
  	//the whole thing is added together and then moduloed to create a hash table index
    public static int getHashKey(String word){
    BigInteger a;
 		BigInteger b;
 		BigInteger power = new BigInteger("29");
 		BigInteger result = new BigInteger("0");
 		
 		//Multiply each char in the string by successive powers of 29 and add them together
 		for(int i = 0; i < word.length(); i++){
 			a = new BigInteger(Integer.toString(word.charAt(i)));
 			b = power.pow(i);
 			result = result.add(a.multiply(b));	
 		}
 		
 		BigInteger tableSize = new BigInteger(Integer.toString(size));
 		result = result.mod(tableSize);
 		
 		return result.intValue();
    }
	//this method should be different to the primary hash function
	//it should return a different number for words which generated the same primary hash key value
	//for example, you could just add up all of the letters in the word
    public static int getDoubleHashKey(String word){
    	int start = 7;
    	
    	for(int i = 0; i < word.length(); i++)
    		start = 31 * start + word.charAt(i);
    	
    	int max = 7; //Max jump size
 		return max - (start % max);
     }
}
