package Main;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Database {
	// Fields
	private ArrayList<Movie> movies;
	
	// Constructor
	public Database(String filename){
		movies = new ArrayList<>();
		//Uses fileRead to load in data from db.txt to the database
		fileRead fr = new fileRead(filename);
		for(int i = 0; i < fr.getNumberOfLines(); i++){
			String raw = fr.getLine(i);
			// TODO: Parse using the StringTokenizer here and place into movies as single entries...
			//Adds anything in db.txt into the database
			StringTokenizer st = new StringTokenizer(raw, ";");
			String title = st.nextToken();
			String act1 = st.nextToken();
			String act2 = st.nextToken();
			String director = st.nextToken();
			int year = Integer.valueOf(st.nextToken());
			int runTime = Integer.valueOf(st.nextToken());
			movies.add(new Movie(title, act1, act2, director, year, runTime));
		}
	}
	
	// Methods (search entire database)
	public void addEntry(Movie newEntry){
		movies.add(newEntry);
	}
	
	public void deleteEntry(String title){
		int found = 0;
		for (int i = 0; i < movies.size(); i++){
			if(movies.get(i).getTitle().toLowerCase().equals(title.toLowerCase())){
				movies.remove(movies.get(i));
				found++;
			}
		}
		if(found == 0)
			System.out.println("Title not found");
		else
			System.out.println("DELETED!!!");
	}
	
	public void searchByTitle(String title){
		int found = 0;
		for(Movie m: movies){
			if(m.getTitle().toLowerCase().equals(title.toLowerCase())){
				System.out.println("Actors: " + m.getActor1() + ", " + m.getActor2());
				System.out.println("Director: " + m.getDirector());
				System.out.println("Year: " + m.getYear());
				System.out.println("Runtime: " + m.getRuntime() + " minutes\n");
				found++;
			}
		}
		if(found == 0)
			System.out.println("No titles found for title");
	}
	
	public void searchByActor(String actor){
		int found = 0;
		for(Movie m: movies){
			if(m.isActorInMovie(actor.toLowerCase())){
				System.out.println(m.getTitle());
				found++;
			}
		}
		if(found == 0)
			System.out.println("No titles found for actor");
	}
	
	public void searchByDirector(String director){
		int found = 0;
		for(Movie m: movies){
			if(m.getDirector().toLowerCase().equals(director.toLowerCase())){
				System.out.println(m.getTitle());
				found++;
			}
		}
		if(found == 0)
			System.out.println("No titles found for director");
	}
	
	public void searchByYear(int year){
		int found = 0;
		for(Movie m: movies){
			if(m.getYear() == year){
				System.out.println(m.getTitle());
				found++;
			}
		}
		if(found == 0)
			System.out.println("No titles found for year");
	}
	
	public void searchByRuntime(int runtime){
		int found = 0;
		for(Movie m: movies){
			if(m.getRuntime() == runtime){
				System.out.println(m.getTitle());
				found++;
			}
		}
		if(found == 0)
			System.out.println("No titles found for runtime");
	}
	
	public ArrayList<Movie> getMovie(){
		return this.movies;
	}
}
