import file.MovieDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MovieTriviaTest {
	
	//instance of movie trivia object to test
	MovieTrivia mt;
	//instance of movieDB object
	MovieDB movieDB = new MovieDB();
	
	@BeforeEach
	void setUp() throws Exception {
		//initialize movie trivia object
		mt = new MovieTrivia ();
		
		//set up movie trivia object
		mt.setUp("moviedata.txt", "movieratings.csv");
		
		//set up movieDB object
		movieDB.setUp("moviedata.txt", "movieratings.csv");
	}

	@Test
	void testSetUp() { 
		assertEquals(6, movieDB.getActorsInfo().size());
		assertEquals(7, movieDB.getMoviesInfo().size());
		
		assertEquals("meryl streep", movieDB.getActorsInfo().get(0).getName());
		assertEquals(3, movieDB.getActorsInfo().get(0).getMoviesCast().size());
		assertEquals("doubt", movieDB.getActorsInfo().get(0).getMoviesCast().get(0));
		
		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName());
		assertEquals(79, movieDB.getMoviesInfo().get(0).getCriticRating());
		assertEquals(78, movieDB.getMoviesInfo().get(0).getAudienceRating());
	}
	
	@Test
	 void testInsertActor () {
		mt.insertActor("test1", new String [] {"testmovie1", "testmovie2"}, movieDB.getActorsInfo());
		System.out.println(movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size());	
		assertEquals("test1", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName());
		assertEquals(2, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size());
		assertEquals("testmovie1", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().get(0));

		// TODO add additional test case scenarios
		// insert the actor with the movie which both exists
		mt.insertActor("meryl streep", new String [] {"doubt"}, movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size());
		assertEquals("meryl streep", movieDB.getActorsInfo().get(0).getName());
		assertEquals(3, movieDB.getActorsInfo().get(0).getMoviesCast().size());
		assertEquals("doubt", movieDB.getActorsInfo().get(0).getMoviesCast().get(0));

		// insert the actor which has exit and movie does not exist
		mt.insertActor("Meryl streep ", new String [] {"Thatcher"}, movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size());
		assertEquals("meryl streep", movieDB.getActorsInfo().get(0).getName());
		assertEquals(4, movieDB.getActorsInfo().get(0).getMoviesCast().size());
		assertEquals("thatcher", movieDB.getActorsInfo().get(0).getMoviesCast().get(3));

		// insert the actor with no movies
		mt.insertActor("Sherry", new String [] {}, movieDB.getActorsInfo());
		assertEquals(8, movieDB.getActorsInfo().size());
		assertEquals("sherry", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName());
		assertEquals(0, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size());
	}

	@Test
	void testInsertRating () {
		mt.insertRating("testmovie", new int [] {79, 80}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size());
		assertEquals("testmovie", movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getName());
		assertEquals(79, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating());
		assertEquals(80, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating());

		// TODO add additional test case scenarios
		// update the ratings
		mt.insertRating("doubt", new int [] {80, 79}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size());
		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName());
		assertEquals(80, movieDB.getMoviesInfo().get(0).getCriticRating());
		assertEquals(79, movieDB.getMoviesInfo().get(0).getAudienceRating());

		// insert incorrect number
		mt.insertRating("pig", new int [] {-1, 79}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size());
		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName());
		assertEquals(79, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating());
		assertEquals(80, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating());
	}

	@Test
	void testSelectWhereActorIs () {
		assertEquals(3, mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).size());
		assertEquals("doubt", mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).get(0));

		// TODO add additional test case scenarios
		// actor exists
		assertEquals(3, mt.selectWhereActorIs("Tom Hanks", movieDB.getActorsInfo()).size());
		assertEquals("catch me if you can", mt.selectWhereActorIs("Tom Hanks", movieDB.getActorsInfo()).get(1));

		// the actor does not exist
		assertEquals(0, mt.selectWhereActorIs("sherry", movieDB.getActorsInfo()).size());

		// actor exists, but no movie
		assertEquals(0, mt.selectWhereActorIs("Brandon Krakowsky", movieDB.getActorsInfo()).size());
	}

	@Test
	void testSelectWhereMovieIs () {
		assertEquals(2, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).size());
		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("meryl streep"));
		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("amy adams"));

		// TODO add additional test case scenarios
		// only one actor act in this movie
		assertEquals(1, mt.selectWhereMovieIs("Catch Me If You Can", movieDB.getActorsInfo()).size());
		assertEquals(true, mt.selectWhereMovieIs("Catch Me If You Can", movieDB.getActorsInfo()).contains("tom hanks"));
		assertEquals(false, mt.selectWhereMovieIs("Catch Me If You Can", movieDB.getActorsInfo()).contains("amy adams"));

		// non-existent movie
		assertEquals(0, mt.selectWhereMovieIs("911", movieDB.getActorsInfo()).size());

		// not be case sensitive
		assertEquals(2, mt.selectWhereMovieIs("the post", movieDB.getActorsInfo()).size());
		assertEquals(true, mt.selectWhereMovieIs("The Post", movieDB.getActorsInfo()).contains("tom hanks"));
		assertEquals(false, mt.selectWhereMovieIs("The Post", movieDB.getActorsInfo()).contains("amy adams"));

	}

	@Test
	void testSelectWhereRatingIs () {
		assertEquals(6, mt.selectWhereRatingIs('>', 0, true, movieDB.getMoviesInfo()).size());
		assertEquals(0, mt.selectWhereRatingIs('=', 65, false, movieDB.getMoviesInfo()).size());
		assertEquals(2, mt.selectWhereRatingIs('<', 30, true, movieDB.getMoviesInfo()).size());

		// TODO add additional test case scenarios
		// Non-existent comparison (e.g. ‘?’)
		assertEquals(0, mt.selectWhereRatingIs('?', 0, true, movieDB.getMoviesInfo()).size());

		// A targetRating that is out of range
		assertEquals(0, mt.selectWhereRatingIs('>', 101, true, movieDB.getMoviesInfo()).size());

		// correct comparison
		assertEquals(0, mt.selectWhereRatingIs('>', 98, true, movieDB.getMoviesInfo()).size());
		assertEquals(1, mt.selectWhereRatingIs('=', 95, false, movieDB.getMoviesInfo()).size());
		assertEquals(3, mt.selectWhereRatingIs('<', 80, true, movieDB.getMoviesInfo()).size());
	}

	@Test
	void testGetCoActors () {
		assertEquals(2, mt.getCoActors("meryl streep", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("tom hanks"));
		assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("amy adams"));

		// TODO add additional test case scenarios
		// non-existent actors
		assertEquals(0, mt.getCoActors("sherry", movieDB.getActorsInfo()).size());

		// with leading or trailing whitespace
		assertEquals(2, mt.getCoActors(" meryl streep ", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCoActors("meryl streep ", movieDB.getActorsInfo()).contains("tom hanks"));
		assertTrue(mt.getCoActors(" meryl streep", movieDB.getActorsInfo()).contains("amy adams"));

        // not be case sensitive
		assertEquals(2, mt.getCoActors(" Meryl Streep ", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCoActors("meryl streep ", movieDB.getActorsInfo()).contains("tom hanks"));
		assertTrue(mt.getCoActors(" meryl streep", movieDB.getActorsInfo()).contains("amy adams"));
	}

	@Test
	void testGetCommonMovie () {
		assertEquals(1, mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).contains("the post"));

		// TODO add additional test case scenarios
		// non-existent actors
		assertEquals(0, mt.getCommonMovie("sherry", "tom hanks", movieDB.getActorsInfo()).size());

		// with leading or trailing whitespace
		assertEquals(1, mt.getCommonMovie(" meryl streep ", "tom hanks", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).contains("the post"));

		// not be case sensitive
		assertEquals(1, mt.getCommonMovie(" Meryl Streep ", "tom hanks", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).contains("the post"));
	}

	@Test
	void testGoodMovies () {
		assertEquals(3, mt.goodMovies(movieDB.getMoviesInfo()).size());
		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("jaws"));

		// TODO add additional test case scenarios
		assertEquals(3, mt.goodMovies(movieDB.getMoviesInfo()).size());
		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("rocky ii"));

		// not contains
		assertFalse(mt.goodMovies(movieDB.getMoviesInfo()).contains("Doubt"));

		// contains
		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("et"));

	}

	@Test
	void testGetCommonActors () {
		assertEquals(1, mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).contains("meryl streep"));

		// TODO add additional test case scenarios
		// non-existent movie
		assertEquals(0, mt.getCommonActors("911", "the post", movieDB.getActorsInfo()).size());

		// movie1 and movie2 be the same
		assertEquals(2, mt.getCommonActors("doubt", "doubt", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonActors("doubt", "doubt", movieDB.getActorsInfo()).contains("meryl streep"));

		// with leading or trailing whitespace
		assertEquals(1, mt.getCommonActors(" doubt", "the post ", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonActors("doubt ", " the post", movieDB.getActorsInfo()).contains("meryl streep"));
	}
	
	@Test
	void testGetMean () {
		//fail();

		// TODO add ALL test case scenarios!
		assertEquals(67.85, mt.getMean(movieDB.getMoviesInfo())[0], 0.01);
		assertEquals(65.71, mt.getMean(movieDB.getMoviesInfo())[1], 0.01);

		// insert 1 with [0,0]
		mt.insertRating("911", new int [] {0, 0}, movieDB.getMoviesInfo());
		assertEquals(59.37, mt.getMean(movieDB.getMoviesInfo())[0], 0.01);
		assertEquals(57.5, mt.getMean(movieDB.getMoviesInfo())[1], 0.01);

		// insert 1 with [90, 90]
		mt.insertRating("cit590", new int [] {90, 90}, movieDB.getMoviesInfo());
		assertEquals(62.77, mt.getMean(movieDB.getMoviesInfo())[0], 0.01);
		assertEquals(61.11, mt.getMean(movieDB.getMoviesInfo())[1], 0.01);

		// insert 1 with [90, 100]
		mt.insertRating("chocolate", new int [] {90, 100}, movieDB.getMoviesInfo());
		assertEquals(65.5, mt.getMean(movieDB.getMoviesInfo())[0], 0.01);
		assertEquals(65, mt.getMean(movieDB.getMoviesInfo())[1], 0.01);

	}
}
