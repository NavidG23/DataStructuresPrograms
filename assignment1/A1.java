package assignment1;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
* <h1>HonorThyCode</h1>
* The honor code class <b>currently</b> allows for the user to access a menu with different sections of the honor code,
* the corresponding numbers (1-4) will give the user the option to access different sections of the honor code. If the user
* enters 5 the program will output a .txt file named "output.txt" that contains the honor code. 6 will allow the user to exit
* from this program. After each selection the program will prompt the user with a menu that allows them to continue or to 
* exit the program as well.
*
* @author  Navid Galt
* @version 1.0
* @since   2017-1-31
*/

public class A1 
{
	//private String args;
	public static boolean byebye = true;
	public static String threepiecechicken;

	/**
	 * Method readInfo creates menu for users to select from with numbers for corresponding options.
	 * @see void
	 */
	
	public void readInfo()
	{
		boolean flag = true;
		while(flag)
		{
			System.out.println("Source: 'CS Honor Code Policies', 31 Jan. 2017, http://cs.gmu.edu/resources/honor-code/");
			//Scanner scan1;
			
			System.out.println("Please Select from the following menu of honor code options");
			System.out.println("----------------------------------------------------------------------------------------------------\n");
			System.out.println("Enter 1 to view contents 'You (or your group, if a group assignment) may:'");
			System.out.println("Enter 2 to view contents 'You (or your group, if a group assignment) may not seek assistance \n"
								+ "from anyone else, other than your instructor or teaching assistant:'");
			System.out.println("Enter 3 to view contents 'Unless permission to do so is granted by the instructor, you \n"
								+ "(or your group, if a group assignment) may not'");
			System.out.println("Enter 4 to view contents 'You must:'");
			System.out.println("Enter 5 to print the honor code to txt file you designated");
			System.out.println("Enter 6 to Get right outta town!");
			System.out.println("----------------------------------------------------------------------------------------------------");
			
			Scanner scan = new Scanner(System.in);
	        int x = scan.nextInt();
 
			if(x==1)
			{
				flag=false;
				System.out.println("You (or your group, if a group assignment) may:\n"
						+ "seek assistance in learning to use the computing facilities;\n"
						+ "seek assistance in learning to use special features of a programming language's implementation;\n"
						+ "seek assistance in determining the syntactic correctness of a particular programming language \n"
						+ "statement or construct;\n"
						+ "seek an explanation of a particular syntactic error;\n"
						+ "seek explanations of compilation or run-time error messages\n"
						+ "----------------------------------------------------------------------------------------------------");
			}
			
			else if(x==2)
			{
				flag=false;
				System.out.println("You (or your group, if a group assignment) may not seek assistance from anyone else, other \n"
						+ "than your instructor or teaching assistant:\n"
						+ "in designing the data structures used in your solution to a problem;\n"
						+ "in designing the algorithm to solve a problem;\n"
						+ "in modifying the design of an algorithm determined to be faulty;\n"
						+ "in implementing your algorithm in a programming language;\n"
						+ "in correcting a faulty implementation of your algorithm\n"
						+ "in determining the semantic correctness of your algorithm.\n"
						+ "----------------------------------------------------------------------------------------------------");
			}
			
			else if(x==3)
			{
				flag=false;
				System.out.println("Unless permission to do so is granted by the instructor, you (or your group, if \n"
						+ "a group assignment) may not\n"
						+ "give a copy of your work in any form to another student;\n"
						+ "receive a copy of someone else's work in any form;\n"
						+ "attempt to gain access to any files other than your own or those authorized by the instructor \n"
						+ "or computer center;\n"
						+ "inspect or retain in your possession another student's work, whether it was given to you by \n"
						+ "another student, it was found after other student has discarded his/her work, or it accidently \n"
						+ "came into your possession;\n"
						+ "in any way collaborate with someone else in the design or implementation or logical revision \n"
						+ "of an algorithm;\n"
						+ "present as your own, any algorithmic procedure which is not of your own or of the instructor's \n"
						+ "design, or which is not part of the course's required reading (if you modify any procedure which \n"
						+ "is presented in the course's texts but which is not specifically mentioned in class or covered in \n"
						+ "reading assignments, then a citation with page number must be given);\n"
						+ "incorporate code written by others (such as can be found on the Internet);\n"
						+ "----------------------------------------------------------------------------------------------------");
			}
			
			else if(x==4)
			{
				flag=false;
				System.out.println("You must: report any violations of II and III that you become aware of;\n "
						+ "if part of a group assignment, be an equal 'partner' in your group's activities and productions, \n"
						+ "and represent accurately the level of your participation in your group's activities and productions.\n"
						+ "----------------------------------------------------------------------------------------------------");
			}
			
			else if(x==5)
			{
				flag=false;
				WriteToFile(addHonorCode(), threepiecechicken);
				System.out.println("Copied honor code to a file\n"
				+ "----------------------------------------------------------------------------------------------------");
			}
			
			else if(x==6)
			{
				flag=false;
				System.out.println("See ya later alligator!");
				System.out.println("       >*^,^,^~~~");
				System.exit(0);
			}
			
			else
			{
				flag=false;
				System.out.println("This is an invalid selection, please enter 1-6 only");
			}
				
			//Run the user through the prompt again
			if(mainMenu())
	        {    
	           readInfo();                  
	        }
	        else
	        {
	           return;
	        }
			scan.close();
		}
	}
	/**
	 * Method mainMenu administers a loop that returns the user to main menu after each selection.
	 * @see boolean
	 */
	public static boolean mainMenu()
	{         
		// get user decision
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Would you like to choose from the options again?");
		System.out.println("Please enter Yes or No");
		String response = scan1.nextLine();
		if(response.compareTo("Yes") == 0)
		{
			byebye=true;
		}
		else if(response.compareTo("No") == 0)
		{
			byebye=false;
			System.out.println("----------------------------------------------------------------------------------------------------");
			System.out.println("¡Adios amigos!");
		}
		else
		{
			System.out.println("Not an acceptible answer, Please type Yes or No (case-sensitive)");
			mainMenu();
		}
	return byebye;	
	}
	
/**
* Method addHonorCode uses WriteToFile object of java.io library to export honor code to a .txt file called "output.txt".
* @see String
* @return The Honor Code
* 
*/
	public String addHonorCode()
	{
		return "All CS students must adhere to the GMU Honor Code. In addition to this honor code,\r\n"
				+ "the computer science department has further honor code policies regarding programming \r\n"
				+ "projects, which is detailed below. Your instructor may state further policies for his \r\n"
				+ "or her class as well.\r\n"
				+ "\r\n"
				+ "Unless otherwise stated, at the time that an assignment or project is given, all work handed \r\n"
				+ "in for credit is to be the result of individual effort. (In some classes group work is \r\n"
				+ "encouraged; if so, that will be made explicit when the assignment is given.) \r\n"
				+ "The CS department has a Statement on Academic Integrity.\r\n"
				+ "\r\n\r\n"
				+ "You (or your group, if a group assignment) may:\r\n"
				+ "seek assistance in learning to use the computing facilities;\r\n"
				+ "seek assistance in learning to use special features of a programming language's implementation;\r\n"
				+ "seek assistance in determining the syntactic correctness of a particular programming language \r\n"
				+ "statement or construct;\r\n"
				+ "seek an explanation of a particular syntactic error;\r\n"
				+ "seek explanations of compilation or run-time error messages\r\n"
				+ "\r\n\r\n"
				+ "You (or your group, if a group assignment) may not seek assistance from anyone else, other \r\n"
				+ "than your instructor or teaching assistant:\r\n"
				+ "in designing the data structures used in your solution to a problem;\r\n"
				+ "in designing the algorithm to solve a problem;\r\n"
				+ "in modifying the design of an algorithm determined to be faulty;\r\n"
				+ "in implementing your algorithm in a programming language;\r\n"
				+ "in correcting a faulty implementation of your algorithm\r\n"
				+ "in determining the semantic correctness of your algorithm.\r\n"
				+"\r\n\r\n"
				+"Unless permission to do so is granted by the instructor, you (or your group, if \r\n"
				+ "a group assignment) may not\r\n"
				+ "give a copy of your work in any form to another student;\r\n"
				+ "receive a copy of someone else's work in any form;\r\n"
				+ "attempt to gain access to any files other than your own or those authorized by the instructor \r\n"
				+ "or computer center;\r\n"
				+ "inspect or retain in your possession another student's work, whether it was given to you by \r\n"
				+ "another student, it was found after other student has discarded his/her work, or it accidently \r\n"
				+ "came into your possession;\r\n"
				+ "in any way collaborate with someone else in the design or implementation or logical revision \r\n"
				+ "of an algorithm;\r\n"
				+ "present as your own, any algorithmic procedure which is not of your own or of the instructor's \r\n"
				+ "design, or which is not part of the course's required reading (if you modify any procedure which \r\n"
				+ "is presented in the course's texts but which is not specifically mentioned in class or covered in \r\n"
				+ "reading assignments, then a citation with page number must be given);\r\n"
				+ "incorporate code written by others (such as can be found on the Internet);\r\n"
				+"\r\n\r\n"
				+ "You must: report any violations of II and III that you become aware of;\n "
				+ "if part of a group assignment, be an equal 'partner' in your group's activities and productions, \r\n"
				+ "and represent accurately the level of your participation in your group's activities and productions.\r\n"
				+"\r\n\r\n"
				+ "Source: 'CS Honor Code Policies', 31 Jan. 2017, http://cs.gmu.edu/resources/honor-code/";
	}
/**
 * Method used to print Honor Code to File 
 * @param text
 * @param filename
 * @param FileNotFoundException e
 * @see void
 */
	public void WriteToFile(String text, String filename)
	{
		try(PrintWriter out = new PrintWriter( filename ))
		{
			out.println( text );
			out.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found" + e.toString());
		}
	}
	
	/**
     * The main method for the HonorThyCode class. Outputs a text welcoming the user and printing out the excerpt
     * from the gmu cs honor code. Then uses the arg command to write honor code to users designated file.
     *
     * @param args  used
     */
	public static void main(String[] args) 
	{
		threepiecechicken = args[0];
	
		System.out.println("\n\n");
		System.out.println("Welcome to the Honor Code!! \n");
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("All CS students must adhere to the GMU Honor Code. In addition to this honor code,\r\n"
				+ "the computer science department has further honor code policies regarding programming \r\n"
				+ "projects, which is detailed below. Your instructor may state further policies for his \r\n"
				+ "or her class as well."
				+ "\r\n\r\n"
				+ "Unless otherwise stated, at the time that an assignment or project is given, all work handed \r\n"
				+ "in for credit is to be the result of individual effort. (In some classes group work is \r\n"
				+ "encouraged; if so, that will be made explicit when the assignment is given.) \r\n"
				+ "The CS department has a Statement on Academic Integrity.\r\n"
				+ "----------------------------------------------------------------------------------------------------");
		A1 georgeMason = new A1();
		georgeMason.readInfo();
		
	}
}
	
 

