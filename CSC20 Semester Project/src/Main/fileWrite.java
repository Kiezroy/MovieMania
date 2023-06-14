/* READ THIS!: The idea behind this class is that we save the "writeBuffer" in memory but do not actually write the file to disk until someone calls the 
 * "saveFile" method. The reason for this is for performance and to prevent keeping an open file pointer (which is poor form and risky) */

package Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class fileWrite {
	// Fields
	private ArrayList<String> writeBuffer;
	private String filename;
	
	// Constructor
	public fileWrite(String filename){
		this.filename = filename;			// Save filename for later
		writeBuffer = new ArrayList<>();
	}
	
	//Methods
	public void writeLine(String newLine){
		// TODO: Add the newLine to the writeBuffer...
		writeBuffer.add(newLine);
	}
	
	public void saveFile(){
		// TODO: Save all of the lines in the writeBuffer to the file (given in filename)
		//will write all of the lines from writeBuffer into db.txt
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename));
			for(String str: this.writeBuffer){
				writer.write(str + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}	
	}
}
