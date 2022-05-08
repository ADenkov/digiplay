package digiplay.videostreamingservice.service;

import digiplay.videostreamingservice.entity.Movie;
import digiplay.videostreamingservice.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository repository;

    @Override
    public Movie getMovie(String name) {
//        if(!repository.existsByName(name)){
//            throw new VideoNotFoundException();
//        }
        return repository.findByName(name);
    }

    @Override
    public void saveMovie(MultipartFile file, String name) throws IOException {
        if(repository.existsByName(name)){
            throw new IllegalArgumentException();
        }
        Movie newMovie = new Movie(name, file.getBytes());
        repository.save(newMovie);
    }

    @Override
    public List<String> getAllMovieNames() {
        return repository.getAllEntryNames();
    }

}
