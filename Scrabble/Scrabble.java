/*
The goal of the program is to make some words using the letters the user types in.

*/

package CS211;
import java.util.*;

public class Boggle {
    static List<String> solutions = new ArrayList<String>();

    static String[] dictionary;
        public static void main(String[] args){
            FileIO io = new FileIO();
            dictionary = io.load("X:\\CS211\\dictionary.txt");  
            for(int i = 0;i<dictionary.length;i++){
                dictionary[i] = dictionary[i].trim();
            }
            System.out.println("Enter the 16 letters row by row: ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            for(String dictionaryWord:dictionary){
                if(canMake(dictionaryWord,input)){
                    solutions.add(dictionaryWord);
                }
            }
            System.out.println("Here are words you can make: ");
            int count = 1;
            for(String word: solutions){
                System.out.println(word);
                count++;
            }
            System.out.println("Total = " + count);
            
    }
        public static boolean canMake(String word, String allowedLetters){
            for(char c: word.toCharArray()){
                if(allowedLetters.indexOf(""+c)==-1){
                    return false;
                }
                allowedLetters=allowedLetters.replaceFirst(""+c,"");
            }
            return true;
        }
}

