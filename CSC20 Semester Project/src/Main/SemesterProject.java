//Name: Kyle Nguyen
//Course: CSC 20-01, Professor Matthew W. Phillips

package Main;

import java.util.StringTokenizer;

public class SemesterProject {

	public static Database data = new Database("db.txt");

	public static void main(String[] args) {	
		System.out.println("----------------Welcome to MovieMania!----------------\n");
	    printMenu();
		
		keyboardInput input = new keyboardInput();
		String command = input.getKeyboardLine();
		
		
		while(true){
			if(command.equals("h")){
				//Save and write all of data from Database obj into file before termination
				
				fileWrite writer = new fileWrite("db.txt");
				for(Movie movs: data.getMovie()){
					writer.writeLine(movs.getTitle() + ";" + movs.getActor1() + ";"
									+ movs.getActor2() + ";" + movs.getDirector() + ";"
									+ movs.getYear() + ";" + movs.getRuntime());
				}
				
				//NOW MUST CALL writer.saveFile()
				writer.saveFile();
				//Close the open input and break out of while loop to terminate
				input.closeKeyboard();
				break;
			}
			//Decides what to do based on user input of letter
			switch(command){
				case "a":
					newEntry();
					break;
					
				case "b":
					searchByActor();
					break;
					
				case "c":
					searchByYear();
					break;
					
				case "d":
					searchByRuntime();
					break;
					
				case "e":
					searchByDirector();
					break;
					
				case "f":
					searchByTitle();
					break;
					
				case "g":
					deleteEntry();
					break;
			
				default:
					System.out.println("Unknown command!");
					break;
			}
			
			System.out.println();
			printMenu();
			command = input.getKeyboardLine();
		}
	}
	
	public static void printMenu(){
		System.out.print("Enter command by letter >\n ");
		
		System.out.println(
			"a.) new entry\n " +

            "b.) search by actor\n " +

            "c.) search by year\n " +

            "d.) search by runtime (in minutes)\n " +

            "e.) search by director\n " +

            "f.) search by title\n " +
            
            "g.) delete entry\n " +

            "h.) quit\n ");
	}
	
	public static void newEntry(){
		keyboardInput input = new keyboardInput();
		
		//Must use stringTokenizer and in one String line
		String entry = "";
		
		System.out.println("Enter title >");
		String title = input.getKeyboardLine();
		entry += title + ";";
		
		//Makes sure entries are valid
		if(title.length() < 3){
			System.out.println("Invalid entry (minimum length of valid title is three characters)");
		}else{
			//Asks user to enter appropriate data and concatenates to one string
			System.out.println("Enter actor 1 >");
			String act1 = input.getKeyboardLine();
			//Test if user enters a number for actor 1 instead of string or if blank
			entry += userTestingString(act1);
			
			System.out.println("Enter actor 2 >");
			String act2 = input.getKeyboardLine();
			entry += userTestingString(act2);
			
			System.out.println("Enter Director >");
			String director = input.getKeyboardLine();
			entry += userTestingString(director);
			
			System.out.println("Enter year >");
			String year = input.getKeyboardLine();
			entry += userTestingInt(year);
			
			System.out.println("Enter runtime (minutes) >");
			String runtime = input.getKeyboardLine();
			entry += userTestingInt(runtime);
			
			//Single string broken into tokens and sorted into database
			StringTokenizer st = new StringTokenizer(entry, ";");
			
			data.addEntry(new Movie(st.nextToken(),st.nextToken(),st.nextToken(),
					st.nextToken(),Integer.valueOf(st.nextToken()),
					Integer.valueOf(st.nextToken())));
		}
	}
	
	public static void searchByActor(){
		keyboardInput input = new keyboardInput();
		System.out.println("Enter actor >");
		String actor = input.getKeyboardLine();
		
		//If user enters blank it'll keep asking until they enter a valid actor
		if(actor.length() != 0 && !testIfNumber(actor)){
			data.searchByActor(actor);
		}else{
			System.out.println("Please enter an actor!\n");
			searchByActor();
		}
	}
	
	public static void searchByYear(){
		keyboardInput input = new keyboardInput();
		System.out.println("Enter year >");
		String year = input.getKeyboardLine();
		
		//If user enters blank it'll keep asking until they enter a valid year
		//Also checks if user enters a string instead of year
		if(year.length() != 0 && testIfNumber(year)){
			data.searchByYear(Integer.valueOf(year));
		}else{
			System.out.println("Please enter a year!\n");
			searchByYear();
		}
	}
	
	public static void searchByRuntime(){
		keyboardInput input = new keyboardInput();
		System.out.println("Enter runtime (minutes) >");
		String runtime = input.getKeyboardLine();
		
		//If user enters blank it'll keep asking until they enter a valid runtime
		if(runtime.length() != 0 && testIfNumber(runtime)){
			data.searchByRuntime(Integer.valueOf(runtime));
		}else{
			System.out.println("Please enter a runtime!\n");
			searchByRuntime();
		}
	}
	
	public static void searchByDirector(){
		keyboardInput input = new keyboardInput();
		System.out.println("Enter director >");
		String director = input.getKeyboardLine();
		
		//If user enters blank it'll keep asking until they enter a valid director
		if(director.length() != 0 && !testIfNumber(director)){
			data.searchByDirector(director);
		}else{
			System.out.println("Please enter a director!\n");
			searchByDirector();
		}
	}
	
	public static void searchByTitle(){
		keyboardInput input = new keyboardInput();
		System.out.println("Enter title >");
		String title = input.getKeyboardLine();
		
		//If user enters blank it'll keep asking until they enter a valid title
		if(title.length() != 0 && !testIfNumber(title)){
			data.searchByTitle(title);
		}else{
			System.out.println("Please enter a title!\n");
			searchByTitle();
		}
	}
	
	public static void deleteEntry(){
		keyboardInput input = new keyboardInput();
		System.out.println("Enter title to delete >");
		String title = input.getKeyboardLine();
		
		//Check in database to see if exists & delete if true
		data.deleteEntry(title);
	}
	
	//Test if user enters a number instead of string or if blank
	public static String userTestingString(String var){
		if(testIfNumber(var) || var.isEmpty()){
			//If a number is entered, tries again
			System.out.println("Entered number or was blank!!! Leaving as \"N/A\"");
			var = "N/A;";
		}else{
			var += ";";
		}
		return var;
	}
	
	//Tests if user enters string instead of integer of blank
	public static String userTestingInt(String var){
		if(!testIfNumber(var) || var.isEmpty()){
			System.out.println("Entered string or was blank!!! Leaving as \"0\"");
			var = "0;";
		}else{
			var += ";";
		}
		return var;
	}
	
	//Checks if user enters a string instead of year, vice versa
	public static boolean testIfNumber(String str){
		try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}
	
}
