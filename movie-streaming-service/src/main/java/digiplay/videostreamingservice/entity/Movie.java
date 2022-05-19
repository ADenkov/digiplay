package digiplay.videostreamingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String[] genres;

//    private String starring;

    private String releaseDate;

    private int rating;

    private String data;

//    public Movie(String name, String[] genres, String releaseDate, int rating, String data) {
//        this.name = name;
//        this.data = data;
//        this.genres = genres;
//        this.releaseDate = releaseDate;
//        this.rating = rating;
//    }
}
