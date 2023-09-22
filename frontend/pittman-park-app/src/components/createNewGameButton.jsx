import { Link } from 'react-router-dom';

export default function CreateNewGameButton() {
    return (
        <button className="btn btn-primary create-game-btn"><Link to={"/games/create"}>+ Create new game</Link></button>
    );
}