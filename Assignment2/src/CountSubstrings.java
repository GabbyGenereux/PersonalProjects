import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 * Gabrielle Genereux
 * 100963245
 * Assignment 2 - Count Substrings
 */

public class CountSubstrings 
{
	public static void main(String args[]) throws java.io.IOException
	{
		try
		{   //Asks user for file name, looks for file
			System.out.println("Please enter the file name plus extension: ");
			Scanner input = new Scanner(System.in);
			File f = new File(input.nextLine());
			System.out.println("Please enter the word you want to look for: ");
			Scanner input2 = new Scanner(System.in);
			String key = input2.next();
			input = new Scanner(f);
			
			//starts first counter, passes file to addToArray method, prints count out and stops the timer
			long counter1 = System.currentTimeMillis();
			ArrayList<String> array = addToArray(input);
			int count = handleArray(array, key);
			System.out.println("Arraylist: that word occurs "+count+" times");
			long arrayTime = System.currentTimeMillis() - counter1;
			System.out.println("The time it took to handle the array was: "+arrayTime+"milliseconds");
			
			//resets the input so it's at the start of the file
			input = new Scanner(f);
			long counter2 = System.currentTimeMillis();
			LinkedList<String> list = addToList(input);
			int count2 = handleArray(list, key);
			System.out.println("Linkedlist: that word occurs "+count2+" times");
			long listTime = System.currentTimeMillis() - counter2;
			System.out.println("The time it took to handle the list was: "+listTime+"milliseconds");
			input.close();
		}
		catch(Exception ex) //if the file name is invalid it throws an exception
		{
			System.out.println("File not found");
			String array[] = {"0"};
			main(array);
		}
	}
	//adds each line of strings to an arrayList as an element
	//intakes the scanner input file
	//returns the arrayList
	public static ArrayList<String> addToArray(Scanner i)
	{
		ArrayList<String> arr = new ArrayList<String>();
		while(i.hasNextLine()) //uses scanner method hadNextLine which returns a boolean to determine whether the file has a next line
		{
			arr.add(i.nextLine()); //adds line to arraylist
		}	
		return arr;
	}
	//counts the occurrences of the specified key in each element of the list
	//intakes the list and the key to look for
	public static int handleArray(List<String> a, String key)
	{
		String replacement = "wxzy"; //replacement key to avoid counting the same key twice
		int wordCount = 0;
		for(String temp: a)
		{
			while(temp.contains(key))
			{
				wordCount++;
				temp = temp.replaceFirst(key, replacement); //replaces the key with the replacement word
			}
		}
		return wordCount;
	}
	
	//adds each line of the file into a LinkedList
	//takes in the file as input
	//returns the LinkedList
	public static LinkedList<String> addToList(Scanner i)
	{
		LinkedList<String> list = new LinkedList<String>();
		while(i.hasNextLine())
		{
			list.add(i.nextLine()); //adds the line to the linkedlist
		}	
		return list;		
	}
}
