package digiplay.videostreamingservice.service;

import digiplay.videostreamingservice.entity.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieService {

    Movie getMovie(long id);

    void saveMovie(Movie movie) throws IOException;

    List<Movie> getAllMovies();
}
