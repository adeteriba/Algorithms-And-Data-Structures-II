// I wrote a program that takes in a sentence from the user, and outputs that sentence in Morse code.

import javax.sound.sampled.*;
import java.util.Scanner;

public class MorseCode{
	public static void main(String[] args) throws Exception {
		char[] english = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
		'i', 'j', 'k', 'l','m', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v','w', 'x',
		'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8',
		'9', '0',',', '.', '?'};
	
		String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.",
		"--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", 
		".-.","...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
		"..---", "...--", "....-", ".....", "-....", "--...",
		"---..", "----.","-----", "--..--", ".-.-.-", "..--.." };

		for(int i=0;i<39;i++){
			int random=(int)(Math.random()*39);
			char temp = english[i];
			english[i]=english[random];
			english[random]=temp;
		}
		
		FileIO io = new FileIO();
		String[] original = io.load("C:\\ file.txt");

		for(int i=0;i<original.length;i++){
			String sentence=original[i];
			for(int j=0;j<sentence.length()-1;j++){
				int index = 0;
				if(sentence.charAt(j)==' '){
					// Thread.sleep(200);
				}else{
					while(Character.toLowerCase(sentence.charAt(j))!=english[index]){
						if(index==english.length-1){
							System.out.println("You've entered a nonMorse character!!!");
							System.out.println("It's"+Character.toLowerCase(sentence.charAt(j)));
							System.exit(0);
						}
						index++;
					}
					System.out.println(english[index]);
					for(int k=0;k<morse[index].length();k++){
						if(morse[index].charAt(k)=='.'){
							tone(700,50,1);
						}else if(morse[index].charAt(k)=='-'){
						tone(700,150,1);
					}
					Thread.sleep(150);
				}
			}
		}
	}
 }
 
 public static void tone(int hz, int msecs, double vol) throws LineUnavailableException{
	float SAMPLE_RATE = 8000f;
	byte[] buf = new byte[1];
	AudioFormat af = new AudioFormat(SAMPLE_RATE,8,1,true,false);
	SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
	sdl.open(af);
	sdl.start();
	for (int i=0; i < msecs*8; i++) {
		double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
		buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
		sdl.write(buf,0,1);
		}
		sdl.drain();
		sdl.stop();
		sdl.close();
	}
}
