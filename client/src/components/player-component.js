import React, { useState } from "react";
import { useEffect } from "react";
import { useParams } from "react-router-dom";
import gateway from "../services/gateway-service";

function MoviePlayer() {
  // this.state = {
  //   //movieId: this.props.match.params.id,
  //   movieData: "",
  // };

  //   try {
  //     const res = await fetch(
  //       `http://localhost:8080/movies/api/v1/movie/${this.state.movieId}/data`
  //     );
  //     const data = await res.json();
  //     this.setState({ movieData: data });
  //     console.log(this.props.match.params.id);
  //   } catch (error) {
  //     console.log(error);
  //   }

  let { id } = useParams();
  console.log(id);
  const [movie, setMovie] = useState("");

  useEffect(() => {
    gateway.getMovie(id).then(
      (response) => {
        setMovie(response.data);
        console.log(response.data);
      },
      (error) => {
        this.setState({
          content:
            (error.response && error.response.data) ||
            error.message ||
            error.toString(),
        });
      }
    );
  }, []);

  return (
    <div className="App">
      <header className="App-header">
      <h1>{movie.name}</h1>
        {movie === "" ? (
          <p>Player loading...</p>
        ) : (
          <video controls autoPlay muted>
            <source src={movie.data} type="video/mp4" />
          </video>
        )}
      </header>
    </div>
  );
}

export default MoviePlayer;
