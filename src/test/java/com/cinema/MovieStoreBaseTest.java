package com.cinema;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static com.cinema.MovieStoreBase.*;

public class MovieStoreBaseTest {
    private final MovieStoreBase movieStore = new MovieStoreBase();
    private final Movie harryPotter = new Movie("Harry Potter", "Rowling", 2000);
    private final Movie starWars = new Movie("Star Wars", "Shwimmer", 1999);
    private final Movie starTrek = new Movie("STAR Trek", "Shwimmer", 2002);
    private final Movie shawshank = new Movie("Shawshank Redemption", "Bob", 2001);
    private final Movie takeThat = new Movie("Take That", "Shwimmer", 2010);

    @Before
    public void setUp() throws Exception {
        movieStore.add(shawshank);
        movieStore.add(harryPotter);
        movieStore.add(starWars);
        movieStore.add(starTrek);
        movieStore.add(takeThat);
    }

    @Test
    public void returnsNoResultsWhenNoTitlesPartiallyMatchSearch() throws Exception {
        List<Movie> results = movieStore.findByPartialTitle("abc");
        assertEquals(0, results.size());
    }

    @Test
    public void findsMoviesWhenTitlesArePartiallyMatchedCaseInsensitive() throws Exception {
        List<Movie> results = movieStore.findByPartialTitle("tar");
        assertEquals(2, results.size());
        assertThat(results, containsInAnyOrder(starTrek, starWars));
    }

    @Test
    public void findsMoviesWhenDirectorExactlyMatches() throws Exception {
        List<Movie> results = movieStore.findByDirector("Shwimmer");
        assertEquals(3, results.size());
        assertThat(results, containsInAnyOrder(starTrek, starWars, takeThat));
    }

    @Test
    public void findsMoviesWhenReleaseYearIsBetweenTwoValues() throws Exception {
        List<Movie> results = movieStore.findByReleaseYear(2000, 2001);
        assertEquals(2, results.size());
        assertThat(results, containsInAnyOrder(harryPotter, shawshank));
    }
}