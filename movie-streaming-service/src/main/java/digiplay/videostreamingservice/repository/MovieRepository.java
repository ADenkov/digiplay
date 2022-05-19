package digiplay.videostreamingservice.repository;

import digiplay.videostreamingservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findById(long id);

    boolean existsById(long id);

    List<Movie> findAll();

//    @Query(nativeQuery = true, value="SELECT name FROM movie")
//    List<String> getAllEntryNames();
}
