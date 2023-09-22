package main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.*;

@DynamoDBTable(tableName = "Games")
public class Game {
    private String gameId;
    private String gameTime;
    private String location;
    private List<User> players;
    private GameStatus status;

    public Game() {
    };

    public Game(String gameTime, List<User> playerList) {
        this(UUID.randomUUID().toString(), gameTime, "Pittman Park", playerList, GameStatus.ACTIVE);
    }

    public Game(String gameTime, List<User> playerList, String location) {
        this(UUID.randomUUID().toString(), gameTime, location, playerList, GameStatus.ACTIVE);
    }

    public Game(String gameId, String gameTime, String location, List<User> players, GameStatus status) {
        this.gameId = gameId;
        this.gameTime = gameTime;
        this.location = location;
        this.players = players;
        this.status = status;
    }

    @DynamoDBHashKey(attributeName = "gameId")
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "status-gameTime-index")
    @DynamoDBAttribute(attributeName = "gameTime")
    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
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
        this.players = players;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "status-gameTime-index")
    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "status")
    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

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

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                ", gameTime='" + gameTime + '\'' +
                ", location='" + location + '\'' +
                ", players=" + players +
                ", status=" + status +
                '}';
    }
}
