import file.MovieDB;
import movies.Actor;
import movies.Movie;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Movie trivia class providing different methods for querying and updating a movie database.
 * @author Rui Tan
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
	 * @param movie is the movie name as a string
	 * @param ratings is an int array with 2 elements
	 * @param moviesInfo is the ArrayList that is to be inserted into/updated
	 */
	public void insertRating (String movie, int [] ratings, ArrayList <Movie> moviesInfo) {
		if (ratings.length != 2) return;
		for (int rating : ratings) {
			if (rating < 0 || rating > 100) return;
		}
		for (Movie movieInfo: moviesInfo) {
			if (movieInfo.getName().equals(movie.trim().toLowerCase())){
				movieInfo.setAudienceRating(ratings[1]);
				movieInfo.setCriticRating(ratings[0]);
				return;
			}
		}
		Movie newMovie = new Movie(movie.trim().toLowerCase(), ratings[0], ratings[1]);
		moviesInfo.add(newMovie);
		return;
	}

	/**
	 * Given an actor, returns the list of all movies
	 * @param actor is the name of an actor as a String
	 * @param actorsInfo is the ArrayList to get the data from
	 * @return
	 */
	public ArrayList <String> selectWhereActorIs (String actor, ArrayList <Actor> actorsInfo) {
		for (Actor actorInfo : actorsInfo) {
			if (actorInfo.getName().equals(actor.trim().toLowerCase())) {
				return actorInfo.getMoviesCast();
			}
		}
		return new ArrayList <String>();
	}

	/**
	 * Given a movie, returns the list of all actors in that movie
	 * @param movie  is the name of a movie as a String
	 * @param actorsInfo is the ArrayList to get the data from
	 * @return the list of all actors in that movie
	 */
	public ArrayList <String> selectWhereMovieIs (String movie, ArrayList <Actor> actorsInfo) {
		ArrayList<String> res = new ArrayList<>();
		for (Actor actorInfo : actorsInfo) {
			if (actorInfo.getMoviesCast().contains(movie.trim().toLowerCase())) {
				res.add(actorInfo.getName());
			}
		}
		return res;
	}

	/**
	 * returns a list of movies that satisfy an inequality or equality
	 * @param comparison either ‘=’, ‘>’, or ‘< ‘
	 * @param targetRating is an integer
	 * @param isCritic true = critic ratings, false = audience ratings.
	 * @param moviesInfo is the ArrayList to get the data from
	 * @return a list of movies that satisfy an inequality or equality
	 */

	public ArrayList<String> selectWhereRatingIs (char comparison, int targetRating, boolean isCritic, ArrayList <Movie> moviesInfo) {

		ArrayList<String> res = new ArrayList<>();
		if (targetRating< 0 || targetRating > 100) return res;
		else if (comparison != '>' && comparison != '=' && comparison != '<') return res;

		if (isCritic == true) {
			res = selectWhereCriticRatingIs(comparison, targetRating, moviesInfo);
		} else {
			res = selectWhereAudienceRatingIs(comparison, targetRating, moviesInfo);
		}
		return res;
	}

	/**
	 * returns a list of movies whose CriticRating satisfy an inequality or equality
	 * @param comparison either ‘=’, ‘>’, or ‘< ‘
	 * @param targetRating is an integer
	 * @param moviesInfo is the ArrayList to get the data from
	 * @return a list of movies whose CriticRating satisfy an inequality or equality
	 */

	private ArrayList<String> selectWhereCriticRatingIs (char comparison, int targetRating, ArrayList <Movie> moviesInfo) {
		ArrayList<String> temp = new ArrayList<>();
		for (Movie movieInfo : moviesInfo) {
			if (comparison == '='){
				if (movieInfo.getCriticRating() == targetRating) temp.add(movieInfo.getName());
			} else if (comparison == '>') {
				if (movieInfo.getCriticRating() > targetRating) temp.add(movieInfo.getName());
		    } else {
				if (movieInfo.getCriticRating() < targetRating) temp.add(movieInfo.getName());
	        }
		}
		return temp;
	}

	/**
	 * returns a list of movies whose AudienceRating satisfy an inequality or equality
	 * @param comparison either ‘=’, ‘>’, or ‘< ‘
	 * @param targetRating is an integer
	 * @param moviesInfo is the ArrayList to get the data from
	 * @return a list of movies whose AudienceRating satisfy an inequality or equality
	 */

	private ArrayList<String> selectWhereAudienceRatingIs (char comparison, int targetRating, ArrayList <Movie> moviesInfo) {
		ArrayList<String> temp = new ArrayList<>();
		for (Movie movieInfo : moviesInfo) {
			if (comparison == '='){
				if (movieInfo.getAudienceRating() == targetRating) temp.add(movieInfo.getName());
			} else if (comparison == '>') {
				if (movieInfo.getAudienceRating() > targetRating) temp.add(movieInfo.getName());
			} else {
				if (movieInfo.getAudienceRating() < targetRating) temp.add(movieInfo.getName());
			}
		}
		return temp;
	}

	/**
	 * get the list of all actors that the given actor has ever worked with in any movie except the actor herself/himself.
	 * @param actor is the name of an actor as a String
	 * @param actorsInfo is the ArrayList to search through
	 * @return Returns a list of all actors that the given actor has ever worked with in any movie.
	 */
	public ArrayList <String> getCoActors (String actor, ArrayList <Actor> actorsInfo) {
		//return a list of all movies
		// selectWhereActorIs (String actor, ArrayList <Actor> actorsInfo
		// returns the list of all actors in that movie
		//selectWhereMovieIs (String movie, ArrayList <Actor> actorsInfo)
		ArrayList <String> res = new ArrayList<>();
		// a list of all movies the actor act
		ArrayList<String> moviesIn = selectWhereActorIs (actor, actorsInfo);
		if (moviesIn.isEmpty()) return res;
		for (String movieIn : moviesIn) {
			// a list of actors in the given movie
			ArrayList<String> actorsIn = selectWhereMovieIs (movieIn, actorsInfo);
			for (String actorIn: actorsIn) {
				if (! actorIn.equals(actor.trim().toLowerCase()) && ! res.contains(actorIn.trim().toLowerCase())) res.add(actorIn.trim());
			}
		}
		return res;
	}

	/**
	 * get a list of movie where both actors were cast
	 * @param actor1 actor names as String
	 * @param actor2 actor names as String
	 * @param actorsInfo the ArrayList to search through
	 * @return a list of movie names where both actors were cast.
	 */

	public ArrayList <String> getCommonMovie (String actor1, String actor2, ArrayList <Actor> actorsInfo) {
		ArrayList <String> res = new ArrayList<>();
		ArrayList <String> movies1 = selectWhereActorIs (actor1, actorsInfo);
		ArrayList <String> movies2 = selectWhereActorIs (actor2, actorsInfo);
		if (movies1.isEmpty() || movies2.isEmpty()) return res;
		if (actor1.trim().toLowerCase().equals(actor2.trim().toLowerCase())) return movies1;
		for (String movie : movies1) {
			if (movies2.contains(movie)) res.add(movie);
		}
		return res;
	}

	/**
	 * Returns a list of movie names that both critics and the audience have rated above 85 (>= 85).
	 * @param moviesInfo
	 * @return is the ArrayList to search through
	 */

	public ArrayList <String> goodMovies (ArrayList <Movie> moviesInfo) {
		ArrayList <String> res = new ArrayList<>();
		for (Movie movie : moviesInfo) {
			if (movie.getCriticRating() >= 85 && movie.getAudienceRating() >= 85) res.add(movie.getName());
		}
		return res;
	}

	/**
	 * Given a pair of movies, this method returns a list of actors that acted in both movies.
	 * @param movie1 is the names of movie as String
	 * @param movie2 is the names of movie as String
	 * @param actorsInfo is the actor ArrayList
	 * @return a list of actors that acted in both movies.
	 */

	public ArrayList <String> getCommonActors (String movie1, String movie2, ArrayList <Actor> actorsInfo) {
		ArrayList <String> res = new ArrayList<>();
		ArrayList <String> actors1 = selectWhereMovieIs (movie1, actorsInfo);
		ArrayList <String> actors2 = selectWhereMovieIs (movie2, actorsInfo);
		if (actors1.isEmpty() || actors2.isEmpty()) return res;
		if(movie1.trim().toLowerCase().equals(movie2.trim().toLowerCase())) return actors1;
		for (String actor : actors1) {
			if (actors2.contains(actor)) res.add(actor);
		}
		return res;
	}

	/**
	 * Given the moviesInfo DB, this static method returns the mean value of the critics’ ratings and the audience ratings.
	 * @param moviesInfo is the ArrayList
	 * @return  the mean values as a double array
	 */

	public static double [] getMean (ArrayList <Movie> moviesInfo) {
		double [] mean = new double[2];
		ArrayList<Integer> criticsRating = new ArrayList<>();
		ArrayList<Integer> audienceRating = new ArrayList<>();
		for (Movie movieInfo : moviesInfo) {
			criticsRating.add(movieInfo.getCriticRating());
			audienceRating.add(movieInfo.getAudienceRating());
		}
		int size = criticsRating.size();
		double sumCriticsRating = 0;
		double sumAudienceRating = 0;
		for (int i = 0 ; i < size; i++){
			sumCriticsRating += criticsRating.get(i);
			sumAudienceRating += audienceRating.get(i);
		}

		mean[0] = sumCriticsRating / size;
		mean[1] = sumAudienceRating / size;

		return mean;
	}
}




