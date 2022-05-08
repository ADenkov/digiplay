package digiplay.videostreamingservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

//    private List<String> genres;

    private String starring;

    private String releaseDate;

    private String rating;

    @Lob
    private byte[] data;

    public Movie(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }
}
