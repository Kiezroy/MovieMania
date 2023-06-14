//Name: Kyle Nguyen
//Course: CSC 20-01, Professor Matthew W. Phillips

package Main;

public class Movie {
	// Fields
	private String title;
	private String actor1;
	private String actor2;
	private String director;
	private int year;
	private int runtimeMinutes;
	
	// Constructor
	public Movie(String title, String actor1, String actor2, String director, int year, int runtimeMinutes){
		this.title = title;
		this.actor1 = actor1;
		this.actor2 = actor2;
		this.director = director;
		this.year = year;
		this.runtimeMinutes = runtimeMinutes;
	}
	
	// Methods
	public String getTitle(){
		return this.title;
	}
	
	public String getActor1(){
		return this.actor1;
	}
	
	public String getActor2(){
		return this.actor2;
	}
	
	public String getDirector(){
		return this.director;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public int getRuntime(){
		return this.runtimeMinutes;
	}
	
	// Optional
	public boolean isActorInMovie(String actor){
		return actor.equals(this.actor1.toLowerCase()) || actor.equals(this.actor2.toLowerCase());
	}
}
