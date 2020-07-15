package com.cinema;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MovieStoreBase {
    private final List<Movie> movies = new LinkedList<>();
    public void add(final Movie movie) {
        movies.add(movie);
    }

    public <T> List<T> filterMoviesBy(Predicate<T> fn, List<T> movies) {
        return movies.stream().filter(fn).collect(Collectors.toList());
    }

    public List<Movie> findByPartialTitle(final String partialTitle) {
       return filterMoviesBy(movie -> movie.title().toUpperCase().contains(partialTitle.toUpperCase()), movies);
    }
    public List<Movie> findByDirector(final String director) {
        return filterMoviesBy(movie -> movie.director().equals(director), movies);
    }
    public List<Movie> findByReleaseYear(final int from, final int to) {
        return filterMoviesBy(movie -> movie.releaseYear() >= from && movie.releaseYear() <= to, movies);
    }
    static class Movie {
        private final String title;
        private final String director;
        private final int releaseYear;
        public Movie(final String title, final String director, final int releaseYear) {
            this.title = title;
            this.director = director;
            this.releaseYear = releaseYear;
        }
        public String title() {
            return title;
        }
        public String director() {
            return director;
        }
        public int releaseYear() {
            return releaseYear;
        }
    }
}