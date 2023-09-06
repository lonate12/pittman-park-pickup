package main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@DynamoDBTable(tableName = "games")
public class Game {
    private UUID gameId;
    private LocalDateTime gameTime;
    private String location;
    private List<User> players;
    // Question: Do I need an attribute to denote if a game has been played or not
    //  (e.g. status with an enum Upcoming, Played, Cancelled, etc.)? Or, when I query Dynamo
    //  do I just query for games with a gameTime after the current time?
    // Question: Follow-up question on this same topic, when it comes to deleting games, should we actually
    //  delete them from the DB, or just mark them as cancelled and filter those out when we search for games
    //  in the DB?

    public Game() {};
    public Game(LocalDateTime gameTime, String location) {
        this.gameId = UUID.randomUUID();
        this.gameTime = gameTime;
        this.location = location;
        this.players = new ArrayList<User>();
    }

    @DynamoBDHashKey(attributeName = "gameId")
//    Question: Does this need to be returned as a string vs. a UUID? Same question for the non-String values below?
    //  Or, do we take care of that in a different class?
    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    @DynamoDBRangeKey(attributename = "gameTime")
    public LocalDateTime getGameTime() {
        return gameTime;
    }

    public void setGameTime(LocalDateTime newGameTime) {
        // TODO: Some validation checks to make sure the new GameTime is in the correct format,
        //  the time hasn't already passed, and it's not to close to the original game time.
        //  If there's something off, throw an exception
        this.gameTime = newGameTime;
    }

    @DynamoDBAttribute(attributeName = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @DynamoDBAttribute(attributeName = "players")
    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        // TODO: Do we need to do any kind of validation here? We need to check that we don't have duplicate players,
        //  but should we do that here or somewhere else?
        this.players = players;
    }


    // Question: For equals and hashcode, because we'll have a UUID for each game, I'm using that as the
    //  only attribute to check when it comes to equality and hashcode, is that ok?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(getGameId(), game.getGameId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId());
    }
}
