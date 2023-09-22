import { Link } from "react-router-dom";
import { DateTime } from "luxon";

import AttendanceButton from "./attendanceButton";
import { getUser } from "../helperModule";

export default function GameCard({key, game, userId, updateRsvp}) {
    const formattedDateTime = DateTime.fromMillis(Number(game.gameTime)).toFormat('EEE, MMM dd, y, h:mm a ZZZZ');
    const foundPlayer = game.players.find((player) => player.userId === userId);
    const isPlaying = foundPlayer ? true : false;
    const editPath = `/games/${game.gameId}/edit`;
    
    function kickOffRsvp(updateButtonState) {
        updateRsvp(game.gameId, isPlaying, updateButtonState);
    }

    return (
        <li key={key}>
            <div className={isPlaying ? "card mt-2 mb-2 attending" : "card mt-2 mb-2"}>
                <div className="card-header">
                    <div className="row justify-content-evenly">
                        <p className="col-8">{formattedDateTime}</p>
                        <p className={getUser().role === "admin" ? "col-4 text-end edit-game visible" : "col-4 text-end edit-game invisible"}>
                            <Link to={editPath}>
                                <i className="fa-solid fa-pen-to-square" style={{paddingRight: "2px"}}></i>
                                Edit game
                            </Link>
                        </p>
                    </div>
                </div>
                <div className="card-body">
                    <h5 className="card-title">Saturday Morning Pickup</h5>
                    <div className="row card-location">
                        <i className="fa-solid fa-location-dot col-1" style={{paddingTop: "4px", paddingRight: "4px",textAlign: "right"}}></i>
                        <p className="col-11" style={{paddingLeft: "0px"}}>{game.location}</p>
                    </div>
                    <div className="row align-items-center justify-content-between">
                        <div className="col-8">
                            <div className="row align-items-center">
                                <p className="card-text col-8 pe-0 col-sm-6 col-md-5">Number attending: {game.players.length}</p>
                                <p className="attending-notification col-4 ps-0 col-sm-6">{isPlaying && "You're in!"}</p>
                            </div>
                        </div>
                        <AttendanceButton isPlaying={isPlaying} kickOffRsvp={kickOffRsvp}/>
                    </div>
                </div>
            </div>
        </li>
    );
}