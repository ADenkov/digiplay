const form = document.querySelector('#movie-form');
const movieDiv = document.querySelector('#movie-player');
const movieScreen = document.querySelector('#movie-screen');

const queryParams = Object.fromEntries(new URLSearchParams(window.location.search));

fetch('http://localhost:8082/api/v1/all')
    .then(result => result.json())
    .then(result => {

        const myVids = document.querySelector('#all-movies');
        if(result.length > 0){
            for(let vid of result){
                const li = document.createElement('LI');
                const link = document.createElement('A');
                link.innerText = vid;
                link.href = window.location.origin + window.location.pathname + '?movie=' + vid;
                li.appendChild(link);
                myVids.appendChild(li);
            }
        }else{
            myVids.innerHTML = 'No movies found';
        }

    });

if(queryParams.video){

    videoScreen.src = `http://localhost:8082/movie/${queryParams.video}`;
    videoDiv.style.display = 'block';
    document.querySelector('#now-playing')
        .innerText = 'Now playing ' + queryParams.video;

}

if(queryParams.video){

    videoScreen.src = `http://localhost:8082/movie/${queryParams.video}`;
    videoDiv.style.display = 'block';
    document.querySelector('#now-playing')
        .innerText = 'Now playing ' + queryParams.video;

}