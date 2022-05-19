import React, { Component } from 'react'

export default class Player extends Component {
    constructor(props) {
        super(props);
        this.state = {
            movieId: this.props.match.params.id,
            movieData: {}
        }
    }

    async componentDidMount() {
        try {
            const res = await fetch(`http://localhost:8082/movie/${this.state.movieId}/data`);
            const data = await res.json();
            this.setState({ movieData: data});
        } catch (error) {
            console.log(error);
        }
    }
    
    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <video controls muted autoPlay>
                        <source src={`http://localhost:8082/movie/${this.state.movieId}`} type="video/mp4" />
                    </video>
                    <h1>{ this.state.movieData.name }</h1>
                </header>
            </div>
        )
    }
}