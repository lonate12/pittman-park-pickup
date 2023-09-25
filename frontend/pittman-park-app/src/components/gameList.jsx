import GameCard  from './gameCard';

export default function GameList({games, userId, updateRsvp}) {
    return (
        <>
            {!games ? <h1 className="text-center" style={{color: "white"}}>No scheduled games right now.</h1> : games.map((game) => {
                return (
                    <GameCard key={game.id} game={game} userId={userId} updateRsvp={updateRsvp}/>
                );
            })}
        </>
    );
}