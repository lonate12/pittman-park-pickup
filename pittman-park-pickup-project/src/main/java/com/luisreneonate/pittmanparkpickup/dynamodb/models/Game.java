package main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.*;

@DynamoDBTable(tableName = "Games")
public class Game {
    private String gameId;
    private String gameTime;
    private String location;
    private List<User> players;
    private String status;

    public Game() {};
    public Game(String gameTime, String location) {
        this.gameId = UUID.randomUUID().toString();
        this.gameTime = gameTime;
        this.location = location;
        this.players = new ArrayList<>();
    }

    @DynamoDBHashKey(attributeName = "gameId")
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

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

    @DynamoDBAttribute(attributeName = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
}
