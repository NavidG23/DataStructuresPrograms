package Project2;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import com.opencsv.*;
/**
 * This class drives the analysis portion of the program
 * 
 * @author Navid Galt
 * INFS 519
 * Spring 2017 
 */
public class Project2
{
	public static void main(String[] args)
	{
		if(args.length != 2) {
			System.out.println("Wrong number of arguments.\nUsage: java Project2 dataset companyToFind");
			return;
		}
		System.out.println(args[0]);
		File dataFile = new File(args[0]);
		String companyToFind = args[1];
		
		long start, end, duration;
		Company searchResult = null;
		
		start = System.currentTimeMillis();
		searchResult = Project2.findLinearSearch(companyToFind, dataFile);
		end = System.currentTimeMillis();
		duration = end - start;
		
		System.out.println("\n*********************\nLinear Search\n*********************\n");
		System.out.println("Search Time: " + duration + "ms");
		if(searchResult != null)
			System.out.println("Complaints: " + String.join(", ", searchResult.getIds()));
		else
			System.out.println("No Complaints found.");
			
		System.out.println("\n*********************\nMy Hash: Separate Chaining\n*********************\n");
		start = System.currentTimeMillis();
		MapSeparateChaining<String,Company> mapOpenHash = Project2.makeMapSeparateChaining(dataFile);
		end = System.currentTimeMillis();
		duration = end - start;
		System.out.println("Map Creation Time: " + duration + "ms");
		
		start = System.currentTimeMillis();
		searchResult = mapOpenHash.get(companyToFind);
		end = System.currentTimeMillis();
		duration = end - start;
		
		System.out.println("Search Time: " + duration + "ms");
		if(searchResult != null)
			System.out.println("Complaints: " + String.join(", ", searchResult.getIds()));
		else
			System.out.println("No Complaints found.");
		
		System.out.println("\n*********************\nMy Hash: Open Addressing\n*********************\n");
		start = System.currentTimeMillis();
		MapOpenAddressing<String,Company> mapOpenAddress = Project2.makeMapOpenAddressing(dataFile);
		end = System.currentTimeMillis();
		duration = end - start;
		System.out.println("Map Creation Time: " + duration + "ms");
		
		start = System.currentTimeMillis();
		searchResult = mapOpenAddress.get(companyToFind);
		end = System.currentTimeMillis();
		duration = end - start;
		
		System.out.println("Search Time: " + duration + "ms");
		if(searchResult != null)
			System.out.println("Complaints: " + String.join(", ", searchResult.getIds()));
		else
			System.out.println("No Complaints found.");
		
		System.out.println("\n*********************\nJava HashMap\n*********************\n");
		start = System.currentTimeMillis();
		HashMap<String,Company> map = Project2.javaHash(dataFile);
		end = System.currentTimeMillis();
		duration = end - start;
		System.out.println("Map Creation Time: " + duration + "ms");
		
		start = System.currentTimeMillis();
		searchResult = map.get(companyToFind);
		end = System.currentTimeMillis();
		duration = end - start;
		
		System.out.println("Search Time: " + duration + "ms");
		if(searchResult != null)
			System.out.println("Complaints: " + String.join(", ", searchResult.getIds()));
		else
			System.out.println("No Complaints found.");
		
		System.out.println("\n*********************\n");
	}
	/**
	 * Method findLinearSearch takes in a CSV file, does a linear search on contents and returns the company's complaint ids searched for.
	 * 
	 * @param needle the company being searched for
	 * @param dataFile CSV file being read in
	 * @return companyHolder returns the company asked for with complaint ids
	 */
	public static Company findLinearSearch(String needle, File dataFile)
	{
		//Put company in if we find, storing company in
		Company companyHolder = null;
		try
		{
			CSVReader reader = new CSVReader(new FileReader(dataFile));
		    String [] nextLine;
		    while ((nextLine = reader.readNext()) != null) 
		    {
					String currentCompany = nextLine[7];
					String complaintId = nextLine[17];
					if(!(currentCompany.equalsIgnoreCase(needle)))
					{
						continue;
					}
					if(companyHolder == null)
		    		{
						companyHolder = new Company(currentCompany, complaintId);
		    			continue;
		    		}
					companyHolder.addId(complaintId);		
				} return companyHolder;
		    }
			catch(FileNotFoundException e)
			{
				System.out.println("File not found, please enter correct file");
				System.exit(0);
			}
			catch(Exception e)
			{
				System.out.println("Error");
				System.exit(0);
			}
		return companyHolder;
	}
	/**
	 * Method MapSeparateChaining reads in a CSV file and creates a hash table and 
	 * uses the company searched for to return complaint Ids linked to that company.
	 * 
	 * @param dataFile CSV file being read in
	 * @return sepChain set of complaint Ids linked to company asked for
	 */
	public static MapSeparateChaining<String,Company> makeMapSeparateChaining(File dataFile) 
	{
		try
		{
			MapSeparateChaining<String,Company> sepChain = new MapSeparateChaining<String,Company>(100);
			
			CSVReader reader = new CSVReader(new FileReader(dataFile));
		    String [] nextLine;
		    while ((nextLine = reader.readNext()) != null) 
		    {
				String currentCompany = nextLine[7];
				String complaintId = nextLine[17];
				Company companyHolder = new Company(currentCompany, complaintId);
				Company retrievedCompany = sepChain.get(currentCompany);
				
				if(sepChain.searchKey(currentCompany))
				{
					sepChain.get(currentCompany).addId(complaintId);
				}
				else
				{
					sepChain.put(currentCompany, companyHolder);
				}			
			}
			return sepChain;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found, please enter correct file");
			System.exit(0);
		}
		catch(Exception e)
		{
			System.out.println("Error");
			System.exit(0);
		} return null;
	}
	/**
	 * Method MapOpenAddressing reads in a CSV file and creates a hash table using linear probing. 
	 * Accepts a company being searched and returns complaint Ids linked to that company.
	 * 
	 * @param dataFile CSV file being read in
	 * @return b set of complaint Ids linked to company asked for
	 */
	public static MapOpenAddressing<String,Company> makeMapOpenAddressing(File dataFile) 
	{
		try
		{
			MapOpenAddressing<String,Company> b = new MapOpenAddressing<String,Company>(43);
			
			CSVReader reader = new CSVReader(new FileReader(dataFile));
		    String [] nextLine;
		    while ((nextLine = reader.readNext()) != null) 
		    {
				String currentCompany = nextLine[7];
				String complaintId = nextLine[17];
				Company retrievedCompany = b.get(currentCompany);
				if(retrievedCompany == null)
				{
					Company companyHolder = new Company(currentCompany, complaintId);
					b.put(currentCompany, companyHolder);
				}
				else
				{
					retrievedCompany.addId(complaintId);
				}				
			}
			return b;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found, please enter correct file");
			System.exit(0);
		}
		catch(Exception e)
		{
			System.out.println("Error");
			System.exit(0);
		} return null;
	}
	/**
	 * Method HashMap uses java built in structure to read in CSV file and create table with companies mapped to complaint ids to search through.
	 * 
	 * @param dataFile CSV file being read in
	 * @return hmap set of complaint Ids linked to company asked for
	 */
	public static HashMap<String,Company> javaHash(File dataFile) 
	{
		try
		{
		    HashMap<String, Company> hmap = new HashMap<String, Company>();
		      
			CSVReader reader = new CSVReader(new FileReader(dataFile));
		    String [] nextLine;
		    while ((nextLine = reader.readNext()) != null) 
		    {
		    	String currentCompany = nextLine[7];
				String complaintId = nextLine[17];
				Company retrievedCompany = hmap.get(currentCompany);
				if(retrievedCompany == null)
				{
					Company companyHolder = new Company(currentCompany, complaintId);
					hmap.put(currentCompany, companyHolder);
				}
				else
				{
					retrievedCompany.addId(complaintId);
				}
		    }return hmap;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found, please enter correct file");
			System.exit(0);
		}
		catch(Exception e)
		{
			System.out.println("Error");
			System.exit(0);
		} return null;
	}
}
