import file.MovieDB;
import movies.Actor;
import movies.Movie;

import java.util.ArrayList;

/**
 * Movie trivia class providing different methods for querying and updating a movie database.
 */
public class MovieTrivia {
	
	/**
	 * Create instance of movie database
	 */
	MovieDB movieDB = new MovieDB();
	
	
	public static void main(String[] args) {
		
		//create instance of movie trivia class
		MovieTrivia mt = new MovieTrivia();
		
		//setup movie trivia class
		mt.setUp("moviedata.txt", "movieratings.csv");
	}
	
	/**
	 * Sets up the Movie Trivia class
	 * @param movieData .txt file
	 * @param movieRatings .csv file
	 */
	public void setUp(String movieData, String movieRatings) {
		//load movie database files
		movieDB.setUp(movieData, movieRatings);
		
		//print all actors and movies
		this.printAllActors();
		this.printAllMovies();		
	}
	
	/**
	 * Prints a list of all actors and the movies they acted in.
	 */
	public void printAllActors () {
		System.out.println(movieDB.getActorsInfo());
	}
	
	/**
	 * Prints a list of all movies and their ratings.
	 */
	public void printAllMovies () {
		System.out.println(movieDB.getMoviesInfo());
	}
	
	
	// TODO add additional methods as specified in the instructions PDF
	/**
	 * The actorsInfo is the ArrayList that stores the information of actor.
	 * actor is the actor name as a string.
	 * movies is a String array of movie names that the actor has acted in
	 */
	public void insertActor (String actor, String[] movies, ArrayList<Actor> actorsInfo){
		// the actor has exist in the list
		for (Actor oneInActor : actorsInfo){
			if (oneInActor.getName().equals(actor.trim().toLowerCase())){
				// update the movies information of this actor
				updateForInsertActor (movies, oneInActor);
				return;
			}
		}
		//the actor does not exist in the list
		insertForInsertActor (actor, movies, actorsInfo);
		return;
	}

	/**
	 * if the actor has been in the list, then update their movies
	 * @param movies the movies need to be checked
	 * @param actor one actor
	 */

	private void updateForInsertActor (String[] movies, Actor actor) {
		for (int i = 0; i < movies.length; i++) {
			ArrayList<String> moviesCasted = actor.getMoviesCast();
			if (moviesCasted.contains(movies[i].trim().toLowerCase())) {
				continue;
			} else {
				moviesCasted.add(movies[i].trim().toLowerCase());
			}
		}
		return;
	}

	/**
	 * if the actor doesn't exist in the list, then insert a new one
	 * @param actor to be inserted
	 * @param movies
	 * @param actorsInfo
	 */
	private void insertForInsertActor (String actor, String[] movies, ArrayList<Actor> actorsInfo) {
		Actor newActor = new Actor (actor.trim().toLowerCase());
		for (int i = 0; i < movies.length; i++) {
			newActor.getMoviesCast().add(movies[i].trim().toLowerCase());
		}
		actorsInfo.add(newActor);
		return;
	}

	/**
	 * Inserts given ratings for given movie into database.
	 * Update the ratings for a movie if the movie is already in the database
	 * @param movie
	 * @param ratings
	 * @param moviesInfo
	 */
	public void insertRating (String movie, int [] ratings, ArrayList <Movie> moviesInfo) {
		if (ratings.length != 2) return;
		for (int rating : ratings) {
			if (rating < 0 || rating > 100) return;
		}
		Movie newMovie = new Movie(movie.trim().toLowerCase(), ratings[0], ratings[1]);
		moviesInfo.add(newMovie);
	}
}
