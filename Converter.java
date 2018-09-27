import java.util.*;
public class Converter {

	// I developed a converter to convert a decimal number to binary or a binary number to its decimal equivalent.
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ans = null;
		do
		{
			System.out.print("1.Convert Decimal to Binary\n2.Convert Binary to Decimal\n" + "Enter an option : ");
			int op = sc.nextInt();
			if(op == 1){
				System.out.print("Enter the decimal number : ");
				int num = sc.nextInt();
				System.out.println("The number in binary is " + decToBin(num));
			}
			else if(op == 2){
				System.out.print("Enter the binary number : ");
				String num = sc.next();
				System.out.println("The number in decimal is " + binToDec(num));
			}
			else{
				System.out.println("Wrong input entered");
			}
			System.out.print("Do you want to try again?(y/n) : ");
			ans = sc.next();
		}while(ans.equalsIgnoreCase("y"));
	}

	private static long binToDec(String x) {
		long dec = 0;
		for(int i=x.length()-1;i>=0;i--)
		{
			long power = (long) Math.pow(2.0, x.length()-i-1);
			dec+=(power*(x.charAt(i)-'0'));
		}
		return dec;
	}

	private static String decToBin(int x) {
		
		String bin = "";
		do{
			bin = x%2 + bin;
			x = x/2;
		}while(x>0);
		return bin;
	}

}
