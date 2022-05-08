package digiplay.videostreamingservice.controller;

import digiplay.videostreamingservice.service.MovieService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(MovieController.BASE_URL)
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieController {

    public static final String BASE_URL = "/api/v1";

    private MovieService service;

    @PostMapping()
    public ResponseEntity<String> saveMovie(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws IOException {
        service.saveMovie(file, name);
        return ResponseEntity.ok("Movie saved successfully.");
    }

    @GetMapping("{name}")
    public ResponseEntity<Resource> getMovieByName(@PathVariable("name") String name){
        return ResponseEntity
                .ok(new ByteArrayResource(service.getMovie(name).getData()));
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllMovieNames(){
        return ResponseEntity
                .ok(service.getAllMovieNames());
    }
}
