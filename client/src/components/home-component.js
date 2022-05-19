import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import UserService from "../services/user-service";

export default class Home extends Component {
    constructor() {
        super();
        this.state = {
            movies: []
        };
    }
    // async componentDidMount() {
    //     try {
    //         const response = await fetch(`http://localhost:8082/api/v1/all`);
    //         const data = await response.json();
    //         this.setState({ movies: [...data] });
    //     } catch (error) {
    //         console.log(error);
    //     }
    // }
    async componentDidMount() {
        UserService.getHomepage().then(
          response => {
            this.setState({
              movies: response.data
            });
          },
          error => {
            this.setState({
              content:
                (error.response && error.response.data) ||
                error.message ||
                error.toString()
            });
          }
        );
      }

    render() {
        return (
            <div className="App App-header">
                <div className="container">
                    {/* <video width="720" height="480" controls>
                        <source
                            src="https://storage.googleapis.com/digiplay-movie-storage/The%20Greatest%20Lie%20Ever%20Told%20-%20The%20Holocaust%20(2015)%20Documentary.mp4"
                            type="video/mp4"
                        />
                    </video> */}
                    <div className="row">
                        {this.state.movies.map(movie =>
                            <div className="col-md-4" key={movie.id}>
                                <Link to={`/player/${movie.id}`}>
                                    <div className="card border-0">
                                        <img src={`http://localhost:8082/api/v1/${movie.poster}`} alt={movie.name} />
                                            <div className="card-body">
                                                <p>{movie.name}</p>
                                                <p>{movie.duration}</p>
                                            </div>
                                    </div>
                                </Link>
                            </div>
                        )}
                    </div>
                </div>
            </div>
        )
    }
}