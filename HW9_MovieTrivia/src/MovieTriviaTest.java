import file.MovieDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


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
		assertEquals(7, movieDB.getActorsInfo().size());	
		assertEquals("test1", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName());
		assertEquals(2, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size());
		assertEquals("testmovie1", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().get(0));
		
		// TODO add additional test case scenarios
	}

	@Test
	void testInsertRating () {
		mt.insertRating("testmovie", new int [] {79, 80}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size());
		assertEquals("testmovie", movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getName());
		assertEquals(79, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating());
		assertEquals(80, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating());

		// TODO add additional test case scenarios
	}
//
//	@Test
//	void testSelectWhereActorIs () {
//		assertEquals(3, mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).size());
//		assertEquals("doubt", mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).get(0));
//
//		// TODO add additional test case scenarios
//	}
//
//	@Test
//	void testSelectWhereMovieIs () {
//		assertEquals(2, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).size());
//		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("meryl streep"));
//		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("amy adams"));
//
//		// TODO add additional test case scenarios
//	}
//
//	@Test
//	void testSelectWhereRatingIs () {
//		assertEquals(6, mt.selectWhereRatingIs('>', 0, true, movieDB.getMoviesInfo()).size());
//		assertEquals(0, mt.selectWhereRatingIs('=', 65, false, movieDB.getMoviesInfo()).size());
//		assertEquals(2, mt.selectWhereRatingIs('<', 30, true, movieDB.getMoviesInfo()).size());
//
//		// TODO add additional test case scenarios
//	}
//
//	@Test
//	void testGetCoActors () {
//		assertEquals(2, mt.getCoActors("meryl streep", movieDB.getActorsInfo()).size());
//		assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("tom hanks"));
//		assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("amy adams"));
//
//		// TODO add additional test case scenarios
//	}
//
//	@Test
//	void testGetCommonMovie () {
//		assertEquals(1, mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).size());
//		assertTrue(mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).contains("the post"));
//
//		// TODO add additional test case scenarios
//	}
//
//	@Test
//	void testGoodMovies () {
//		assertEquals(3, mt.goodMovies(movieDB.getMoviesInfo()).size());
//		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("jaws"));
//
//		// TODO add additional test case scenarios
//	}
//
//	@Test
//	void testGetCommonActors () {
//		assertEquals(1, mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).size());
//		assertTrue(mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).contains("meryl streep"));
//
//		// TODO add additional test case scenarios
//	}
	
	@Test
	void testGetMean () {
		fail();
		
		// TODO add ALL test case scenarios!
	}
}
