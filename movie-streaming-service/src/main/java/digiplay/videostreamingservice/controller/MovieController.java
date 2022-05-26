package digiplay.videostreamingservice.controller;

import digiplay.videostreamingservice.entity.Movie;
import digiplay.videostreamingservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(MovieController.BASE_URL)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieController {

    public static final String BASE_URL = "/api/v1";

    @Autowired
    private MovieService service;

    @PostMapping("/save")
    public ResponseEntity<String> saveMovie(@RequestBody Movie movie) throws IOException {
        service.saveMovie(movie);
        return ResponseEntity.ok("Movie saved successfully.");
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("id") long id){
        return ResponseEntity
                .ok(service.getMovie(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity
                .ok(service.getAllMovies());
    }
}
