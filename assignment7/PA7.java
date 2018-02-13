package assignment7;

import java.io.*;
import java.util.InputMismatchException;
/**
 * Class PA7 Reads and writes in three different case situations. It houses the driver where it checks for correct arguments entered in
 * command prompt and runs two different cases to either encode or decode and file of text/binary.
 * 
 * @author Navid Galt
 * INFS 519
 * Spring 2017
 */
class PA7
{
	/**
     * Main method of PA7 that calls all components of reading in/writing out and also instances of Huffman to encode and decode 
     * files specified by user. Checks for all instances of incorrect input from user.
     *
     * There are two options, encrypt(-e) and decrypt(-d)
     * @param args
     */
	public static void main(String[] args)
	{
       if (args.length == 4)
        {
            if(args[0].charAt(0) != '-')
            {
                System.out.println("Put a dash.");  
            }
            else if(args[0].charAt(1) == 'e')
            {
                System.out.println("Encoding selected.");
            }
            else if(args[0].charAt(1) == 'd')
            {
                System.out.println("Decoding selected.");
            }
            else
            {
                System.out.println("Bad input argument.");
            }                              
        } 
        else
        {
            System.out.println("Usage: PA10 -<e/d> <fileToEncode/fileToDecode> <outputFile> <objectFile>");
            System.exit(0);
        }
       /**Variable used to house contents of file as a String*/
       String fileAsString;
       /** Instance of class Huffman*/
       Huffman h;
		try
		{
			switch(args[0])
			{
				case "-d":
					h = getEncodedObject(args[3]);
					fileAsString = getFileBinaryContents(args[1]);
					String decodedMessage = h.decode(fileAsString);
					writeDecodedMessage(decodedMessage, args[2]);
					break;
				case "-e":
					fileAsString = getFileContents(args[1]);
					h = new Huffman(fileAsString);
					String encoding = h.encode();
					writeEncodedObject(h, args[3]);
					writeEncodedMessage(encoding, args[2]);
					break;
			}
		}
		catch(IOException e)
		{
			System.out.println("Problem reading or writing to specified file");
			System.out.println(e.toString());
		}
	}
	/**
	 * Method getEncodedObject gets encoded objects (an instance of Huffman) and reads it from file
	 * @param filename name of file to read in
	 * @return newHuffy instance of class Huffman
	 * @throws IOException if class is not found
	 */
	static Huffman getEncodedObject(String filename) throws IOException
	{
		Huffman newHuffy = null;
		try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename)))
		{
			newHuffy = (Huffman)input.readObject();
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage() + "File not found please re-enter file");
			System.exit(0);
		}
		return newHuffy;
	}
	/**
	 * Method writeEncodedObject will take class Huffman create a new instance of that class and write it out to a file
	 * @param h Huffman encoding
	 * @param filename name of file user wants to use
	 * @throws IOException if input/object is not correct
	 */
	static void writeEncodedObject(Huffman h, String filename) throws IOException
	{
		try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename)))
		{
			output.writeObject(h);
			output.close();
		}
	}
	/**
	 * Method getFileContents reads in file and continues to do so as long as file has another line to read in
	 * 
	 * @param filename name of the file to read in
	 */
	static String getFileContents(String filename) throws IOException
	{
		StringBuffer fileContents = new StringBuffer();
		
		try(BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
            /**Variable for characters within files*/
            int value = 0;

            while((value = br.read()) != -1)
            {
            	/**Variable that converts integer to character*/
            	char c = (char)value;
            	fileContents.append(c);                
            }
         br.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage() + "File not found please re-enter file");
			System.exit(0);
		}
		catch (IOException ioex)
		{
			System.out.println(ioex.getMessage() + "Error Reading File");
		}
		return fileContents.toString();
	}
	/**
	 * Method getFileBinaryContents reads in file that contains bits represented as a String
	 * @param filename name of file to read in
	 * @return fileContents the contents within the file
	 * @throws IOException if the contents are not integers
	 * @throws FileNotFoundException if file is not found
	 */
	static String getFileBinaryContents(String filename) throws IOException
	{
		StringBuffer fileContents = new StringBuffer();
		
		try(BitInputStream bs = new BitInputStream(new FileInputStream(filename), true))
		{
			while(bs.hasNextBit())
			{
				fileContents.append(bs.readBit());
			}
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("File not found please re-enter file");
			System.exit(0);
		}
		catch (InputMismatchException imex)
		{
			System.out.println(imex.getMessage() + "Error Reading File");
		}return fileContents.toString();
	}
	 /** Method writeDecodedMessage prints out the decoded text from the file to a text file of the users choice
	  * 
	  * @param message the contents of what is being written to a file
	  * @param filename the name of the file that the message is being written to
	  * @throws IOException
	  * */
	static void writeDecodedMessage(String message, String filename) throws IOException
	{
		try(BufferedWriter br = new BufferedWriter(new FileWriter(filename)))
		{
			br.write( message );
			br.close();
		}
	}
	 /** Method writeEncodedMessage prints out the encoded text in binary to a text file of the users choice
	  * 
	  * @param message the contents of what is being written to a file
	  * @param filename the name of the file that the message is being written to
	  * @throws IOException
	  * */
	static void writeEncodedMessage(String message, String filename) throws IOException
	{
		try(BitOutputStream bs = new BitOutputStream(new FileOutputStream(filename), true))
		{
			bs.writeBits( message );
			bs.close();
		}
	}
}
