package main.java.com.luisreneonate.pittmanparkpickup.apimodels;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.User;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class GameModel {
    private String gameId;
    private String gameTime;
    private String location;
    private List<User> players;
    private String status;

    public GameModel() {

    }

    public GameModel(Builder builder) {
        this.gameId = builder.gameId;
        this.gameTime = builder.gameTime;
        this.location = builder.location;
        this.players = builder.players;
        this.status = builder.status;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameModel)) return false;
        GameModel gameModel = (GameModel) o;
        return Objects.equals(getGameId(), gameModel.getGameId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId());
    }

    @Override
    public String toString() {
        return "GameModel{" +
                "gameId='" + gameId + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String gameId;
        private String gameTime;
        private String location;
        private List<User> players;
        private String status;

        public Builder withGameId(String gameIdToUse) {
            this.gameId = gameIdToUse;
            return this;
        }

        public Builder withGameTime(String gameTimeToUse) {
            this.gameTime = gameTimeToUse;
            return this;
        }

        public Builder withLocation(String locationToUse) {
            this.location = locationToUse;
            return this;
        }

        public Builder withPlayers(List<User> playersToUse) {
            this.players = playersToUse;
            return this;
        }

        public Builder withStatus(String statusToUse) {
            this.status = statusToUse;
            return this;
        }

        public GameModel build() {
            return new GameModel(this);
        }
    }
}

