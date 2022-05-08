package digiplay.videostreamingservice.service;

import digiplay.videostreamingservice.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {

    Movie getMovie(String name);

    void saveMovie(MultipartFile file, String name) throws IOException;

    List<String> getAllMovieNames();
}
