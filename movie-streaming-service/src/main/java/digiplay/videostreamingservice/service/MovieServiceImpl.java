package digiplay.videostreamingservice.service;

import digiplay.videostreamingservice.entity.Movie;
import digiplay.videostreamingservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository repository;

    @Override
    public String getMovie(long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }
        Movie mov = repository.findById(id);

        return mov.getData();
    }

    @Override
    public void saveMovie(Movie movie) throws IOException {

        Movie newMovie = new Movie(0, movie.getName(), movie.getGenres(), movie.getReleaseDate(), movie.getRating(), movie.getData());
        repository.save(newMovie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

//    @Override
//    public List<String> getAllMoviesWithName(String name) {
//        return repository.getAllEntryNames();
//    }

}
