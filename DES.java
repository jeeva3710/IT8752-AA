import java.io.*;  
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
public class DES   
{  
	private static Cipher encrypt;  
	private static Cipher decrypt;  
	private static final byte[] initialization_vector = { 22, 33, 11, 112, 55, 99, 66, 77 };  
	public static void main(String[] args)   
	{ 
		String textFile = "src/demo.txt";  
		String eData = "encrypteddata.txt";  
		String dData = "decrypteddata.txt";  
		try   
		{  
			SecretKey scrtkey = KeyGenerator.getInstance("DES").generateKey();  
			AlgorithmParameterSpec aps = new IvParameterSpec(initialization_vector);  
			encrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");  
			encrypt.init(Cipher.ENCRYPT_MODE, scrtkey, aps);  
			decrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");  
			decrypt.init(Cipher.DECRYPT_MODE, scrtkey, aps);  
			encryption(new FileInputStream(textFile), new FileOutputStream(eData));  
			decryption(new FileInputStream(eData), new FileOutputStream(dData));  
			System.out.println("The encrypted and decrypted files created successfully.");  
		}   
		catch (Exception e)   
		{  
			e.printStackTrace();  
		}  
	}  
	private static void encryption(InputStream input, OutputStream output) throws IOException   
	{  
		output = new CipherOutputStream(output, encrypt);  
		writeBytes(input, output);  
	}   
	private static void decryption(InputStream input, OutputStream output) throws IOException   
	{  
		input = new CipherInputStream(input, decrypt);  
		writeBytes(input, output);  
	}  
	private static void writeBytes(InputStream input, OutputStream output) throws IOException   
	{  
		byte[] writeBuffer = new byte[512];  
		int readBytes = 0;  
		while ((readBytes = input.read(writeBuffer)) >= 0)   
		{  
			output.write(writeBuffer, 0, readBytes);  
		}  
		output.close();  
		input.close();  
	}   
}  
