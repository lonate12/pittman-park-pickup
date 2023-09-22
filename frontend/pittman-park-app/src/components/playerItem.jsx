export default function PlayerItem({ player, removePlayer }) {
    return (
        <li className="list-group-item d-flex justify-content-between align-items-start me-2" key={player.userId}>
            <div className="ms-2 me-auto">
                <div className="fw-bold">{player.firstName} {player.lastName}</div>
            </div>
            <span className="badge bg-danger rounded-pill ms-2 player-item" onClick={() => removePlayer(player.userId)}>
                <i className="fa-solid fa-x" style={{color: "white", paddingTop: "1px", paddingRight: "1px"}}></i>
            </span>
        </li>
    );
}