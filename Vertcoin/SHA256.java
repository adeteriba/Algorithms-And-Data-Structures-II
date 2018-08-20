/*
What is Vertcoin? What is Cryptography?
A cryptocurrency is a digital or virtual currency designed to work as a medium of exchange. 
It uses cryptography to secure and verify transactions as well as to control the creation of new units of a particular cryptocurrency
Vertcoin is a cryptocurrency and in order to hold that currency youwill need a virtual wallet.
I created my own Vertcoin paper wallet, including public and private key instead of using randomly generated keys from third party websites.

In order to make the key...
I created a random 64-digit hexadecimal string as your Elliptic Curve
Digital Signature Algorithm (ECDSA) private key, using randomness.
Add “80” to the front of this string. Let’s call this the 80… string
Compute the SHA-256 of the 80… string, and then compute the
SHA-256 hash of that result. Let’s call this the double hash result.
Take the first 8 digits (4 bytes) of the double hash result and stick
it on to the END of the 80… string.
Then I computed the Base58 encoding of that concatenation. The result is my wallet private key. 
I need to generate your public key address from my private key.
I used a third party website to genearte my public key https://walletgenerator.net/?currency=Vertcoin

*/

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
	static String sha256(String input) throws NoSuchAlgorithmException {
		byte[] in = hexStringToByteArray(input);
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		byte[] result = mDigest.digest(in);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
	
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)+ Character.digit(s.charAt(i+1), 16));
			}
		return data;
		}
	public static void main(String[] args) {
		String secret = "5JvVGV1AkvZdeQ4HCmBXK59sZaxrVgohPGGuSDYzsMJhNv47Ypx";
		try{
			System.out.println(sha256(secret));
			}catch (NoSuchAlgorithmException e){}
		}
}

/*
To be able to use my wallet easily and send Vertcoin to other
people, I downloaded the Electrum Vertcoin wallet: https://electrum.vertcoin.org/
I doownloaded Vertcoin one-click miner in order to mine vertcoin https://github.com/vertcoinproject/One-Click-Miner/releases
*/
