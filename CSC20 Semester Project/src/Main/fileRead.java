package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class fileRead {
	// Fields
	private ArrayList<String> lines;
	
	// Constructor
	public fileRead(String filename){
		lines = new ArrayList<>();
		//TODO: Open the filename, read in the data into the lines arraylist, and close the file when done...
		//You will be adding each line of db.txt as a string into the lines arraylist
		try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String str;
			while((str = reader.readLine()) != null){
				lines.add(str);
			}
			reader.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	// Methods
	public int getNumberOfLines(){
		return lines.size();
	}
	
	public String getLine(int index){
		return lines.get(index);
	}
}
