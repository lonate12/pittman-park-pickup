import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { RotatingLines } from 'react-loader-spinner';
import axios from 'axios';

import { axiosConfigObj, baseApiUrl } from '../helperModule';
import GameList from './gameList';

import CreateNewGameButton from './createNewGameButton';

export default function HomePage({user}) {
    const [games, setGames] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);
        axios.get(`${baseApiUrl}/games`, axiosConfigObj)
        .then((res) => {
                console.log(res.data.games);
                setGames(res.data.games);
                setLoading(false);
            });
    }, []);

    function updateRsvp(gameId, currentlyAttending, updateButtonState) {
        const action = currentlyAttending ? "REMOVE" : "ADD";
        console.log(`Kicked off updateRsvp function. Passing gameID ${gameId} and currentlyAttending as ${currentlyAttending}`);
        axios.put(`${baseApiUrl}/games/${gameId}`,{
            "playerRsvp": {
                "action": action,
                "playerId": user.userId
            }
        }, axiosConfigObj)
        .then((res) => {
            const updatedGame = res.data.updatedGame;
            let gamesCopy = JSON.parse(JSON.stringify(games));

            let copyIndex = gamesCopy.findIndex(game => {
                return game.gameId === updatedGame.gameId;
            });

            if (copyIndex !== -1) {
                gamesCopy[copyIndex] = updatedGame;
            }

            setGames(gamesCopy);
            updateButtonState(false);

        }).catch(e => {
            if (e.response) {
                console.log("Request was made, server responded with non 200");
                console.log(e);
            } else if (e.request) {
                console.log("Request was made, but no response received");
                console.log(e);
            } else {
                console.log(e);
            }
        });
    }

    return (
        <>
            <div className="row">
                <h1 className="welcome-header text-center col-12 col-md-4 offset-md-4 mb-1"><Link to="/">Pittman Park Pickup</Link></h1>
                <h3 className="col-12 col-md-4 text-center text-md-end">{user.role === "admin" && <CreateNewGameButton />}</h3>
                <h3 className="text-center">Hey, {user.firstName}!</h3>
            </div>
            <ul className={loading ? "list-unstyled col-md-8 offset-md-2 text-center" : "list-unstyled col-lg-8 offset-lg-2 col-12"}>
                { loading ? <RotatingLines strokeColor="grey"/> : <GameList games={games} userId={user.userId} updateRsvp={updateRsvp}/> }
            </ul>
            
        </>
    );
}